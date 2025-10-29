package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIRootAPITest {
	@Test
	public void apiTest_CreateNow() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void apiTest_ToAPI() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertEquals(api.getClass(), api.newAdditionalField().toAPI().getClass());
		Assertions.assertEquals(api, api.newAdditionalField().toAPI());
	}

	@Test
	public void apiTest_ModifyElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var prevClsName = "prevClsName";
		var cls = api.newClass().withName(prevClsName).createNow();
		Assertions.assertEquals(prevClsName, cls.getName());

		var newClsName = "newClsName";
		api.modifyClass(cls).withName(newClsName);
		Assertions.assertEquals(newClsName, cls.getName());

		// Ensure that modifyX() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	@Test
	public void apiTest_ResetElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";

		var clsInit = api.newClass().withName(clsName);
		Assertions.assertNotNull(clsInit.getCurrentElement());

		clsInit.reset();
		Assertions.assertNull(clsInit.getCurrentElement());

		// Ensure that reset() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}
}
