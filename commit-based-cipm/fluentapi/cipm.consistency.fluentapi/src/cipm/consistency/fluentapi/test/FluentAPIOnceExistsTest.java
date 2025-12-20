package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIOnceExistsTest {
	/**
	 * OuterCls {Cls1}
	 */
	@Test
	public void singleOnceExistsTest_StyleOne() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";

		api.newClass().withName(outerClsName).markCurrent(outerClsName);
		api.onceExists(cls1Name,
				(Runnable) () -> api.continueOldestClass().withAddedMembers(api.getMarkedClass(cls1Name)));

		api.newClass().withName(cls1Name).markCurrent(cls1Name);

		var outerCls = api.continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(cls1Name, cls1.getName());
	}

	/**
	 * OuterCls {Cls1}
	 */
	@Test
	public void singleOnceExistsTest_StyleTwo() {
		// TODO Implement continueMarkedX (since modifyMarkedX is for a different
		// purpose)

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName)
				//
				.toAPI()
				.onceExists(cls1Name,
						(Runnable) () -> api.modifyMarkedClass(outerClsName)
								.withAddedMembers(api.getMarkedClass(cls1Name)))
				//
				.newClass().withName(cls1Name).markCurrent(cls1Name).toAPI().continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(cls1Name, cls1.getName());
	}

	/**
	 * OuterCls {Cls1, Cls2}
	 */
	@Test
	public void multipleOnceExistsTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI().onceExists(cls1Name,
				(Runnable) () -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				.newClass().withName(cls1Name).markCurrent(cls1Name).toAPI()
				.onceExists(cls2Name,
						(Runnable) () -> api.modifyMarkedClass(outerClsName)
								.withAddedMembers(api.getMarkedClass(cls2Name)))
				.newClass().withName(cls2Name).markCurrent(cls2Name).toAPI().continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(2, outerCls.getMembers().size());
		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls2.getName());
	}

	/**
	 * OuterCls {Cls1 extends Cls2, Cls2}
	 */
	@Test
	public void multipleOnceExistsTest_OnceExistsChain() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI().onceExists(cls1Name,
				(Runnable) () -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				.newClass().withName(cls1Name).markCurrent(cls1Name).toAPI()
				.onceExists(cls2Name,
						(Runnable) () -> api.modifyMarkedClass(cls1Name).withExtends(
								api.newClassifierReference().withTarget(api.getMarkedClass(cls2Name)).createNow()))
				.onceExists(cls2Name,
						(Runnable) () -> api.modifyMarkedClass(outerClsName)
								.withAddedMembers(api.getMarkedClass(cls2Name)))
				.newClass().withName(cls2Name).markCurrent(cls2Name).toAPI().continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(2, outerCls.getMembers().size());

		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls1.getExtends().getPureClassifierReference().getTarget().getName());

		Assertions.assertEquals(cls2Name, cls2.getName());
	}

	/**
	 * OuterCls {Cls1, Cls2, Cls3}
	 * 
	 * Cls1 exists -> Add Cls1 to OuterCls AND (once Cls2 exists -> create Cls3 in
	 * OuterCls)
	 */
	@Test
	public void multipleOnceExistsTest_NestedOnceExists_TriggerOuterThenInner() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";
		var cls3Name = "Cls3";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI()
				.onceExists(cls1Name, (Runnable) () ->

				api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name))
						//
						.toAPI().onceExists(cls2Name,
								(Runnable) () -> api.modifyMarkedClass(outerClsName)
										.withAddedMembers(api.getMarkedClass(cls2Name))
										//
										.toAPI().modifyMarkedClass(outerClsName)
										.withAddedMembers(api.newClass().withName(cls3Name).createNow())))
				// Trigger outer once exists
				.newClass().withName(cls1Name).markCurrent(cls1Name)
				// Trigger inner once exists
				.toAPI().newClass().withName(cls2Name).markCurrent(cls2Name)
				//
				.toAPI().continueOldestClass().createNow();

		Assertions.assertEquals(3, outerCls.getMembers().size());
		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);
		var cls3 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(2);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls2.getName());
		Assertions.assertEquals(cls3Name, cls3.getName());
	}

	/**
	 * OuterCls {Cls1, Cls2, Cls3}
	 * 
	 * Cls1 exists -> Add Cls1 to OuterCls AND (once Cls2 exists -> create Cls3 in
	 * OuterCls)
	 */
	@Test
	public void multipleOnceExistsTest_NestedOnceExists_TriggerInnerThenOuter() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";
		var cls3Name = "Cls3";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI()
				.onceExists(cls1Name, (Runnable) () ->

				api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name))
						//
						.toAPI().onceExists(cls2Name,
								(Runnable) () -> api.modifyMarkedClass(outerClsName)
										.withAddedMembers(api.getMarkedClass(cls2Name))
										//
										.toAPI().modifyMarkedClass(outerClsName)
										.withAddedMembers(api.newClass().withName(cls3Name).createNow())))
				// Inner once exists may trigger
				.newClass().withName(cls2Name).markCurrent(cls2Name)
				// Trigger outer once exists
				.toAPI().newClass().withName(cls1Name).markCurrent(cls1Name)
				//
				.toAPI().continueOldestClass().createNow();

		Assertions.assertEquals(3, outerCls.getMembers().size());
		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);
		var cls3 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(2);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls2.getName());
		Assertions.assertEquals(cls3Name, cls3.getName());
	}
}
