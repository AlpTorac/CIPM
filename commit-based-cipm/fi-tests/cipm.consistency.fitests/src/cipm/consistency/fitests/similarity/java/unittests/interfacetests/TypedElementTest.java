package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypedElementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class TypedElementTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected TypedElement initElement(ITypedElementInitialiser init, TypeReference tref) {
		TypedElement result = init.instantiate();
		init.minimalInitialisation(result);
		init.setTypeReference(result, tref);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(TypedElementTestParams.class)
	public void testTypeReference(ITypedElementInitialiser init) {
		this.setResourceFileTestIdentifier("testTypeReference");
		
		var objOne = this.initElement(init, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(init, this.createMinimalClsRef("cls2"));
		
		this.testX(objOne, objTwo, false);
	}
}