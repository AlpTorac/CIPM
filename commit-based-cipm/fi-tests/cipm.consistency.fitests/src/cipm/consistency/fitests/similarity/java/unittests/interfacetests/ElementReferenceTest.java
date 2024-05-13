package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IElementReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class ElementReferenceTest extends EObjectSimilarityTest implements UsesConcreteClassifiers {
	protected ElementReference initElement(IElementReferenceInitialiser init, ReferenceableElement cTarget,
			ReferenceableElement target) {
		ElementReference result = init.instantiate();
		init.minimalInitialisation(result);
		init.setTarget(result, target);
		init.setContainedTarget(result, target);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testTarget(IElementReferenceInitialiser init) {
		this.setResourceFileTestIdentifier("testTarget");
		
		var objOne = this.initElement(init, this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(init, this.createMinimalClass("cls2"), null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testContainedTarget(IElementReferenceInitialiser init) {
		this.setResourceFileTestIdentifier("testContainedTarget");
		
		var objOne = this.initElement(init, null, this.createMinimalClassWithPac("cls1", new String[] {"ns1"}));
		var objTwo = this.initElement(init, null, this.createMinimalClassWithPac("cls2", new String[] {"ns2"}));
		
		this.testX(objOne, objTwo, false);
	}
}
