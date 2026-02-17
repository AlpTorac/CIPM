package cipm.consistency.fluentapi.test;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;

public class FluentAPIRootAPITest extends AbstractFluentAPITest {
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
		var cls = api.newClass().mark(clsKey).createNow();

		Assertions.assertSame(cls, api.getMarkedClass(clsKey));

		var cls2 = api.modifyMarkedClass(clsKey).createNow();
		Assertions.assertSame(cls, cls2);

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
}
