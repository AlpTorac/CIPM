package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EClass;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPISuperInitTest {
	private static final EClass modECls = ContainersPackage.Literals.MODULE;

	@Test
	public void onceExistsTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var metName = "met";
		var returnTypeName = "returnType";

		org.emftext.language.java.members.ClassMethod met = null;

		api.newX(org.emftext.language.java.members.ClassMethod.class).markCurrent(metName);
		met = api.getMarkedClassMethod(metName);

		Assertions.assertNull(met.getTypeReference());
		api.onceExists(returnTypeName, () -> api.continueMarkedClassMethod(metName).withTypeReference(
				api.newClassifierReference().withTarget(api.getMarkedClass(returnTypeName)).createNow()));
		Assertions.assertNull(met.getTypeReference());

		var returnType = api.newClass().withName(returnTypeName).markCurrent(returnTypeName).createNow();
		Assertions.assertEquals(returnType, met.getTypeReference().getPureClassifierReference().getTarget());

		met = api.continueClassMethod().createNow(org.emftext.language.java.members.ClassMethod.class);
		Assertions.assertEquals(returnType, met.getTypeReference().getPureClassifierReference().getTarget());
	}

	@Test
	public void markCurrentTest_SingleMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var mod = api.newX(modECls).markCurrent(key).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertSame(mod, api.getMarked(key));
	}

	@Test
	public void markCurrentTest_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key1 = new Object();
		var key2 = new Object();

		var mod = api.newX(modECls).markCurrent(key1).markCurrent(key2).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertSame(mod, api.getMarked(key1));
		Assertions.assertSame(mod, api.getMarked(key2));
	}

	@Test
	public void unmarkCurrentTest_SingleMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var mod = api.newX(modECls).markCurrent(key).unmarkCurrent(key).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertNull(api.getMarked(key));
	}

	@Test
	public void unmarkCurrentTest_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key1 = new Object();
		var key2 = new Object();

		var mod = api.newX(modECls).markCurrent(key1).markCurrent(key2).unmarkCurrent(key1).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertNull(api.getMarked(key1));
		Assertions.assertSame(mod, api.getMarked(key2));
	}
}
