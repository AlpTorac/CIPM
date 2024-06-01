package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IElementReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationInstances;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationParameters;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class ElementReferenceTest extends EObjectSimilarityTest implements UsesConcreteClassifiers,
UsesAnnotationInstances, UsesAnnotationParameters {
	protected ElementReference initElement(IElementReferenceInitialiser init,
			ReferenceableElement target, ReferenceableElement cTarget) {
		ElementReference result = init.instantiate();
		init.minimalInitialisation(result);
		init.setTarget(result, target);
		init.setContainedTarget(result, cTarget);
		
		var aiInit = new AnnotationInstanceInitialiser();
		var ai = this.createMinimalAI(new String[] {"nsai"}, "an");
		aiInit.setAnnotationParameter(ai, this.createSingleAnnoParam(result));
		
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testTarget(IElementReferenceInitialiser init) {
		this.setResourceFileTestIdentifier("testTarget");
		
		var cls1 = this.createMinimalClassWithPac("cls1", new String[] {"ns1"});
		var cls2 = this.createMinimalClassWithPac("cls2", new String[] {"ns2"});
		
		var objOne = this.initElement(init, cls1.getPackage(), cls1);
		var objTwo = this.initElement(init, cls2.getPackage(), cls2);
		
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