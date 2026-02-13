package cipm.consistency.fluentapi.test;

import java.util.stream.Collectors;

import org.emftext.language.java.containers.ContainersPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;

public class FluentAPIRootAPITest extends AbstractFluentAPITest {
	@Test
	public void createNowTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void createNowWithTypeTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newX(ContainersPackage.Literals.MODULE)
				.createNow(org.emftext.language.java.containers.Module.class);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void toAPITest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertSame(api, api.newAdditionalField().toAPI());
	}

	@Test
	public void modifyTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = api.newClass().createNow();

		var init = api.modifyClass(cls);
		// Ensure that modifyX() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());

		var cls2 = init.createNow();
		Assertions.assertSame(cls, cls2);
	}

	@Test
	public void modifyMarkedElementTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsKey = new Object();
		var cls = api.newClass().markCurrent(clsKey).createNow();

		Assertions.assertSame(cls, api.getMarkedClass(clsKey));

		var cls2 = api.modifyMarkedClass(clsKey).createNow();
		Assertions.assertSame(cls, cls2);

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
	public void newXWithEClassTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ContainersPackage.Literals.MODULE.getInstanceClass();
		var mod = api.newX(cls).createNow();
		Assertions.assertInstanceOf(cls, mod);
	}

	@Test
	public void newXWithClassTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ContainersPackage.Literals.MODULE.getInstanceClass();
		var mod = api.newX(cls).createNow();
		Assertions.assertInstanceOf(cls, mod);
	}

	@Test
	public void createNewXTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ContainersPackage.Literals.MODULE.getInstanceClass();
		var mod = api.createNewX(cls);
		Assertions.assertInstanceOf(cls, mod);
	}

	@Test
	public void getAllSupportedClassesTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var supportedEClasses = api.getAllSupportedClasses();
		var expectedSupportedEClasses = new FluentAPIJavaMetamodelPackageProvider()
				.getAllTargetMetamodelConcreteEClasses();
		var expectedSupportedClasses = expectedSupportedEClasses.stream().map((eCls) -> eCls.getInstanceClass())
				.collect(Collectors.toList());
		Assertions.assertEquals(expectedSupportedClasses.size(), supportedEClasses.size());
		Assertions.assertTrue(supportedEClasses.containsAll(expectedSupportedClasses));
	}

	public void createNewXMethodTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var mod = api.createNewModule();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
	}
}
