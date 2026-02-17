package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIInitTest extends AbstractFluentAPITest {
	@Test
	public void toAPITest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertSame(api, api.newAdditionalField().toAPI());
	}

	@Test
	public void resetElementTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit = api.newClass();
		Assertions.assertNotNull(clsInit.getCurrentElement());

		clsInit.reset();
		Assertions.assertNull(clsInit.getCurrentElement());

		// Ensure that reset() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	@Test
	public void createNowTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

}
