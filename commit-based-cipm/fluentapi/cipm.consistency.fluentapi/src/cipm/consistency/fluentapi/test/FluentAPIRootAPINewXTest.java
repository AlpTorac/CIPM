package cipm.consistency.fluentapi.test;

import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIRootAPINewXTest extends AbstractFluentAPITest {
	@Test
	public void newXTest_WithEClass() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ContainersPackage.Literals.MODULE.getInstanceClass();
		var mod = api.newX(cls).createNow();
		Assertions.assertInstanceOf(cls, mod);
	}

	@Test
	public void newXTest_WithClass() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ContainersPackage.Literals.MODULE.getInstanceClass();
		var mod = api.newX(cls).createNow();
		Assertions.assertInstanceOf(cls, mod);
	}

	@Test
	public void createNewXTest_WithClass() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ContainersPackage.Literals.MODULE.getInstanceClass();
		var mod = api.createNewX(cls);
		Assertions.assertInstanceOf(cls, mod);
	}

	@Test
	public void createNewXTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.createNewModule();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
	}
}
