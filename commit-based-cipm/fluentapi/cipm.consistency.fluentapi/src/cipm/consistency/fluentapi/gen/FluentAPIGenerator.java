package cipm.consistency.fluentapi.gen;

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
import org.eclipse.emf.ecore.EReference;
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

		// Add the super type for sub initialisation classes
		var fluentAPISubClssSuperType = getFluentAPISuperType();
		javaPac.getEClassifiers().add(fluentAPISubClssSuperType);

		// Add the classes for individual model elements
		var fluentAPISubClss = this.getFluentAPIClasses(fluentAPISubClssSuperType);
		fluentAPISubClss.forEach((cls) -> cls.getESuperTypes().add(fluentAPISubClssSuperType));
		javaPac.getEClassifiers().addAll(fluentAPISubClss);

		// Add the Class for composite Initialisation class
		var fluentAPICls = this.getFluentAPIClass();
		var subInitsRef = getSubInitialisationsReference(fluentAPISubClssSuperType);
		fluentAPICls.getEStructuralFeatures().add(subInitsRef);
		javaPac.getEClassifiers().add(fluentAPICls);

		// Interconnected EReference setup
		fluentAPISubClss.forEach((cls) -> {
			var rootInitRef = getRootInitialisationReference(fluentAPICls);
			cls.getEStructuralFeatures().add(rootInitRef);
		});

		return javaPac;
	}

	private List<EClass> getFluentAPIClasses(EClass fluentAPISubClssSuperType) {
		var javaSubPackages = JavaPackageUtil.getAllSubPackages();
		var fluentAPISubClss = new ArrayList<EClass>();

		for (var javaSubPac : javaSubPackages) {
			var clss = this.getFluentAPIClassesFor(javaSubPac);
			if (clss != null)
				fluentAPISubClss.addAll(clss);
		}

		return fluentAPISubClss;
	}

	private static final String fluentAPISubClassesSuperTypeName = "IInitialisation";

	private EClass getFluentAPISuperType() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(true);
		superType.setName(fluentAPISubClassesSuperTypeName);
		return superType;
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
			apiSubCls.getEStructuralFeatures().add(getInitialisedClassReference(eCls));

			// Add an EReference for all instantiated elements
			apiSubCls.getEStructuralFeatures().add(getCreatedElementsReference(eCls));

			// TODO Find a way to set the EReference above most likely through a protected
			// init() method in constructor (?). apiSubCls.eSet(...) does not work

			// Add initialisation operations "newX()"
			var newOp = getNewOperationFor(eCls);
			if (newOp != null) {
				allNewOperations.add(newOp);
				apiSubCls.getEOperations().add(newOp);
			}

			// Add modification methods "withX_Feat(obj, featValParam)"
			var withOps = getWithOperationsFor(eCls);
			if (withOps != null)
				apiSubCls.getEOperations().addAll(withOps);

			clss.add(apiSubCls);
		}

		return clss;
	}

	private static final String rootInitialisationReferenceName = "rootInitialisation";
	private static final String subInitialisationsReferenceName = "subInitialisations";

	private static EReference getSubInitialisationsReference(EClass subInitialisationClsSuperType) {
		var subInitialisationsRef = EcoreFactory.eINSTANCE.createEReference();
		subInitialisationsRef.setChangeable(true);
		subInitialisationsRef.setContainment(true);
		subInitialisationsRef.setEType(subInitialisationClsSuperType);
		subInitialisationsRef.setName(subInitialisationsReferenceName);
		subInitialisationsRef.setLowerBound(0);
		subInitialisationsRef.setUpperBound(EReference.UNBOUNDED_MULTIPLICITY);
		return subInitialisationsRef;
	}

	private static EReference getRootInitialisationReference(EClass rootInitialisationCls) {
		var rootInitialisationRef = EcoreFactory.eINSTANCE.createEReference();
		rootInitialisationRef.setChangeable(true);
		rootInitialisationRef.setContainment(false);
		rootInitialisationRef.setEType(rootInitialisationCls);
		rootInitialisationRef.setName(rootInitialisationReferenceName);
		rootInitialisationRef.setLowerBound(1);
		rootInitialisationRef.setUpperBound(1);
		return rootInitialisationRef;
	}

	private static final String createdElementsReferenceName = "createdObjs";

	/**
	 * @return An EReference that is to reference the EObjects created via "newX()"
	 *         methods
	 */
	private static EReference getCreatedElementsReference(EClass eClsToInit) {
		var createdElementsRef = EcoreFactory.eINSTANCE.createEReference();
		createdElementsRef.setChangeable(true);
		createdElementsRef.setContainment(false);
		createdElementsRef.setEType(eClsToInit);
		createdElementsRef.setName(createdElementsReferenceName);
		createdElementsRef.setLowerBound(0);
		createdElementsRef.setUpperBound(EReference.UNBOUNDED_MULTIPLICITY);
		return createdElementsRef;
	}

	private static final String initialisedClassReferenceName = "initialisedClass";

	/**
	 * @return An EReference that is to reference the EClass of the instantiated
	 *         type
	 */
	private static EReference getInitialisedClassReference(EClass eClsToInit) {
		var initialisedClassRef = EcoreFactory.eINSTANCE.createEReference();
		initialisedClassRef.setChangeable(true);
		initialisedClassRef.setContainment(false);
		initialisedClassRef.setEType(eClsToInit.eClass());
		initialisedClassRef.setName(initialisedClassReferenceName);
		initialisedClassRef.setLowerBound(1);
		initialisedClassRef.setUpperBound(1);
		return initialisedClassRef;
	}

	private static boolean isConcrete(EClass elemToInit) {
		return !elemToInit.isAbstract() && !elemToInit.isInterface();
	}

	private static final String newOperationNamePrefix = "new";

	private static final String eOperationBodyKey = "body";

	private static EOperation getNewOperationFor(EClass elemToInit) {
		if (!isConcrete(elemToInit))
			return null;

		var op = EcoreFactory.eINSTANCE.createEOperation();
		var elemInstanceName = elemToInit.getInstanceClass().getSimpleName();
		op.setName(newOperationNamePrefix + elemInstanceName);
		op.setEType(elemToInit);

		// Add the method body
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(genModelURL);
		// TODO Add hooks to creation methods (?)
		var bodyValue = getNewOperationBodyFor(elemToInit);
		anno.getDetails().put(eOperationBodyKey, bodyValue);

		op.getEAnnotations().add(anno);
		return op;
	}

	private static String getNewOperationBodyFor(EClass elemToInit) {
		// TODO Add the created element to getCreatedElementsReference(elemToInit) and
		// return "this"

		return String.format("return %s.eINSTANCE.create%s();", getFactoryNameForEPackage(elemToInit.getEPackage()),
				elemToInit.getInstanceClass().getSimpleName());
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
				// Enable if only features with EObject values are to be considered
//				.filter((attr) -> EObject.class.isAssignableFrom(attr.getEType().getInstanceClass()))
				.filter((attr) -> attr.isChangeable()).toArray(EStructuralFeature[]::new)) {

			var op = EcoreFactory.eINSTANCE.createEOperation();
			var elemInstanceName = elemToInit.getInstanceClass().getSimpleName();
			var featName = feat.getName();
			op.setName(String.format("with%s_%s", elemInstanceName, featName));

			// Add obj param to operation, which is the element to initialise
			var objParam = EcoreFactory.eINSTANCE.createEParameter();
			var objParamName = "objToInit";
			objParam.setEType(elemToInit);
			objParam.setName(objParamName);
			objParam.setLowerBound(1);
			objParam.setUpperBound(1);
			op.getEParameters().add(objParam);

			// Add feature param to operation, which will be the new value of obj.feat
			var featValParam = EcoreFactory.eINSTANCE.createEParameter();
			var featValParamName = "";
			featValParam.setEType(feat.getEType());
			if (!feat.isMany()) {
				featValParamName = "newFeatVal";
			} else {
				featValParamName = "addToFeatVal";
			}
			featValParam.setName(featValParamName);
			featValParam.setLowerBound(1);
			featValParam.setUpperBound(1);
			op.getEParameters().add(featValParam);

			// Add the method body
			var anno = EcoreFactory.eINSTANCE.createEAnnotation();
			anno.setSource(genModelURL);
			var bodyKey = "body";
			var bodyValue = getWithOperationBodyFor(objParamName, featValParamName, feat);

			anno.getDetails().put(bodyKey, bodyValue);

			op.getEAnnotations().add(anno);

			ops.add(op);
		}
		return ops;
	}

	private static String getWithOperationBodyFor(String objParamName, String featureValParamName,
			EStructuralFeature feat) {
		/*
		 * TODO Add hooks to creation methods for validation and assertions
		 */

		if (!feat.isMany()) {
			return String.format("%s.eSet(%s.eClass().getEStructuralFeature(\"%s\"), %s);", objParamName, objParamName,
					feat.getName(), featureValParamName);
		} else {
			return String.format(
					"var val = %s.eGet(%s.eClass().getEStructuralFeature(\"%s\"));" + System.lineSeparator()
							+ "((EList) val).add(%s);",
					objParamName, objParamName, feat.getName(), featureValParamName);
		}
	}

	private static String getFactoryNameForEPackage(EPackage pac) {
		return pac.getEFactoryInstance().getClass().getName().replaceFirst(".impl", "").replace("Impl", "");
	}

	private EClass getFluentAPIClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();
		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(fluentAPIClassName);

		// TODO Add operations here

		return fluentAPICls;
	}
}
