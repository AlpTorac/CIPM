package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class TypeReferenceTest extends EObjectSimilarityTest implements UsesTypeReferences {
	@ParameterizedTest
	@ArgumentsSource(TypeReferenceTestParams.class)
	public void testTarget(ITypeReferenceInitialiser init) {
		this.setResourceFileTestIdentifier("testTarget");
		
		var objOne = this.createMinimalClsRef(init, "cls1");
		var objTwo = this.createMinimalClsRef(init, "cls2");
		
		this.testX(objOne, objTwo, false);
	}
}
