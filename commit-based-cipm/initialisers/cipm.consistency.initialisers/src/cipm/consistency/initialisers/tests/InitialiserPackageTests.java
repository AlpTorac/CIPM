package cipm.consistency.initialisers.tests;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.initialisers.tests.dummy.packages.DummyAggregateInitialiserPackageOne;
import cipm.consistency.initialisers.tests.dummy.packages.DummyAggregateInitialiserPackageTwo;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserA;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserB;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserC;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserD;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserE;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserPackageA;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserPackageB;
import cipm.consistency.initialisers.tests.dummy.packages.DummyInitialiserPackageCD;
import cipm.consistency.initialisers.tests.dummy.packages.DummyObjA;
import cipm.consistency.initialisers.tests.dummy.packages.DummyObjB;
import cipm.consistency.initialisers.tests.dummy.packages.DummyObjC;
import cipm.consistency.initialisers.tests.dummy.packages.DummyObjD;
import cipm.consistency.initialisers.tests.dummy.packages.DummyObjE;
import cipm.consistency.initialisers.tests.dummy.packages.DummyTopLevelInitialiserPackage;

public class InitialiserPackageTests {
	private void assertInitialiserReachableFrom(Class<?> cls, IInitialiserPackage pac, boolean isReachable) {
		var init = pac.getInitialiserInstanceFor(cls);
		var initCls = pac.getInitialiserInterfaceTypeFor(cls);

		Assertions.assertEquals(isReachable, init != null);
		Assertions.assertEquals(isReachable, initCls != null);

		if (isReachable) {
			Assertions.assertTrue(init.isInitialiserFor(cls));
			Assertions.assertTrue(init.getClass().equals(initCls));
		}
	}

	private void assertInitialiserReachableFrom(Class<?> cls, IInitialiserPackage pac) {
		this.assertInitialiserReachableFrom(cls, pac, true);
	}

	private <T> void assertContentEqualsByClass(T[] expectedElems, Collection<T> col) {
		if (expectedElems != null) {
			Assertions.assertEquals(expectedElems.length, col.size());

			for (var elem : expectedElems) {
				Assertions.assertTrue(
						col.contains(elem) || col.stream().anyMatch((e) -> e.getClass().equals(elem.getClass())));
			}
		} else {
			Assertions.assertEquals(0, col.size());
		}
	}

	@SuppressWarnings("unchecked")
	private void assertInitialiserContentEquals(IInitialiser[] inits, Collection<IInitialiser> initInsCol,
			Collection<Class<? extends IInitialiser>> initClsCol) {
		this.assertContentEqualsByClass(inits, initInsCol);
		this.assertContentEqualsByClass(initInsCol.stream().map((i) -> i.getClass()).toArray(Class[]::new), initClsCol);
	}

	private void assertDirectPackageContentEquals(IInitialiser[] inits, IInitialiserPackage pac) {
		this.assertInitialiserContentEquals(inits, pac.getInitialiserInstances(), pac.getInitialiserInterfaceTypes());
	}

	private void assertAllPackageContentEquals(IInitialiser[] inits, IInitialiserPackage pac) {
		this.assertInitialiserContentEquals(inits, pac.getAllInitialiserInstances(),
				pac.getAllInitialiserInterfaceTypes());
	}

	@Test
	public void test_TopLevelPackage_Content() {
		var pac = new DummyTopLevelInitialiserPackage();

		this.assertDirectPackageContentEquals(null, pac);
		this.assertAllPackageContentEquals(new IInitialiser[] { new DummyInitialiserA(), new DummyInitialiserB(),
				new DummyInitialiserC(), new DummyInitialiserD(), new DummyInitialiserE() }, pac);
	}

	@Test
	public void test_AggregateOnePackage_Content() {
		var pac = new DummyAggregateInitialiserPackageOne();

		this.assertDirectPackageContentEquals(new IInitialiser[] { new DummyInitialiserE() }, pac);
		this.assertAllPackageContentEquals(
				new IInitialiser[] { new DummyInitialiserE(), new DummyInitialiserA(), new DummyInitialiserB() }, pac);
	}

	@Test
	public void test_AggregateTwoPackage_Content() {
		var pac = new DummyAggregateInitialiserPackageTwo();

		this.assertDirectPackageContentEquals(null, pac);
		this.assertAllPackageContentEquals(new IInitialiser[] { new DummyInitialiserC(), new DummyInitialiserD() },
				pac);
	}

	@Test
	public void test_TopLevelPackage_GetInitialiser() {
		var pac = new DummyTopLevelInitialiserPackage();

		this.assertInitialiserReachableFrom(DummyObjA.class, pac);
		this.assertInitialiserReachableFrom(DummyObjB.class, pac);
		this.assertInitialiserReachableFrom(DummyObjC.class, pac);
		this.assertInitialiserReachableFrom(DummyObjD.class, pac);
		this.assertInitialiserReachableFrom(DummyObjE.class, pac);
	}

	@Test
	public void test_AggregateOnePackage_GetInitialiser() {
		var pac = new DummyAggregateInitialiserPackageOne();

		this.assertInitialiserReachableFrom(DummyObjA.class, pac);
		this.assertInitialiserReachableFrom(DummyObjB.class, pac);
		this.assertInitialiserReachableFrom(DummyObjC.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjD.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjE.class, pac);
	}

	@Test
	public void test_AggregateTwoPackage_GetInitialiser() {
		var pac = new DummyAggregateInitialiserPackageTwo();

		this.assertInitialiserReachableFrom(DummyObjA.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjB.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjC.class, pac);
		this.assertInitialiserReachableFrom(DummyObjD.class, pac);
		this.assertInitialiserReachableFrom(DummyObjE.class, pac, false);
	}

	@Test
	public void test_PackageA_GetInitialiser() {
		var pac = new DummyInitialiserPackageA();

		this.assertInitialiserReachableFrom(DummyObjA.class, pac);
		this.assertInitialiserReachableFrom(DummyObjB.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjC.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjD.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjE.class, pac, false);
	}

	@Test
	public void test_PackageB_GetInitialiser() {
		var pac = new DummyInitialiserPackageB();

		this.assertInitialiserReachableFrom(DummyObjA.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjB.class, pac);
		this.assertInitialiserReachableFrom(DummyObjC.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjD.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjE.class, pac, false);
	}

	@Test
	public void test_PackageCD_GetInitialiser() {
		var pac = new DummyInitialiserPackageCD();

		this.assertInitialiserReachableFrom(DummyObjA.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjB.class, pac, false);
		this.assertInitialiserReachableFrom(DummyObjC.class, pac);
		this.assertInitialiserReachableFrom(DummyObjD.class, pac);
		this.assertInitialiserReachableFrom(DummyObjE.class, pac, false);
	}
}
