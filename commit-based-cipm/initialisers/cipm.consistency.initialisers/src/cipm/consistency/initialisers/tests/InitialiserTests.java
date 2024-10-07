package cipm.consistency.initialisers.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjFour;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjFourInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjOne;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjOneInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjThree;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjThreeInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjTwo;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.DummyObjTwoInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.IDummyObjFourInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.IDummyObjOneInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.IDummyObjThreeInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.flathierarchy.IDummyObjTwoInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyAlternateInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyNonTerminalObj;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyNonTerminalObjInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyTerminalObj;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyTerminalObjInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyTopLevelObj;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.DummyTopLevelObjInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy.IDummyAlternateInitialiser;

/**
 * Contains tests for (static/default) methods implemented in
 * {@link IInitialiser}
 * 
 * @author Alp Torac Genc
 */
public class InitialiserTests {
	/**
	 * Checks whether {@link IInitialiser#declaresModificationMethods(Class)}
	 * circumvents null pointer exceptions.
	 */
	@Test
	public void test_DeclaresModificationMethods_NullClass() {
		Class<? extends IInitialiser> cls = null;
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(cls));
	}

	/**
	 * Checks whether {@link IInitialiser#declaresModificationMethods(IInitialiser)}
	 * circumvents null pointer exceptions.
	 */
	@Test
	public void test_DeclaresModificationMethods_NullInitialiser() {
		IInitialiser init = null;
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(init));
	}

	/**
	 * Check whether a modification method implemented only in the interface of an
	 * initialiser can be found.
	 */
	@Test
	public void test_DeclaresModificationMethods_InInterface() {
		var init = new DummyObjOneInitialiser();
		Assertions.assertFalse(init.declaresModificationMethods());
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(init));
		Assertions.assertTrue(IInitialiser.declaresModificationMethods(IDummyObjOneInitialiser.class));
	}

	/**
	 * Check whether a modification method implemented only in the concrete class of
	 * an initialiser can be found.
	 */
	@Test
	public void test_DeclaresModificationMethods_InClass() {
		var init = new DummyObjTwoInitialiser();
		Assertions.assertTrue(init.declaresModificationMethods());
		Assertions.assertTrue(IInitialiser.declaresModificationMethods(init));
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(IDummyObjTwoInitialiser.class));
	}

	/**
	 * Check whether initialisers with only an overridden instantiate() method are
	 * detected as initialisers without modification methods.
	 */
	@Test
	public void test_DeclaresModificationMethods_NoAdditionalMethods() {
		var init = new DummyObjThreeInitialiser();
		Assertions.assertFalse(init.declaresModificationMethods());
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(init));
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(IDummyObjThreeInitialiser.class));
	}

	/**
	 * Check whether initialisers with no additional modification methods can be
	 * detected, even if they do have other methods.
	 */
	@Test
	public void test_DeclaresModificationMethods_NoModificationMethods() {
		var init = new DummyObjFourInitialiser();
		Assertions.assertFalse(init.declaresModificationMethods());
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(init));
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(IDummyObjFourInitialiser.class));
	}

	/**
	 * Check whether initialisers with no direct modification methods are detected,
	 * i.e. initialisers that do inherit modification methods yet do not implement
	 * any in their own body.
	 */
	@Test
	public void test_DeclaresModificationMethods_NoDirectModificationMethods() {
		var init = new DummyAlternateInitialiser();
		Assertions.assertFalse(init.declaresModificationMethods());
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(init));
		Assertions.assertFalse(IInitialiser.declaresModificationMethods(IDummyAlternateInitialiser.class));
	}

	/**
	 * Check whether an initialiser, whose name does not contain the name of the
	 * object class it is supposed to instantiate, is still detected as its
	 * initialiser, if it has a proper instantiation method.
	 */
	@Test
	public void test_IsInitialiserFor_NameMismatch() {
		Assertions.assertTrue(new DummyAlternateInitialiser().isInitialiserFor(DummyNonTerminalObj.class));
		Assertions
				.assertTrue(IInitialiser.isInitialiserFor(IDummyAlternateInitialiser.class, DummyNonTerminalObj.class));
	}

	/**
	 * Checks whether {@link IInitialiser#isInitialiserFor(Class, Class)}
	 * circumvents null pointer exceptions, which could be caused by the first
	 * parameter being null.
	 */
	@Test
	public void test_IsInitialiserFor_NullInitialiserClass() {
		Class<? extends IInitialiser> initCls = null;
		Assertions.assertFalse(IInitialiser.isInitialiserFor(initCls, DummyNonTerminalObj.class));
	}

	/**
	 * Checks whether {@link IInitialiser#isInitialiserFor(IInitialiser, Class)}
	 * circumvents null pointer exceptions, which could be caused by the first
	 * parameter being null.
	 */
	@Test
	public void test_IsInitialiserFor_NullInitialiser() {
		IInitialiser init = null;
		Assertions.assertFalse(IInitialiser.isInitialiserFor(init, DummyNonTerminalObj.class));
	}

	/**
	 * Checks whether {@link IInitialiser#isInitialiserFor(Class, Class)} and
	 * {@link IInitialiser#isInitialiserFor(IInitialiser, Class)} circumvents null
	 * pointer exceptions, which could be caused by the second parameter being null.
	 */
	@Test
	public void test_IsInitialiserFor_NullClass() {
		Class<?> cls = null;

		Assertions.assertFalse(IInitialiser.isInitialiserFor(new DummyObjOneInitialiser(), cls));
		Assertions.assertFalse(IInitialiser.isInitialiserFor(DummyObjOneInitialiser.class, cls));
	}

	/**
	 * Check whether initialisers that are meant to instantiate certain objects can
	 * be detected, where the said objects (and their initialisers) have no type
	 * hierarchy.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test_IsInitialiserFor_NoTypeHierarchy() {
		var objClss = new Class[] { DummyObjOne.class, DummyObjTwo.class, DummyObjThree.class, DummyObjFour.class };
		var initInterfaceClss = new Class[] { IDummyObjOneInitialiser.class, IDummyObjTwoInitialiser.class,
				IDummyObjThreeInitialiser.class, IDummyObjFourInitialiser.class };
		var initClss = new Class[] { DummyObjOneInitialiser.class, DummyObjTwoInitialiser.class,
				DummyObjThreeInitialiser.class, DummyObjFourInitialiser.class };
		var inits = new IInitialiser[] { new DummyObjOneInitialiser(), new DummyObjTwoInitialiser(),
				new DummyObjThreeInitialiser(), new DummyObjFourInitialiser() };

		// Test over all the classes given above, in order to make sure that
		// other methods implemented in the classes/interfaces do not cause issues
		for (int i = 0; i < objClss.length; i++) {
			var cInitInterfaceCls = initInterfaceClss[i];
			var cInitClss = initClss[i];
			var cInit = inits[i];

			for (int j = 0; j < objClss.length; j++) {
				var cObjCls = objClss[j];

				Assertions.assertEquals(i == j, cInit.isInitialiserFor(cObjCls));
				Assertions.assertEquals(i == j, IInitialiser.isInitialiserFor(cInit, cObjCls));
				Assertions.assertEquals(i == j, IInitialiser.isInitialiserFor(cInitClss, cObjCls));
				Assertions.assertEquals(i == j, IInitialiser.isInitialiserFor(cInitInterfaceCls, cObjCls));
			}
		}
	}

	/**
	 * Check whether initialisers within a type hierarchy are correctly matched to
	 * the object class they are supposed to instantiate.
	 */
	@Test
	public void test_IsInitialiserFor_WithTypeHierarchy() {
		var tlInit = new DummyTopLevelObjInitialiser();
		var ntInit = new DummyNonTerminalObjInitialiser();
		var tInit = new DummyTerminalObjInitialiser();

		Assertions.assertFalse(tlInit.isInitialiserFor(DummyTerminalObj.class));
		Assertions.assertFalse(tlInit.isInitialiserFor(DummyNonTerminalObj.class));
		Assertions.assertTrue(tlInit.isInitialiserFor(DummyTopLevelObj.class));

		Assertions.assertFalse(ntInit.isInitialiserFor(DummyTerminalObj.class));
		Assertions.assertTrue(ntInit.isInitialiserFor(DummyNonTerminalObj.class));
		Assertions.assertFalse(ntInit.isInitialiserFor(DummyTopLevelObj.class));

		Assertions.assertTrue(tInit.isInitialiserFor(DummyTerminalObj.class));
		Assertions.assertFalse(tInit.isInitialiserFor(DummyNonTerminalObj.class));
		Assertions.assertFalse(tInit.isInitialiserFor(DummyTopLevelObj.class));

	}
}
