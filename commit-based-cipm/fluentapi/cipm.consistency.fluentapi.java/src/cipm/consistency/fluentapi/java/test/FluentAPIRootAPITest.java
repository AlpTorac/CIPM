package cipm.consistency.fluentapi.java.test;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.java.api.ApiFactory;
import cipm.consistency.fluentapi.java.metamodel.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIRootAPITest extends AbstractFluentAPITest {
	@Test
	public void modifyTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var cls = api.newClass().createNow();

		var init = api.modifyClass(cls);
		// Ensure that modifyX() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());

		var cls2 = init.createNow();
		Assertions.assertSame(cls, cls2);
	}

	@Test
	public void modifyMarkedElementTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var clsKey = new Object();
		var cls = api.newClass().markCurrentElement(clsKey).createNow();

		Assertions.assertSame(cls, api.getMarkedClass(clsKey));

		var cls2 = api.modifyMarkedClass(clsKey).createNow();
		Assertions.assertSame(cls, cls2);

	}

	@Test
	public void getAllSupportedClassesTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var supportedClasses = api.getAllSupportedClasses();
		var provider = new FluentAPIJavaMetamodelPackageProvider();
		var expectedSupportedEClasses = provider.getAllTargetMetamodelConcreteEClasses();
		var expectedSupportedClasses = expectedSupportedEClasses.stream().map((eCls) -> eCls.getInstanceClass())
				.collect(Collectors.toList());
		Assertions.assertEquals(expectedSupportedClasses.size(), supportedClasses.size());
		Assertions.assertTrue(supportedClasses.containsAll(expectedSupportedClasses));
		var originalEClasses = provider.getAllConcreteEClassedInOriginalMetamodel();
		Assertions.assertEquals(originalEClasses.size(), supportedClasses.size());
		Assertions.assertTrue(originalEClasses.stream().allMatch(
				(orECls) -> supportedClasses.stream().anyMatch((suCls) -> orECls.getInstanceClass().equals(suCls))));
	}
}
