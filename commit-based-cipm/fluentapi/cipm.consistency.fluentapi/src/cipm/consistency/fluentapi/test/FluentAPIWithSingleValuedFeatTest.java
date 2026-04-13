package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EClass;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.javaFluentAPI.JavaFluentAPIFactory;

public class FluentAPIWithSingleValuedFeatTest {
	@Test
	public void withSingleValuedEAttribute() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

		var modName = "modName";

		var mod = api.newModule().withName(modName).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
	}

	@Test
	public void withSingleValuedEReference() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

		var open = api.newOpen();

		var mod = api.newModule().withOpen(open).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(open, mod.getOpen());
	}

	@Test
	public void withoutSingleValuedEAttribute() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

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
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();

		var open = api.newOpen();

		var modInit = api.newModule().withOpen(open);
		// TODO Implement getCurrentElement method signature override in each
		// initialisation to spare casting
		Assertions.assertEquals(open,
				((org.emftext.language.java.containers.Module) modInit.getCurrentElement()).getOpen());
		modInit.withoutOpen();
		Assertions.assertNull(((org.emftext.language.java.containers.Module) modInit.getCurrentElement()).getOpen());
	}
}
