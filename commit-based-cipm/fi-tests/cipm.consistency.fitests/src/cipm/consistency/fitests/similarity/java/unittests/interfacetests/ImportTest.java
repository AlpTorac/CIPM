package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.java.unittests.UsesImports;

public class ImportTest extends EObjectSimilarityTest implements UsesImports {
	private ConcreteClassifier cls1;
	private ConcreteClassifier cls2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.cls1 = this.createMinimalClass("cls1Name");
		this.cls2 = this.createMinimalClass("cls2Name");

		super.setUp();
	}
	
	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testClassifier");
		
		var objOne = this.createMinimalImport(initialiser, this.cls1);
		var objTwo = this.createMinimalImport(initialiser, this.cls2);
		
		this.testX(objOne, objTwo, false);
	}
}
