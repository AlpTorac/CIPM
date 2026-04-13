package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.javaFluentAPI.JavaFluentAPIFactory;

public class FluentAPIOnceExistsTest {
	/**
	 * OuterCls {Cls1}
	 */
	@Test
	public void singleOnceExistsTest_MultipleMethodChains() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";

		api.newClass().withName(outerClsName).markCurrent(outerClsName);
		api.onceExists(cls1Name, () -> api.continueOldestClass().withAddedMembers(api.getMarkedClass(cls1Name)));

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
	public void singleOnceExistsTest_SingleMethodChain() {
		// TODO Implement continueMarkedX (since modifyMarkedX is for a different
		// purpose)

		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName)
				//
				.toAPI()
				.onceExists(cls1Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				//
				.newClass().withName(cls1Name).markCurrent(cls1Name).toAPI().continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(cls1Name, cls1.getName());
	}

	/**
	 * OuterCls {Cls1}
	 */
	@Test
	public void singleOnceExistsTest_ViaInitialisation() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName)
				//
				.onceExists(cls1Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				//
				.toAPI().newClass().withName(cls1Name).markCurrent(cls1Name).toAPI().continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(cls1Name, cls1.getName());
	}

	/**
	 * OuterCls {Cls1, Cls2}
	 */
	@Test
	public void sequentialOnceExistsTest() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI()
				.onceExists(cls1Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				.newClass().withName(cls1Name).markCurrent(cls1Name).toAPI()
				.onceExists(cls2Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls2Name)))
				.newClass().withName(cls2Name).markCurrent(cls2Name).toAPI().continueOldestClass().createNow();

		var cls1 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(0);
		var cls2 = (org.emftext.language.java.classifiers.Class) outerCls.getMembers().get(1);

		Assertions.assertEquals(outerClsName, outerCls.getName());
		Assertions.assertEquals(2, outerCls.getMembers().size());
		Assertions.assertEquals(cls1Name, cls1.getName());
		Assertions.assertEquals(cls2Name, cls2.getName());
	}

	/**
	 * OuterCls {Cls1, Cls2}
	 */
	@Test
	public void sequentialOnceExistsTest_ViaInitialisation() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName)
				//
				.onceExists(cls1Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				//
				.onceExists(cls2Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls2Name)))
				// Cls1
				.toAPI().newClass().withName(cls1Name).markCurrent(cls1Name)
				// Cls2
				.toAPI().newClass().withName(cls2Name).markCurrent(cls2Name)
				// OuterCls
				.toAPI().continueOldestClass().createNow();

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
	public void consecutiveOnceExistsChain() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI()
				.onceExists(cls1Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name)))
				.newClass().withName(cls1Name).markCurrent(cls1Name).toAPI()
				.onceExists(cls2Name,
						() -> api.modifyMarkedClass(cls1Name).withExtends(
								api.newClassifierReference().withTarget(api.getMarkedClass(cls2Name)).createNow()))
				.onceExists(cls2Name,
						() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls2Name)))
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
	public void nestedOnceExistsTest_TriggerOuterThenInner() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";
		var cls3Name = "Cls3";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI()
				.onceExists(cls1Name, () ->

				api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name))
						//
						.toAPI().onceExists(cls2Name,
								() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls2Name))
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
	public void nestedOnceExistsTest_TriggerInnerThenOuter() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var outerClsName = "OuterCls";
		var cls1Name = "Cls1";
		var cls2Name = "Cls2";
		var cls3Name = "Cls3";

		var outerCls = api.newClass().withName(outerClsName).markCurrent(outerClsName).toAPI()
				.onceExists(cls1Name, () ->

				api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls1Name))
						//
						.toAPI().onceExists(cls2Name,
								() -> api.modifyMarkedClass(outerClsName).withAddedMembers(api.getMarkedClass(cls2Name))
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

	/**
	 * ImplCls implements Ifc1, Ifc2
	 */
	@Test
	public void multipleMarkOnceExistsTest() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var implementingClsName = "ImplCls";
		var ifcOneName = "Ifc1";
		var ifcTwoName = "Ifc2";

		// Top-down construction: ImplCls construction first, then interfaces'
		// construction
		var implCls = api
				.onceExists(FluentAPITestUtils.toEList(ifcOneName, ifcTwoName), () -> api.newClass()
						.withName(implementingClsName).withAddedImplements(FluentAPITestUtils.toEList(
								api.newClassifierReference().withTarget(api.getMarkedInterface(ifcOneName)).createNow(),
								api.newClassifierReference().withTarget(api.getMarkedInterface(ifcTwoName))
										.createNow()))
						.markCurrent(implementingClsName))
				// Ifc1 construction
				.newInterface().withName(ifcOneName).markCurrent(ifcOneName)
				// Ifc2 construction. Both Ifc exist, trigger onceExists
				.toAPI().newInterface().withName(ifcTwoName).markCurrent(ifcTwoName)
				// Return implCls
				.toAPI().getMarkedClass(implementingClsName);

		var ifcOne = api.getMarkedInterface(ifcOneName);
		Assertions.assertNotNull(ifcOne);
		Assertions.assertEquals(ifcOneName, ifcOne.getName());

		var ifcTwo = api.getMarkedInterface(ifcTwoName);
		Assertions.assertNotNull(ifcTwo);
		Assertions.assertEquals(ifcTwoName, ifcTwo.getName());

		Assertions.assertEquals(2, implCls.getImplements().size());
		Assertions.assertEquals(ifcOne, implCls.getImplements().get(0).getPureClassifierReference().getTarget());
		Assertions.assertEquals(ifcTwo, implCls.getImplements().get(1).getPureClassifierReference().getTarget());
	}

	/**
	 * ImplCls implements Ifc1, Ifc2
	 */
	@Test
	public void multipleMarkOnceExistsTest_AsArray() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var implementingClsName = "ImplCls";
		var ifcOneName = "Ifc1";
		var ifcTwoName = "Ifc2";

		// Top-down construction: ImplCls construction first, then interfaces'
		// construction
		var implCls = api
				.onceExists(new String[] { ifcOneName, ifcTwoName }, () -> api.newClass().withName(implementingClsName)
						.withAddedImplements(FluentAPITestUtils.toEList(
								api.newClassifierReference().withTarget(api.getMarkedInterface(ifcOneName)).createNow(),
								api.newClassifierReference().withTarget(api.getMarkedInterface(ifcTwoName))
										.createNow()))
						.markCurrent(implementingClsName))
				// Ifc1 construction
				.newInterface().withName(ifcOneName).markCurrent(ifcOneName)
				// Ifc2 construction. Both Ifc exist, trigger onceExists
				.toAPI().newInterface().withName(ifcTwoName).markCurrent(ifcTwoName)
				// Return implCls
				.toAPI().getMarkedClass(implementingClsName);

		var ifcOne = api.getMarkedInterface(ifcOneName);
		Assertions.assertNotNull(ifcOne);
		Assertions.assertEquals(ifcOneName, ifcOne.getName());

		var ifcTwo = api.getMarkedInterface(ifcTwoName);
		Assertions.assertNotNull(ifcTwo);
		Assertions.assertEquals(ifcTwoName, ifcTwo.getName());

		Assertions.assertEquals(2, implCls.getImplements().size());
		Assertions.assertEquals(ifcOne, implCls.getImplements().get(0).getPureClassifierReference().getTarget());
		Assertions.assertEquals(ifcTwo, implCls.getImplements().get(1).getPureClassifierReference().getTarget());
	}

	/**
	 * Cls met1()
	 * 
	 * Cls met2()
	 */
	@Test
	public void multipleOnceExistsForSameMarkTest() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var clsName = "Cls";
		var met1Name = "met1";
		var met2Name = "met2";

		var cls =
				// met1
				api.newClassMethod().withName(met1Name).markCurrent(met1Name).toAPI().onceExists(clsName,
						() -> api.modifyMarkedClassMethod(met1Name).withTypeReference(
								api.newClassifierReference().withTarget(api.getMarkedClass(clsName)).createNow()))
						// met2
						.newClassMethod().withName(met2Name).markCurrent(met2Name).toAPI()
						.onceExists(clsName,
								() -> api.modifyMarkedClassMethod(met2Name)
										.withTypeReference(api.newClassifierReference()
												.withTarget(api.getMarkedClass(clsName)).createNow()))
						// Cls
						// Both onceExists trigger, since Cls is marked
						.newClass().withName(clsName).markCurrent(clsName).createNow();

		var met1 = api.getMarkedClassMethod(met1Name);
		Assertions.assertNotNull(met1);
		Assertions.assertEquals(met1Name, met1.getName());
		Assertions.assertEquals(cls, met1.getTypeReference().getPureClassifierReference().getTarget());

		var met2 = api.getMarkedClassMethod(met2Name);
		Assertions.assertNotNull(met2);
		Assertions.assertEquals(met2Name, met2.getName());
		Assertions.assertEquals(cls, met2.getTypeReference().getPureClassifierReference().getTarget());
	}

	/**
	 * Cls met1(PCls p)
	 * 
	 * Cls met2(PCls p)
	 */
	@Test
	public void multipleOnceExistsForSameMarksTest() {
		var api = JavaFluentAPIFactory.eINSTANCE.createFluentJavaAPI();
		var clsName = "Cls";
		var paramClsName = "PCls";
		var paramName = "p";
		var met1Name = "met1";
		var met2Name = "met2";

		var cls =
				// met1
				api.newClassMethod().withName(met1Name).markCurrent(met1Name).toAPI().onceExists(
						FluentAPITestUtils.toEList(clsName, paramClsName),
						() -> api.modifyMarkedClassMethod(met1Name)
								.withTypeReference(api.newClassifierReference().withTarget(api.getMarkedClass(clsName))
										.createNow())
								.withAddedParameters(api.newOrdinaryParameter().withName(paramName)
										.withTypeReference(api.newClassifierReference()
												.withTarget(api.getMarkedClass(paramClsName)).createNow())
										.createNow()))
						// met2
						.newClassMethod().withName(met2Name).markCurrent(met2Name).toAPI()
						.onceExists(FluentAPITestUtils.toEList(clsName, paramClsName), () -> api
								.modifyMarkedClassMethod(met2Name)
								.withTypeReference(api.newClassifierReference().withTarget(api.getMarkedClass(clsName))
										.createNow())
								.withAddedParameters(api.newOrdinaryParameter().withName(paramName)
										.withTypeReference(api.newClassifierReference()
												.withTarget(api.getMarkedClass(paramClsName)).createNow())
										.createNow()))
						// ParamCls
						.newClass().withName(paramClsName).markCurrent(paramClsName)
						// Cls
						// Both onceExists trigger, since Cls is marked
						.toAPI().newClass().withName(clsName).markCurrent(clsName).createNow();

		var met1 = api.getMarkedClassMethod(met1Name);
		Assertions.assertNotNull(met1);
		Assertions.assertEquals(met1Name, met1.getName());
		Assertions.assertEquals(cls, met1.getTypeReference().getPureClassifierReference().getTarget());

		Assertions.assertEquals(1, met1.getParameters().size());
		Assertions.assertEquals(paramName, met1.getParameters().get(0).getName());
		Assertions.assertEquals(paramClsName,
				met1.getParameters().get(0).getTypeReference().getPureClassifierReference().getTarget().getName());

		var met2 = api.getMarkedClassMethod(met2Name);
		Assertions.assertNotNull(met2);
		Assertions.assertEquals(met2Name, met2.getName());
		Assertions.assertEquals(cls, met2.getTypeReference().getPureClassifierReference().getTarget());

		Assertions.assertEquals(1, met2.getParameters().size());
		Assertions.assertEquals(paramName, met2.getParameters().get(0).getName());
		Assertions.assertEquals(paramClsName,
				met2.getParameters().get(0).getTypeReference().getPureClassifierReference().getTarget().getName());
	}
}
