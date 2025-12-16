package cipm.consistency.fluentapi.test;

import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIMarkTest {
	@Test
	public void markAndSkipConstruction() {
		var mod1Key = new Object();

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod2 = api.newModule().markCurrent(mod1Key).toAPI().newModule().createNow();
		var mod1 = api.getMarked(mod1Key);

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod1);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod2);

		Assertions.assertNotEquals(mod1, mod2);
	}

	@Test
	public void useMarkedInConstruction() {
		var key = new Object();

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newPackage().markCurrent(key).toAPI().newModule()
				.withAddedPackages((org.emftext.language.java.containers.Package) api.getMarked(key)).createNow();

		var pac = api.getMarked(key);

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Package.class, pac);

		Assertions.assertEquals(1, mod.getPackages().size());
		Assertions.assertEquals(pac, mod.getPackages().get(0));
	}

	@Test
	public void unmarkAfterUseInConstruction() {
		var key = new Object();

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newPackage().markCurrent(key).toAPI().newModule()
				.withAddedPackages((org.emftext.language.java.containers.Package) api.getMarked(key)).createNow();

		var pac = api.getMarked(key);
		var pacAfterUnmark = api.unmark(key).getMarked(key);

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Package.class, pac);
		Assertions.assertNull(pacAfterUnmark);

		Assertions.assertEquals(1, mod.getPackages().size());
		Assertions.assertEquals(pac, mod.getPackages().get(0));
		Assertions.assertNotEquals(pac, pacAfterUnmark);
	}

	@Test
	public void getNonExistingMarked() {
		var key = new Object();
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.getMarked(key));
	}

	@Test
	public void differentAPIInstances() {
		var keyOne = new Object();
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiOne.newModule().markCurrent(keyOne);

		var keyTwo = new Object();
		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiTwo.newModule().markCurrent(keyTwo);

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiOne.getMarked(keyOne));
		Assertions.assertNull(apiOne.getMarked(keyTwo));

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiTwo.getMarked(keyTwo));
		Assertions.assertNull(apiTwo.getMarked(keyOne));
	}

	@Test
	public void splitConstructionWithMarks() {
		var modApi = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pacApi = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modKey = new Object();
		var pacKey = new Object();

		modApi.newModule().markCurrent(modKey);
		pacApi.newPackage().markCurrent(pacKey);

		var mod = modApi.continueModule().withAddedPackages((Package) pacApi.getMarked(pacKey)).createNow();
		var pac = pacApi.continuePackage().withModule((Module) modApi.getMarked(modKey)).createNow();

		Assertions.assertEquals(mod, pac.getModule());

		Assertions.assertEquals(1, mod.getPackages().size());
		Assertions.assertEquals(pac, mod.getPackages().get(0));
	}

	@Test
	public void multipleMarksOnSameEObject() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var keyOne = new Object();
		var keyTwo = new Object();

		var mod = api.newModule().markCurrent(keyOne).markCurrent(keyTwo).createNow();

		Assertions.assertEquals(mod, api.getMarked(keyOne));
		Assertions.assertEquals(mod, api.getMarked(keyTwo));
	}

	@Test
	public void differentMarkedEObjects() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var keyOne = new Object();
		var keyTwo = new Object();

		var mod = api.newPackage().markCurrent(keyOne).toAPI().newPackage().markCurrent(keyTwo).toAPI().newModule()
				.withAddedPackages(
						FluentAPITestUtils.toEList((Package) api.getMarked(keyOne), (Package) api.getMarked(keyTwo)))
				.createNow();

		var pacOne = api.getMarked(keyOne);
		var pacTwo = api.getMarked(keyTwo);

		Assertions.assertEquals(2, mod.getPackages().size());
		Assertions.assertEquals(pacOne, mod.getPackages().get(0));
		Assertions.assertEquals(pacTwo, mod.getPackages().get(1));
	}

	@Test
	public void superInitialisationMarkTest() {
		var modKey = new Object();
		var pacKey = new Object();

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newX(ContainersPackage.Literals.MODULE).markCurrent(modKey).createNow();
		Assertions.assertEquals(mod, api.getMarked(modKey));

		var pac = api.newX(ContainersPackage.Literals.PACKAGE).unmark(modKey).markCurrent(pacKey).createNow();
		Assertions.assertNull(api.getMarked(modKey));
		Assertions.assertEquals(pac, api.getMarked(pacKey));
	}
}
