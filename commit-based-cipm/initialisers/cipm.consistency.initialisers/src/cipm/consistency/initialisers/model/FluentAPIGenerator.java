package cipm.consistency.initialisers.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaFactory;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.commons.Commentable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FluentAPIGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String fluentAPIClassSuffix = "Initialisation";

	private static final String fluentAPIClassName = "FluentJavaInitialisation";
	private static final String fluentAPIRootPacName = "java";

	private static final String initModelDirName = "initModel";
	private static final File initModelFile = new File(initModelDirName).getAbsoluteFile();
	private static final Path ecoreFilePath = initModelFile.toPath().resolve("initialiserModels.ecore");
	private static final Path genmodelFilePath = initModelFile.toPath().resolve("initialiserModels.genmodel");

	private static final List<EOperation> allNewOperations = new ArrayList<EOperation>();

	@Test
	public void generateModelFiles() {
		if (initModelFile.exists()) {
			for (var file : initModelFile.listFiles()) {
				file.delete();
			}
			initModelFile.delete();
		}

		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(URI.createFileURI(ecoreFilePath.toString()));

		res.getContents().add(this.generateJavaPackage());
		try {
			res.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}

	private EPackage generateJavaPackage() {
		var javaPac = EcoreFactory.eINSTANCE.createEPackage();
		javaPac.setName(fluentAPIRootPacName);
		javaPac.setNsPrefix(fluentAPIRootPacName);
		javaPac.setNsURI(URI.createFileURI(fluentAPIRootPacName).toString());

		// Add the classes for individual model elements
		javaPac.getEClassifiers().addAll(this.getFluentAPIClasses());

		// Add the Class for composite Initialisation class
		javaPac.getEClassifiers().add(this.generateFluentAPIGeneratorClass());

		return javaPac;
	}

	private List<EClass> getFluentAPIClasses() {
		var javaSubPackages = JavaPackageUtil.getAllSubPackages();
		var fluentAPISubClss = new ArrayList<EClass>();

		for (var javaSubPac : javaSubPackages) {
			var clss = this.getFluentAPIClassesFor(javaSubPac);
			if (clss != null)
				fluentAPISubClss.addAll(clss);
		}

		return fluentAPISubClss;
	}

	private List<EClass> getFluentAPIClassesFor(EPackage javaSubPackage) {
		var clss = new ArrayList<EClass>();

		List<EClass> eClssToInit = javaSubPackage.getEClassifiers().stream().filter((cls) -> cls instanceof EClass)
				.map((cls) -> (EClass) cls).filter((cls) -> !cls.isAbstract() && !cls.isInterface())
				.filter((cls) -> Commentable.class.isAssignableFrom(cls.getInstanceClass())).map((cls) -> (EClass) cls)
				.collect(Collectors.toCollection(ArrayList::new));
		if (eClssToInit.isEmpty())
			return null;

		for (var eCls : eClssToInit) {
			var apiSubCls = EcoreFactory.eINSTANCE.createEClass();
			apiSubCls.setName(eCls.getName() + fluentAPIClassSuffix);

			// Add an EReference for covered model element types
			var modelElementsToInitialiseRef = EcoreFactory.eINSTANCE.createEReference();
			var modelElementsToInitialiseRefName = "initialisedClass";
			modelElementsToInitialiseRef.setChangeable(true);
			modelElementsToInitialiseRef.setContainment(false);
			modelElementsToInitialiseRef.setEType(eCls.eClass().eClass());
			modelElementsToInitialiseRef.setName(modelElementsToInitialiseRefName);
			modelElementsToInitialiseRef.setLowerBound(1);
			modelElementsToInitialiseRef.setUpperBound(1);
			apiSubCls.getEStructuralFeatures().add(modelElementsToInitialiseRef);
			// TODO Find a way to set the EReference above most likely through a protected
			// init() method in constructor (?). apiSubCls.eSet(...) does not work

			// Add initialisation operations
			var newOp = getNewOperationFor(eCls);
			if (newOp != null) {
				allNewOperations.add(newOp);
				apiSubCls.getEOperations().add(newOp);
			}

			var withOps = getWithOperationsFor(eCls);
			if (withOps != null)
				apiSubCls.getEOperations().addAll(withOps);

			clss.add(apiSubCls);
		}

		return clss;
	}

	private static boolean isConcrete(EClass elemToInit) {
		return !elemToInit.isAbstract() && !elemToInit.isInterface();
	}

	private static EOperation getNewOperationFor(EClass elemToInit) {
		if (!isConcrete(elemToInit))
			return null;

		var op = EcoreFactory.eINSTANCE.createEOperation();
		var elemInstanceName = elemToInit.getInstanceClass().getSimpleName();
		op.setName("new" + elemInstanceName);
		op.setEType(elemToInit);

		// Add the method body
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(genModelURL);
		var bodyKey = "body";
		// TODO Add hooks to creation methods (?)
		var bodyValue = String.format("return %s.eINSTANCE.create%s();",
				getFactoryNameForEPackage(elemToInit.getEPackage()), elemInstanceName);
		anno.getDetails().put(bodyKey, bodyValue);

		op.getEAnnotations().add(anno);
		return op;
	}

	private static List<EOperation> getWithOperationsFor(EClass elemToInit) {
		if (!isConcrete(elemToInit))
			return null;

		var ops = new ArrayList<EOperation>();
		var concreteCls = elemToInit.getEPackage().getEFactoryInstance().create(elemToInit).eClass();
		var allFeats = concreteCls.getEAllStructuralFeatures();
		// Do not include features that are non-changeable or those that do not concern
		// Java model elements directly
		for (var feat : allFeats.stream()
				.filter((attr) -> !attr.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class))
//				.filter((attr) -> EObject.class.isAssignableFrom(attr.getEType().getInstanceClass()))
				.filter((attr) -> attr.isChangeable()).toArray(EStructuralFeature[]::new)) {

			var op = EcoreFactory.eINSTANCE.createEOperation();
			var elemInstanceName = elemToInit.getInstanceClass().getSimpleName();
			var featName = feat.getName();
			op.setName(String.format("with%s_%s", elemInstanceName, featName));

			// Add obj param to operation, which is the element to initialise
			var objParam = EcoreFactory.eINSTANCE.createEParameter();
			objParam.setEType(elemToInit);
			objParam.setName("objToInit");
			objParam.setLowerBound(1);
			objParam.setUpperBound(1);
			op.getEParameters().add(objParam);

			// Add feature param to operation, which will be the new value of obj.feat
			var featValParam = EcoreFactory.eINSTANCE.createEParameter();
			featValParam.setEType(feat.getEType());
			if (!feat.isMany()) {
				featValParam.setName("newFeatVal");
			} else {
				featValParam.setName("addToFeatVal");
			}
			featValParam.setLowerBound(1);
			featValParam.setUpperBound(1);
			op.getEParameters().add(featValParam);

			// Add the method body
			var anno = EcoreFactory.eINSTANCE.createEAnnotation();
			anno.setSource(genModelURL);
			var bodyKey = "body";
			// TODO Add hooks to creation methods (?)
			var bodyValue = "";
			if (!feat.isMany()) {
				bodyValue = String.format(
						"objToInit.eSet(objToInit.eClass().getEStructuralFeature(\"%s\"), newFeatVal);",
						feat.getName());
			} else {
				bodyValue = String.format("var val = objToInit.eGet(objToInit.eClass().getEStructuralFeature(\"%s\"));"
						+ System.lineSeparator() + "((EList) val).add(addToFeatVal);", feat.getName());
			}
			anno.getDetails().put(bodyKey, bodyValue);

			op.getEAnnotations().add(anno);

			ops.add(op);
		}
		return ops;
	}

	private static String getFactoryNameForEPackage(EPackage pac) {
		return pac.getEFactoryInstance().getClass().getName().replaceFirst(".impl", "").replace("Impl", "");
	}

	private EClass generateFluentAPIGeneratorClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();
		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(fluentAPIClassName);

		// TODO Add operations here

		return fluentAPICls;
	}
}
