package cipm.consistency.fluentapi.java.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.extensions.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.extensions.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.java.api.ApiFactory;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

/**
 * A test class containing tests for initialisation classes within the fluent
 * api model.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIInitTest extends AbstractFluentAPITest {
	/**
	 * Checks whether init.toAPI() works as intended.
	 */
	@Test
	public void testInit_ToAPI() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		Assertions.assertSame(api, api.newAdditionalField().toAPI());
	}

	/**
	 * Checks whether init.reset() works as intended.
	 */
	@Test
	public void testInit_Reset() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var clsInit = api.newClass();
		Assertions.assertNotNull(clsInit.getCurrentElement());

		clsInit.reset();
		Assertions.assertNull(clsInit.getCurrentElement());

		// Ensure that reset() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	/**
	 * Checks whether init.createNow() works as intended.
	 */
	@Test
	public void testInit_CreateNow() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	/**
	 * Checks whether init.dropInitialisation() works as intended.
	 */
	@Test
	public void testInit_DropInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var clsInit = api.newClass();

		// Ensure that clsInit is added to FluentAPIInitialisationStorage
		var inits = FluentAPIInitialisationStorage.getOngoingInitialisations();
		Assertions.assertEquals(1, inits.size());
		Assertions.assertSame(clsInit, inits.get(0));

		clsInit.dropInitialisation();
		// Re-retrieve ongoing initialisations
		inits = FluentAPIInitialisationStorage.getOngoingInitialisations();
		// Ensure that clsInit is removed from FluentAPIInitialisationStorage
		Assertions.assertEquals(0, inits.size());
	}

	/**
	 * Checks whether init.markCurrentElement(key) works as intended.
	 */
	@Test
	public void testInit_MarkCurrentElement() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key = new Object();

		var clsInit = api.newClass();
		var cls = clsInit.markCurrentElement(key).getCurrentElement();

		Assertions.assertTrue(FluentAPIMarkExtension.hasMark(key));
		Assertions.assertSame(cls, FluentAPIMarkExtension.getMarked(key));
	}

	/**
	 * Checks whether init.unmarkCurrentElement(key) works as intended.
	 */
	@Test
	public void testInit_UnmarkCurrentElement() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key = new Object();

		var clsInit = api.newClass();
		var cls = clsInit.markCurrentElement(key).getCurrentElement();

		Assertions.assertTrue(FluentAPIMarkExtension.hasMark(key));
		Assertions.assertSame(cls, FluentAPIMarkExtension.getMarked(key));

		clsInit.unmarkCurrentElement(key);

		Assertions.assertFalse(FluentAPIMarkExtension.hasMark(key));
		Assertions.assertNull(FluentAPIMarkExtension.getMarked(key));
	}

	/**
	 * Checks whether init.waitForMark(singleKey, task) works as intended.
	 */
	@Test
	public void testInit_WaitForMark_SingleKey() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var clsOneName = "clsOne";
		var clsTwoName = "clsTwo";
		var clsOneKey = new Object();
		var clsTwoKey = new Object();

		// Goal:
		// class clsOne extends clsTwo {}
		// class clsTwo {}

		var clsOne =
				// Create clsOne (in isolation)
				api.newClass().withName(clsOneName).markCurrentElement(clsOneKey)
						// Have clsOne extend clsTwo, once clsTwo exists and is marked with clsTwoKey
						.waitForMark(clsTwoKey,
								() -> api.continueMarkedClass(clsOneKey).withExtends(api.getMarkedClass(clsTwoKey)))
						// Create clsTwo and mark it
						.toAPI().newClass().withName(clsTwoName).markCurrentElement(clsTwoKey)
						// Swap to clsOne and return it
						.toAPI().continueMarkedClass(clsOneKey).createNow();

		Assertions.assertEquals(clsOneName, clsOne.getName());
		Assertions.assertEquals(clsTwoName, clsOne.getExtends().getPureClassifierReference().getTarget().getName());
	}

	/**
	 * Checks whether init.waitForMark(keyArray, task) works as intended.
	 */
	@Test
	public void testInit_WaitForMark_KeyArray() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var clsOneName = "clsOne";
		var clsTwoName = "clsTwo";
		var clsThreeName = "clsThree";
		var clsOneKey = new Object();
		var clsTwoKey = new Object();
		var clsThreeKey = new Object();
		var clsMemberKeys = new Object[] { clsTwoKey, clsThreeKey };

		// Goal:
		// class clsOne {class clsTwo {} class clsThree {}}

		var clsOne =
				// Create clsOne (in isolation)
				api.newClass().withName(clsOneName).markCurrentElement(clsOneKey)
						// Add clsTwo and clsThree to clsOne once both of them are present and marked
						.waitForMark(clsMemberKeys,
								() -> api.continueMarkedClass(clsOneKey).withAddedMembers(
										List.of(api.getMarkedClass(clsTwoKey), api.getMarkedClass(clsThreeKey))))
						// Create clsTwo and mark it
						.toAPI().newClass().withName(clsTwoName).markCurrentElement(clsTwoKey)
						// Create clsThree and mark it
						.toAPI().newClass().withName(clsThreeName).markCurrentElement(clsThreeKey)
						// Swap to clsOne and return it
						.toAPI().continueMarkedClass(clsOneKey).createNow();

		Assertions.assertEquals(clsOneName, clsOne.getName());
		Assertions.assertEquals(2, clsOne.getMembers().size());
		Assertions.assertEquals(clsTwoName, clsOne.getMembers().get(0).getName());
		Assertions.assertEquals(clsThreeName, clsOne.getMembers().get(1).getName());
	}

	/**
	 * Checks whether init.waitForMark(keyCollection, task) works as intended.
	 */
	@Test
	public void testInit_WaitForMark_KeyCollection() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var clsOneName = "clsOne";
		var clsTwoName = "clsTwo";
		var clsThreeName = "clsThree";
		var clsOneKey = new Object();
		var clsTwoKey = new Object();
		var clsThreeKey = new Object();
		var clsMemberKeys = List.of(clsTwoKey, clsThreeKey);

		// Goal:
		// class clsOne {class clsTwo {} class clsThree {}}

		var clsOne =
				// Create clsOne (in isolation)
				api.newClass().withName(clsOneName).markCurrentElement(clsOneKey)
						// Add clsTwo and clsThree to clsOne once both of them are present and marked
						.waitForMark(clsMemberKeys,
								() -> api.continueMarkedClass(clsOneKey).withAddedMembers(
										List.of(api.getMarkedClass(clsTwoKey), api.getMarkedClass(clsThreeKey))))
						// Create clsTwo and mark it
						.toAPI().newClass().withName(clsTwoName).markCurrentElement(clsTwoKey)
						// Create clsThree and mark it
						.toAPI().newClass().withName(clsThreeName).markCurrentElement(clsThreeKey)
						// Swap to clsOne and return it
						.toAPI().continueMarkedClass(clsOneKey).createNow();

		Assertions.assertEquals(clsOneName, clsOne.getName());
		Assertions.assertEquals(2, clsOne.getMembers().size());
		Assertions.assertEquals(clsTwoName, clsOne.getMembers().get(0).getName());
		Assertions.assertEquals(clsThreeName, clsOne.getMembers().get(1).getName());
	}

	/**
	 * Checks whether init.withX(...) works as intended.
	 */
	@Test
	public void testInit_With() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var name = "cuName";
		var cu = api.newCompilationUnit().withName(name).createNow();
		Assertions.assertEquals(name, cu.getName());
	}

	/**
	 * Checks whether init.withoutX(...) works as intended.
	 */
	@Test
	public void testInit_Without() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var name = "cuName";
		var cu = api.newCompilationUnit().withName(name).withoutName().createNow();
		Assertions.assertNull(cu.getName());
	}

	/**
	 * Checks whether init.withRemovedX(val) works as intended.
	 */
	@Test
	public void testInit_WithRemoved_SingularParameter() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = ns1;

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(2, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
		Assertions.assertEquals(ns3, cu.getNamespaces().get(1));
	}

	/**
	 * Checks whether init.withRemovedX(valArray) works as intended.
	 */
	@Test
	public void testInit_WithRemoved_ArrayParameter() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = new String[] { ns1, ns3 };

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
	}

	/**
	 * Checks whether init.withRemovedX(valCollection) works as intended.
	 */
	@Test
	public void testInit_WithRemoved_CollectionParameter() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = List.of(ns1, ns3);

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
	}

	/**
	 * Checks whether init.clean() works as intended.
	 */
	@Test
	public void testInit_Clean() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).cleanNamespaces().createNow();
		Assertions.assertEquals(0, cu.getNamespaces().size());
	}

	/**
	 * Checks whether init.withAddedX(val) works as intended.
	 */
	@Test
	public void testInit_WithAdded_SingularParameter() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var ns = "ns";
		var cu = api.newCompilationUnit().withAddedNamespaces(ns).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns, cu.getNamespaces().get(0));
	}

	/**
	 * Checks whether init.withAddedX(valArray) works as intended.
	 */
	@Test
	public void testInit_WithAdded_ArrayParameter() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var nss = new String[] { "ns1", "ns2" };
		var cu = api.newCompilationUnit().withAddedNamespaces(nss).createNow();
		Assertions.assertArrayEquals(nss, cu.getNamespaces().toArray(String[]::new));
	}

	/**
	 * Checks whether init.withAddedX(valCollection) works as intended.
	 */
	@Test
	public void testInit_WithAdded_CollectionParameter() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var nss = List.of("ns1", "ns2");
		var cu = api.newCompilationUnit().withAddedNamespaces(nss).createNow();
		Assertions.assertArrayEquals(nss.toArray(String[]::new), cu.getNamespaces().toArray(String[]::new));
	}
}
