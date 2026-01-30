package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

/**
 * Tests the construction of models, where model elements of the same type are
 * nested within one another. Also tests construction of model elements with
 * multiple EReferences using values of the same type.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIContainmentTest extends AbstractFluentAPITest {
	/**
	 * Ensures that the following construction is possible and works as intended:
	 * 
	 * class outer {
	 * 
	 * class inner {}
	 * 
	 * }
	 */
	@Test
	public void testNesting() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

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

	/**
	 * Ensures that the following construction is possible and works as intended:
	 * 
	 * class cls1 {
	 * 
	 * class cls2 extends cls1 {}
	 * 
	 * }
	 */
	@Test
	public void testNestingAndReferencing() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

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

	/**
	 * Ensures setting values EReferences using the same value type works as
	 * intended, for instance:
	 * 
	 * <p>
	 * PrimitiveTypeReference has the EReferences ArrayDimensionsBefore (ADB) and
	 * ArrayDimensionsAfter (ADA), which consider ArrayDimension instances. Assuming
	 * AD1 and AD2 are separate ArrayDimension instances, setting ADB = AD1 and ADA
	 * = AD2 via Fluent API should not mix up ADB and ADA.
	 */
	@Test
	public void testTwoContainmentFeaturesWithSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

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
