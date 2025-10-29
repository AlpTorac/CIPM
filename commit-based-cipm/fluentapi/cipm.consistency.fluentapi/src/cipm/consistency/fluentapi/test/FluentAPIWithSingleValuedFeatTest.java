package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIWithSingleValuedFeatTest {
	@Test
	public void apiTest_WithSingleValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";

		var mod = api.newModule().withName(modName).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
	}

	@Test
	public void apiTest_WithSingleValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var open = api.newOpen();

		var mod = api.newModule().withOpen(open).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(open, mod.getOpen());
	}
}
