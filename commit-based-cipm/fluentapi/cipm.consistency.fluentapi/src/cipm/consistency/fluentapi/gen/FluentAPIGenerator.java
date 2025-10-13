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

import cipm.consistency.fluentapi.gen.java.JavaPackageUtil;

public class FluentAPIGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String fluentAPIClassName = "FluentEObjectAPI";
	private static final String fluentAPIRootPacName = "api";

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

		res.getContents().add(this.generateRootPackage());
		try {
			res.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}

	private EPackage generateRootPackage() {
		var javaPac = EcoreFactory.eINSTANCE.createEPackage();
		javaPac.setName(fluentAPIRootPacName);
		javaPac.setNsPrefix(fluentAPIRootPacName);
		javaPac.setNsURI(URI.createFileURI(fluentAPIRootPacName).toString());

		// Add the super type for sub initialisation classes
		var fluentAPISubClssSuperType = new AbstractInitialisationGenerator().generateFluentAPISuperType();
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
			var clss = new XInitialisationGenerator().getFluentAPIClassesFor(javaSubPac);
			if (clss != null)
				fluentAPISubClss.addAll(clss);
		}

		return fluentAPISubClss;
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

	private EClass getFluentAPIClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();
		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(fluentAPIClassName);

		// TODO Add operations here

		return fluentAPICls;
	}
}
