package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.java.api.ApiFactory;
import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;

public class FluentAPIInitialisationStorageTest extends AbstractFluentAPITest {
	@Test
	public void testAddOngoingInitialisations_SingleInitialisation() {
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var supposedInit = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit);

		Assertions.assertEquals(1, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(supposedInit));
	}

	@Test
	public void testAddOngoingInitialisations_MultipleInitialisations() {
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var supposedInit1 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit1);
		var supposedInit2 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit2);

		Assertions.assertEquals(2, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(supposedInit1));
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(supposedInit2));
	}

	@Test
	public void testAddOngoingInitialisations_Order() {
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var supposedInit1 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit1);
		var supposedInit2 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit2);

		Assertions.assertEquals(2, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertSame(supposedInit1, FluentAPIInitialisationStorage.getOngoingInitialisations().get(0));
		Assertions.assertSame(supposedInit2, FluentAPIInitialisationStorage.getOngoingInitialisations().get(1));
	}

	@Test
	public void testDropInitialisation_SingleInitialisation() {
		var supposedInit = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit);
		FluentAPIInitialisationStorage.dropOngoingInitialisation(supposedInit);
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
	}

	@Test
	public void testDropInitialisation_MultipleInitialisations() {
		var supposedInit1 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit1);
		var supposedInit2 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit2);

		FluentAPIInitialisationStorage.dropOngoingInitialisation(supposedInit1);
		Assertions.assertEquals(1, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertFalse(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(supposedInit1));
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(supposedInit2));

		FluentAPIInitialisationStorage.dropOngoingInitialisation(supposedInit2);
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
	}

	@Test
	public void testDropInitialisation_Order() {
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var supposedInit1 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit1);
		var supposedInit2 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit2);
		var supposedInit3 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit3);

		Assertions.assertArrayEquals(new EObject[] { supposedInit1, supposedInit2, supposedInit3 },
				FluentAPIInitialisationStorage.getOngoingInitialisations().toArray());

		FluentAPIInitialisationStorage.dropOngoingInitialisation(supposedInit2);

		Assertions.assertEquals(2, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertSame(supposedInit1, FluentAPIInitialisationStorage.getOngoingInitialisations().get(0));
		Assertions.assertSame(supposedInit3, FluentAPIInitialisationStorage.getOngoingInitialisations().get(1));
	}

	@Test
	public void testClear() {
		var supposedInit1 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit1);
		var supposedInit2 = EcoreFactory.eINSTANCE.createEObject();
		FluentAPIInitialisationStorage.addOngoingInitialisation(supposedInit2);

		FluentAPIInitialisationStorage.clearAllOngoingInitialisations();
		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
	}

	@Test
	public void testOngoingInits_SingleAPI_SingleOngoingInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var init = api.newClass();

		Assertions.assertEquals(1, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(init));
	}

	@Test
	public void testOngoingInits_SingleAPI_MultipleOngoingInitialisations() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var init1 = api.newClass();
		var init2 = api.newInterface();

		Assertions.assertEquals(2, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(init1));
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(init2));
	}

	@Test
	public void testOngoingInits_MultipleAPIs_MultipleOngoingInitialisations() {
		var apiOne = ApiFactory.eINSTANCE.createFluentJavaAPI();

		Assertions.assertEquals(0, FluentAPIInitialisationStorage.getOngoingInitialisations().size());

		var initOne = apiOne.newClass();

		Assertions.assertEquals(1, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(initOne));

		var apiTwo = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var initTwo = apiTwo.newClass();

		Assertions.assertEquals(2, FluentAPIInitialisationStorage.getOngoingInitialisations().size());
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(initOne));
		Assertions.assertTrue(FluentAPIInitialisationStorage.getOngoingInitialisations().contains(initTwo));
	}
}
