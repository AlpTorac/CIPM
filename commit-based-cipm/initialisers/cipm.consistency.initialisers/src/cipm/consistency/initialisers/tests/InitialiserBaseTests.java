package cipm.consistency.initialisers.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.initialisers.IInitialiserAdapterStrategy;
import cipm.consistency.initialisers.IInitialiserBase;
import cipm.consistency.initialisers.tests.dummy.DummyInitialiserA;
import cipm.consistency.initialisers.tests.dummy.ObjAInitStrat;

public class InitialiserBaseTests extends AbstractInitialiserTest {
	private void assertAdapted(IInitialiserBase init) {
		this.assertAdapted(init, true);
	}

	private void assertAdapted(IInitialiserBase init, boolean isAdapted) {
		Assertions.assertEquals(init.getAdaptingInitialiserCount(), init.getAdaptingInitialisers().size());
		Assertions.assertEquals(isAdapted, init.isAdapted());
		Assertions.assertEquals(isAdapted, init.getAdaptingInitialiserCount() > 0);
	}

	private void assertAdaptedBy(IInitialiserAdapterStrategy[] strats, IInitialiserBase init) {
		if (strats != null) {
			var adaptingStrats = init.getAdaptingInitialisers();
			Assertions.assertEquals(strats.length, adaptingStrats.size());
			this.assertAdapted(init);
			for (var strat : strats) {
				Assertions.assertTrue(adaptingStrats.contains(strat));
			}
		} else {
			this.assertAdapted(init, false);
		}
	}

	@Test
	public void test_AddAdapterStrategy() {
		var init = new DummyInitialiserA();

		this.assertAdapted(init, false);

		var stratOne = new ObjAInitStrat();
		init.addAdaptingInitialiser(stratOne);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratOne }, init);

		var stratTwo = new ObjAInitStrat();
		init.addAdaptingInitialiser(stratTwo);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratOne, stratTwo }, init);
	}

	@Test
	public void test_RemoveAdapterStrategy() {
		var init = new DummyInitialiserA();
		var stratOne = new ObjAInitStrat();
		var stratTwo = new ObjAInitStrat();
		init.addAdaptingInitialiser(stratOne);
		init.addAdaptingInitialiser(stratTwo);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratOne, stratTwo }, init);

		init.removeAdaptingInitialiser(stratOne);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratTwo }, init);

		init.removeAdaptingInitialiser(stratTwo);
		this.assertAdaptedBy(null, init);
	}

	@Test
	public void test_RemoveNonExistentAdapterStrategy() {
		var init = new DummyInitialiserA();
		var strat = new ObjAInitStrat();

		this.assertAdapted(init, false);
		init.removeAdaptingInitialiser(strat);
		this.assertAdapted(init, false);
	}

	@Test
	public void test_RemoveAdapterStrategyTwice() {
		var init = new DummyInitialiserA();
		var stratOne = new ObjAInitStrat();
		var stratTwo = new ObjAInitStrat();
		init.addAdaptingInitialiser(stratOne);
		init.addAdaptingInitialiser(stratTwo);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratOne, stratTwo }, init);

		init.removeAdaptingInitialiser(stratOne);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratTwo }, init);
		init.removeAdaptingInitialiser(stratOne);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratTwo }, init);
	}

	@Test
	public void test_CleanAdapterStrategy() {
		var init = new DummyInitialiserA();
		var stratOne = new ObjAInitStrat();
		var stratTwo = new ObjAInitStrat();
		init.addAdaptingInitialiser(stratOne);
		init.addAdaptingInitialiser(stratTwo);
		this.assertAdaptedBy(new IInitialiserAdapterStrategy[] { stratOne, stratTwo }, init);

		init.cleanAdaptingInitialiser();
		this.assertAdapted(init, false);
	}
}
