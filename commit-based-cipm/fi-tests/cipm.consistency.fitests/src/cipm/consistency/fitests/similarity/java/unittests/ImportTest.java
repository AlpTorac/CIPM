package cipm.consistency.fitests.similarity.java.unittests;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.members.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportInitialiser;
import cipm.consistency.fitests.similarity.java.params.ImportTestParams;

public class ImportTest extends EObjectSimilarityTest {
//	private final String pacName = "pacName";
//	private Package pac;
//	
//	private final String cls1Name = "cls1Name";
	private ConcreteClassifier cls1;
//	
//	private final String m1Name = "m1Name";
//	private Member cls1Member;
//	
//	private final String cls2Name = "cls2Name";
	private ConcreteClassifier cls2;
//	
//	private final String m2Name = "m2Name";
//	private Member cls2Member;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ImportTest.class.getSimpleName());
		// TODO: Clean up
//		var clsInit = new ClassInitialiser();
//		var memberInit = new ClassMethodInitialiser();
//		var pacInit = new PackageInitialiser();
//		
//		this.pac = pacInit.instantiate();
//		pacInit.minimalInitialisation(this.pac);
//		pacInit.initialiseName(this.pac, pacName);
//		
//		this.cls1Member = memberInit.instantiate();
//		memberInit.minimalInitialisation(cls1Member);
//		memberInit.initialiseName(cls1Member, m1Name);
//		
//		this.cls2Member = memberInit.instantiate();
//		memberInit.minimalInitialisation(cls2Member);
//		memberInit.initialiseName(cls2Member, m2Name);
//		
//		this.cls1 = clsInit.instantiate();
//		clsInit.minimalInitialisation(cls1);
//		clsInit.initialiseName(this.cls1, this.cls1Name);
//		clsInit.addMember(this.cls1, cls1Member);
//		clsInit.setPackage(this.cls1, pac);
//		
//		this.cls2 = clsInit.instantiate();
//		clsInit.minimalInitialisation(this.cls2);
//		clsInit.initialiseName(this.cls2, this.cls2Name);
//		clsInit.addMember(cls2, cls2Member);
//		clsInit.setPackage(cls2, pac);
		
		// TODO: Extract JavaClasspath logic
		JavaClasspath.get().registerStdLib();
		this.cls1 = JavaClasspath.get().getConcreteClassifier(String.class.getName());
		this.cls2 = JavaClasspath.get().getConcreteClassifier(Integer.class.getName());
		
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
	
	@Disabled("Only Classifier and StaticMember Imports are being compared")
	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testDifferentClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameClassifier");
		
		var objOne = this.initElement(initialiser, this.cls1);
		var objTwo = this.initElement(initialiser, this.cls2);
		
		this.differentX(objOne, objTwo);
	}
}
