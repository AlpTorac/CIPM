package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIJavaOverloadsTest {
	@Test
	public void withTypeReferenceOverloadTest_SingleValued() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = api.createNewClass();

		var clsMet = api.newClassMethod().withTypeReference(cls).createNow();
		Assertions.assertSame(cls, clsMet.getTypeReference().getPureClassifierReference().getTarget());
	}

	@Test
	public void withTypeReferenceOverloadTest_ManyValued() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsOne = api.createNewClass();
		var clsTwo = api.createNewClass();

		var ai = api.newAnnotationInstance().withAddedActualTargets(clsOne).withAddedActualTargets(clsTwo).createNow();

		Assertions.assertSame(clsOne, ai.getActualTargets().get(0).getPureClassifierReference().getTarget());
		Assertions.assertSame(clsTwo, ai.getActualTargets().get(1).getPureClassifierReference().getTarget());
	}
}
