package cipm.consistency.fluentapi.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIRootAPIGetOngoingInitTest extends AbstractFluentAPITest {
	@Test
	public void getOngoingInitTest_NoInitialisations() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertEquals(0, api.getOngoingInitialisations().size());
	}

	@Test
	public void getOngoingInitTest_SameInitialisationType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit1 = api.newClass();
		var clsInit2 = api.newClass();
		var clsInit3 = api.newClass();

		Assertions.assertEquals(3, api.getOngoingInitialisations().size());
		Assertions.assertSame(clsInit1, api.getOngoingInitialisations().get(0));
		Assertions.assertSame(clsInit2, api.getOngoingInitialisations().get(1));
		Assertions.assertSame(clsInit3, api.getOngoingInitialisations().get(2));
	}

	@Test
	public void getOngoingInitTest_DifferentInitialisationTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = api.newClass();
		var init2 = api.newInterface();
		var init3 = api.newClass();

		Assertions.assertEquals(3, api.getOngoingInitialisations().size());
		Assertions.assertSame(init1, api.getOngoingInitialisations().get(0));
		Assertions.assertSame(init2, api.getOngoingInitialisations().get(1));
		Assertions.assertSame(init3, api.getOngoingInitialisations().get(2));
	}

	@Test
	public void getOngoingInitTest_DifferentAPIInstances() {
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = apiOne.newClass();
		var init2 = apiTwo.newClass();
		var init3 = apiOne.newClass();

		for (var api : List.of(apiOne, apiTwo)) {
			Assertions.assertEquals(3, api.getOngoingInitialisations().size());
			Assertions.assertSame(init1, api.getOngoingInitialisations().get(0));
			Assertions.assertSame(init2, api.getOngoingInitialisations().get(1));
			Assertions.assertSame(init3, api.getOngoingInitialisations().get(2));
		}
	}

	@Test
	public void clearAllOngoingInitsTest() {
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		for (var api : List.of(apiOne, apiTwo)) {
			apiOne.newClass();
			apiOne.newInterface();
			apiTwo.newEnumeration();
			apiTwo.newAdditionalField();

			Assertions.assertEquals(4, apiOne.getOngoingInitialisations().size());
			Assertions.assertEquals(4, apiTwo.getOngoingInitialisations().size());
			api.clearAllOngoingInitialisations();
			Assertions.assertEquals(0, apiOne.getOngoingInitialisations().size());
			Assertions.assertEquals(0, apiTwo.getOngoingInitialisations().size());
		}
	}
}
