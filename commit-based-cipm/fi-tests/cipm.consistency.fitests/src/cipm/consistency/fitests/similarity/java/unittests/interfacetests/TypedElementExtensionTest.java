package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypedElementExtensionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class TypedElementExtensionTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected TypedElementExtension initElement(ITypedElementExtensionInitialiser init, TypeReference tref) {
		TypedElementExtension result = init.instantiate();
		init.minimalInitialisation(result);
		init.addActualTarget(result, tref);	
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(TypedElementExtensionTestParam.class)
	public void testActualTarget(ITypedElementExtensionInitialiser init) {
		this.setResourceFileTestIdentifier("testActualTarget");
		
		var objOne = this.initElement(init, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(init, this.createMinimalClsRef("cls2"));
		
		this.testX(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}
}
