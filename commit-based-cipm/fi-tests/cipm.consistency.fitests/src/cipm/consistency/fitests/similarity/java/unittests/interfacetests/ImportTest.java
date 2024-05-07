package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportInitialiser;

public class ImportTest extends EObjectSimilarityTest {
//	private final String[] pac1nss = new String[] {"ns1", "ns2", "ns3"};
//	private Package pac1;
//	
//	private final String[] pac2nss = new String[] {"ns1", "ns4"};
//	private Package pac2;
//	
	private final String cls1Name = "cls1Name";
	private ConcreteClassifier cls1;
//	
//	private final String m1Name = "m1Name";
//	private Member cls1Member;
//	
	private final String cls2Name = "cls2Name";
	private ConcreteClassifier cls2;
//	
//	private final String m2Name = "m2Name";
//	private Member cls2Member;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ImportTest.class.getSimpleName());
		
//		var pacInit = new PackageInitialiser();
//		var memberInit = new ClassMethodInitialiser();
		var clsInit = new ClassInitialiser();
		
//		this.pac1 = pacInit.instantiate();
//		pacInit.minimalInitialisation(pac1);
//		pacInit.initialiseNamespaces(pac1, pac1nss);
//		
//		this.pac2 = pacInit.instantiate();
//		pacInit.minimalInitialisation(pac2);
//		pacInit.initialiseNamespaces(pac2, pac2nss);
//		
//		this.cls1Member = memberInit.instantiate();
//		memberInit.minimalInitialisation(cls1Member);
//		memberInit.initialiseName(cls1Member, m1Name);
//		
//		this.cls2Member = memberInit.instantiate();
//		memberInit.minimalInitialisation(cls2Member);
//		memberInit.initialiseName(cls2Member, m2Name);
		
		this.cls1 = clsInit.instantiate();
		clsInit.minimalInitialisation(cls1);
		clsInit.initialiseName(cls1, cls1Name);
//		clsInit.addMember(cls1, cls1Member);
//		clsInit.setPackage(cls1, pac1);
		
		this.cls2 = clsInit.instantiate();
		clsInit.minimalInitialisation(cls2);
		clsInit.initialiseName(cls2, cls2Name);
//		clsInit.addMember(cls2, cls2Member);
//		clsInit.setPackage(cls2, pac2);
		
		super.setUp();
	}
	
	protected Import initElement(IImportInitialiser initialiser, ConcreteClassifier cls) {
		Import result = initialiser.instantiate();
		
		initialiser.minimalInitialisation(result);
		initialiser.setClassifier(result, cls);
		
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testSameClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameClassifier");
		
		var objOne = this.initElement(initialiser, this.cls1);
		
		this.sameX(objOne, initialiser);
	}
	
//	@Disabled("Only Classifier and StaticMember Imports are being compared")
	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testDifferentClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameClassifier");
		
		var objOne = this.initElement(initialiser, this.cls1);
		var objTwo = this.initElement(initialiser, this.cls2);
		
		this.differentX(objOne, objTwo);
	}
}
