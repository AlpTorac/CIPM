package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.javaFluentAPI.JavaFluentAPIFactory;

public class FluentAPIContainmentTest {
	/**
	 * class cls1 {
	 * 
	 * class cls2 extends cls1 {
	 * 
	 * }
	 * 
	 * }
	 */
	@Test
	public void apiTest_NestAndReference() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

		var outerClsName = "outer";
		var innerClsName = "inner";

		var outerCls = api.newClass().withName(outerClsName).createNow();
		var innerCls = api.newClass().withName(innerClsName)
				.withExtends(api.newClassifierReference().withTarget(outerCls).createNow()).createNow();
		api.modifyClass(outerCls).withAddedMembers(innerCls).drop();

		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(innerCls, outerCls.getMembers().get(0));
		Assertions.assertEquals(0, outerCls.getDefaultMembers().size());
		Assertions.assertNull(outerCls.eContainer());

		Assertions.assertEquals(0, innerCls.getMembers().size());
		Assertions.assertEquals(0, innerCls.getDefaultMembers().size());
		Assertions.assertEquals(outerCls, innerCls.getExtends().getPureClassifierReference().getTarget());
		Assertions.assertEquals(outerCls, innerCls.eContainer());
	}

	@Test
	public void apiTest_Nesting() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

		var innerClsName = "inner";
		var outerClsName = "outer";

		var innerCls = api.newClass().withName(innerClsName).createNow();
		var outerCls = api.newClass().withName(outerClsName).withAddedMembers(innerCls).createNow();

		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(innerCls, outerCls.getMembers().get(0));
		Assertions.assertEquals(0, outerCls.getDefaultMembers().size());
		Assertions.assertNull(outerCls.eContainer());

		Assertions.assertEquals(0, innerCls.getMembers().size());
		Assertions.assertEquals(0, innerCls.getDefaultMembers().size());
		Assertions.assertEquals(outerCls, innerCls.eContainer());
	}

	@Test
	public void apiTest_TwoContainmentFeaturesWithSameType() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

		var arrDimBefore = api.newArrayDimension().createNow();
		var arrDimAfter = api.newArrayDimension().createNow();
		var ptr = api.newPrimitiveTypeReference().withAddedArrayDimensionsBefore(arrDimBefore)
				.withAddedArrayDimensionsAfter(arrDimAfter).createNow();

		Assertions.assertEquals(1, ptr.getArrayDimensionsBefore().size());
		Assertions.assertEquals(arrDimBefore, ptr.getArrayDimensionsBefore().get(0));
		Assertions.assertEquals(ptr, arrDimBefore.eContainer());

		Assertions.assertEquals(1, ptr.getArrayDimensionsAfter().size());
		Assertions.assertEquals(arrDimAfter, ptr.getArrayDimensionsAfter().get(0));
		Assertions.assertEquals(ptr, arrDimAfter.eContainer());
	}
}
