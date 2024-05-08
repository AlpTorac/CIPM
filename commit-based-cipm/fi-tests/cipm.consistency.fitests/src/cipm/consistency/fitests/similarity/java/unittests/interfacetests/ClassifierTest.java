package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.PackageImport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IClassifierInitialiser;

public class ClassifierTest extends EObjectSimilarityTest {
	private final String cls1Name = "cls1Name";
	private ConcreteClassifier cls1;
	private Import imp1;
	
	private final String cls2Name = "cls2Name";
	private ConcreteClassifier cls2;
	private Import imp2;
	
	private final String[] pac1nss = new String[] {"ns1", "ns2", "ns3"};
	private Package pac1;
	private PackageImport pImp1;
	
	private final String[] pac2nss = new String[] {"ns1", "ns4"};
	private Package pac2;
	private PackageImport pImp2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ClassifierTest.class.getSimpleName());
		
		var clsInit = new ClassInitialiser();
		var impInit = new ClassifierImportInitialiser();
		
		var pacInit = new PackageInitialiser();
		var pImpInit = new PackageImportInitialiser();
		
		this.pac1 = pacInit.instantiate();
		pacInit.minimalInitialisation(pac1);
		pacInit.addNamespaces(pac1, pac1nss);
		
		this.pac2 = pacInit.instantiate();
		pacInit.minimalInitialisation(pac2);
		pacInit.addNamespaces(pac2, pac2nss);
		
		this.pImp1 = pImpInit.instantiate();
		pImpInit.minimalInitialisation(pImp1);
		pImpInit.addNamespaces(pImp1, pac1nss);
		
		this.pImp2 = pImpInit.instantiate();
		pImpInit.minimalInitialisation(pImp2);
		pImpInit.addNamespaces(pImp2, pac2nss);
		
		this.cls1 = clsInit.instantiate();
		clsInit.minimalInitialisation(cls1);
		clsInit.initialiseName(cls1, cls1Name);
		
		this.cls2 = clsInit.instantiate();
		clsInit.minimalInitialisation(cls2);
		clsInit.initialiseName(cls2, cls2Name);
		
		this.imp1 = impInit.instantiate();
		impInit.minimalInitialisation(imp1);
		impInit.setClassifier(imp1, cls1);
		
		this.imp2 = impInit.instantiate();
		impInit.minimalInitialisation(imp2);
		impInit.setClassifier(imp2, cls2);
		
		super.setUp();
	}
	
	protected Classifier initElement(IClassifierInitialiser initialiser,
			Import[] imps, PackageImport[] pImps) {
		Classifier result = initialiser.instantiate();
		initialiser.minimalInitialisationWithContainer(result);
		
		if (imps != null) {
			for (var s : imps) {
				initialiser.addImport(result, s);
			}
		}
		
		if (pImps != null) {
			for (var s : pImps) {
				initialiser.addPackageImport(result, s);
			}
		}
		
		return result;
	}
	
	// TODO: Figure out how to add imports with import strings
	
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testImports");
		
		var objOne = this.initElement(initialiser,
				new Import[] {this.imp1, this.imp2}, null);
		var objTwo = this.initElement(initialiser,
				new Import[] {this.imp2}, null);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, initialiser, false);
	}
	
	@ParameterizedTest
	@ArgumentsSource(ClassifierTestParams.class)
	public void testPackageImports(IClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testPackageImports");
		
		var objOne = this.initElement(initialiser,
				null, new PackageImport[] {this.pImp1, this.pImp2});
		var objTwo = this.initElement(initialiser,
				null, new PackageImport[] {this.pImp2});
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, initialiser, false);
	}
}
