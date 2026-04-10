package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EClass;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.java.api.ApiFactory;

public class FluentAPISuperInitTest extends AbstractFluentAPITest {
	private static final EClass modECls = ContainersPackage.Literals.MODULE;

	@Test
	public void onceExistsTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var metName = "met";
		var returnTypeName = "returnType";

		org.emftext.language.java.members.ClassMethod met = null;

		api.newX(org.emftext.language.java.members.ClassMethod.class).mark(metName);
		met = api.getMarkedClassMethod(metName);

		Assertions.assertNull(met.getTypeReference());
		api.onceExists(returnTypeName, () -> api.continueMarkedClassMethod(metName).withTypeReference(
				api.newClassifierReference().withTarget(api.getMarkedClass(returnTypeName)).createNow()));
		Assertions.assertNull(met.getTypeReference());

		var returnType = api.newClass().withName(returnTypeName).mark(returnTypeName).createNow();
		Assertions.assertEquals(returnType, met.getTypeReference().getPureClassifierReference().getTarget());

		met = api.continueClassMethod().createNow(org.emftext.language.java.members.ClassMethod.class);
		Assertions.assertEquals(returnType, met.getTypeReference().getPureClassifierReference().getTarget());
	}

	@Test
	public void markTest_SingleMark() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key = new Object();

		var mod = api.newX(modECls).mark(key).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertSame(mod, api.getMarkedX(key));
	}

	@Test
	public void markTest_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key1 = new Object();
		var key2 = new Object();

		var mod = api.newX(modECls).mark(key1).mark(key2).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertSame(mod, api.getMarkedX(key1));
		Assertions.assertSame(mod, api.getMarkedX(key2));
	}

	@Test
	public void unmarkTest_SingleMark() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key = new Object();

		var mod = api.newX(modECls).mark(key).unmark(key).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertNull(api.getMarkedX(key));
	}

	@Test
	public void unmarkTest_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key1 = new Object();
		var key2 = new Object();

		var mod = api.newX(modECls).mark(key1).mark(key2).unmark(key1).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertNull(api.getMarkedX(key1));
		Assertions.assertSame(mod, api.getMarkedX(key2));
	}

	@Test
	public void createNowTest_WithAbstractClass() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		org.emftext.language.java.commons.NamedElement mod = api.newX(ContainersPackage.Literals.MODULE)
				.createNow(org.emftext.language.java.commons.NamedElement.class);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void createNowTest_WithConcreteClass() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		org.emftext.language.java.containers.Module mod = api.newX(ContainersPackage.Literals.MODULE)
				.createNow(org.emftext.language.java.containers.Module.class);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void createNowTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		org.emftext.language.java.containers.Module mod = (org.emftext.language.java.containers.Module) api
				.newX(ContainersPackage.Literals.MODULE).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}
}
