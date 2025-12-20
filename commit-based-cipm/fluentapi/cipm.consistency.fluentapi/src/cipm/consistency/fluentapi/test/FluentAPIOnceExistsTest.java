package cipm.consistency.fluentapi.test;

import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIOnceExistsTest {
	@Test
	public void delayedElementConstruction() {

		/*
		 * TODO Remove the need to cast (consumers and class)
		 * 
		 * PROMISING ALTERNATIVE:
		 * 
		 * In utility class: `public static Runnable toRunnable(T arg, Consumer<T>
		 * cons)`
		 * 
		 * Where fluent API is used: `public void somethingToDo(T arg)`
		 * 
		 * In model construction: `api.onceExists(toRunnable(api.getMarkedX(key),
		 * this::somethingToDo))`
		 */

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		api.newClass().withName(outerClsName);

		api.newClass().withName(cls1Name).markCurrent(cls1Name);
		api.onceExists(cls2Name, (Consumer<org.emftext.language.java.classifiers.Class>) (cls2) -> api.modifyClass(cls2)
				.withExtends(api.newClassifierReference().withTarget(api.getMarkedClass(cls1Name)).createNow()));

//		api.onceExists(cls2Name,
//				toRunnable((org.emftext.language.java.classifiers.Class) api.getMarked(cls1Name), this::cons));

		api.newClass().withName(cls2Name).markCurrent(cls2Name);
		api.onceExists(cls1Name, (Consumer<org.emftext.language.java.classifiers.Class>) (cls1) -> api.modifyClass(cls1)
				.withExtends(api.newClassifierReference().withTarget(api.getMarkedClass(cls2Name)).createNow()));

		api.continueOldestClass().withAddedMembers(FluentAPITestUtils.toEList(api.continueClassFromStart(1).createNow(),
				api.continueClassFromStart(1).createNow()));

		var outerCls = api.continueOldestClass().createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.classifiers.Class.class, outerCls);
		Assertions.assertEquals(2, outerCls.getMembers().size());

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls1.getExtends().getPureClassifierReference().getTarget().getName());

		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);
		Assertions.assertEquals(cls2Name, cls2.getName());
		Assertions.assertEquals(cls1Name, cls2.getExtends().getPureClassifierReference().getTarget().getName());
	}

	@Test
	public void delayedElementConstruction_Alternative() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		api.newClass().withName(outerClsName);

		api.newClass().withName(cls1Name).markCurrent(cls1Name);
		api.onceExists(cls2Name, (Consumer<org.emftext.language.java.classifiers.Class>) (cls2) -> api.modifyClass(cls2)
				.withExtends(api.newClassifierReference().withTarget(api.getMarkedClass(cls1Name)).createNow()));

//		api.onceExists(cls2Name, (Runnable) () -> api.modifyClass(api.getMarkedClass(cls2Name))
//				.withExtends(api.newClassifierReference().withTarget(api.getMarkedClass(cls2Name)).createNow()));

		api.newClass().withName(cls2Name).markCurrent(cls2Name);
		api.onceExists(cls1Name, toRunnable(api.getMarkedClass(cls2Name), (cls1) -> api.modifyClass(cls1)
				.withExtends(api.newClassifierReference().withTarget(api.getMarkedClass(cls2Name)).createNow())));

		api.continueOldestClass().withAddedMembers(FluentAPITestUtils.toEList(api.continueClassFromStart(1).createNow(),
				api.continueClassFromStart(1).createNow()));

		var outerCls = api.continueOldestClass().createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.classifiers.Class.class, outerCls);
		Assertions.assertEquals(2, outerCls.getMembers().size());

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls1.getExtends().getPureClassifierReference().getTarget().getName());

		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);
		Assertions.assertEquals(cls2Name, cls2.getName());
		Assertions.assertEquals(cls1Name, cls2.getExtends().getPureClassifierReference().getTarget().getName());
	}

	public <T> Consumer<T> toRunnable(Consumer<T> con) {
		return con;
	}

	public <T> Consumer<T> toRunnable(T arg, Consumer<T> con) {
		return con;
	}

	public void cons(org.emftext.language.java.classifiers.Class cls) {
	};
}
