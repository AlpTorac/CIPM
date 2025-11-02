package cipm.consistency.fluentapi.test;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIWithSingleValuedFeatTest {
	@Test
	public void withSingleValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";

		var mod = api.newModule().withName(modName).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
	}

	@Test
	public void withSingleValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var open = api.newOpen();

		var mod = api.newModule().withOpen(open).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(open, mod.getOpen());
	}

	@Test
	public void withoutSingleValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";

		var modInit = api.newModule().withName(modName);
		// TODO Implement getCurrentElement method signature override in each
		// initialisation to spare casting
		Assertions.assertEquals(modName,
				((org.emftext.language.java.containers.Module) modInit.getCurrentElement()).getName());
		modInit.withoutName();
		Assertions.assertNull(((org.emftext.language.java.containers.Module) modInit.getCurrentElement()).getName());
	}

	@Test
	public void withoutSingleValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var open = api.newOpen();

		var modInit = api.newModule().withOpen(open);
		// TODO Implement getCurrentElement method signature override in each
		// initialisation to spare casting
		Assertions.assertEquals(open,
				((org.emftext.language.java.containers.Module) modInit.getCurrentElement()).getOpen());
		modInit.withoutOpen();
		Assertions.assertNull(((org.emftext.language.java.containers.Module) modInit.getCurrentElement()).getOpen());
	}

	@Test
	public void withSingleValuedEAttributeOfContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cuName = "cu";

		var clsInit = api.newClass();
		api.newCompilationUnit().withName(cuName).withAddedClassifiers((ConcreteClassifier) clsInit.getCurrentElement())
				.createNow();

		Assertions.assertNull(((ConcreteClassifier) clsInit.getCurrentElement()).getName());
		var cls = clsInit.withNameOfContainer().createNow();
		Assertions.assertEquals(cuName, cls.getName());
	}

	@Test
	public void withSingleValuedEReferenceOfContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit = api.newClass();

		var pac = api.newPackage().createNow();
		var clsO = api.newClass().withPackage(pac)
				.withAddedMembers(((org.emftext.language.java.classifiers.Class) clsInit.getCurrentElement()))
				.createNow();

		Assertions.assertEquals(pac, clsO.getPackage());
		Assertions.assertEquals(clsO,
				((org.emftext.language.java.classifiers.Class) clsInit.getCurrentElement()).eContainer());
		Assertions.assertNull(((org.emftext.language.java.classifiers.Class) clsInit.getCurrentElement()).getPackage());

		var clsI = clsInit.withPackageOfContainer().createNow();
		Assertions.assertEquals(pac, clsI.getPackage());
	}
}
