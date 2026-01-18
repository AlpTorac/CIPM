package cipm.consistency.fluentapi.test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.modifiers.Abstract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;

public class FluentAPIRootAPITest {
	@Test
	public void apiTest_CreateNow() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void apiTest_CreateNowWithType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newX(ContainersPackage.Literals.MODULE)
				.createNow(org.emftext.language.java.containers.Module.class);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void apiTest_ToAPI() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertEquals(api.getClass(), api.newAdditionalField().toAPI().getClass());
		Assertions.assertEquals(api, api.newAdditionalField().toAPI());
	}

	@Test
	public void apiTest_ModifyElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var prevClsName = "prevClsName";
		var cls = api.newClass().withName(prevClsName).createNow();
		Assertions.assertEquals(prevClsName, cls.getName());

		var newClsName = "newClsName";
		api.modifyClass(cls).withName(newClsName);
		Assertions.assertEquals(newClsName, cls.getName());

		// Ensure that modifyX() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	@Test
	public void apiTest_ModifyMarkedElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var prevClsName = "prevClsName";
		api.newClass().withName(prevClsName).markCurrent(prevClsName);
		Assertions.assertEquals(prevClsName, api.getMarkedClass(prevClsName).getName());

		var newClsName = "newClsName";
		api.modifyMarkedClass(prevClsName).withName(newClsName);
		Assertions.assertEquals(newClsName, api.getMarkedClass(prevClsName).getName());
	}

	@Test
	public void apiTest_ResetElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";

		var clsInit = api.newClass().withName(clsName);
		Assertions.assertNotNull(clsInit.getCurrentElement());

		clsInit.reset();
		Assertions.assertNull(clsInit.getCurrentElement());

		// Ensure that reset() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	@Test
	public void withFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = ClassifiersFactory.eINSTANCE.createClass();

		Assertions.assertNotEquals(clsName, cls.getName());
		api.xWithFeat(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME, clsName);
		Assertions.assertEquals(clsName, cls.getName());
	}

	@Test
	public void withoutFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = ClassifiersFactory.eINSTANCE.createClass();
		cls.setName(clsName);

		Assertions.assertEquals(clsName, cls.getName());
		api.xWithoutFeat(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		Assertions.assertNotEquals(clsName, cls.getName());
	}

	@Test
	public void withAddedFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1");
		var nsToAddOne = "ns2";
		var nsToAddTwo = FluentAPITestUtils.toEList("ns3", "ns4");
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddOne);
		Assertions.assertEquals(2, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddOne, pac.getNamespaces().get(1));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddTwo);
		Assertions.assertEquals(4, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddTwo.get(0), pac.getNamespaces().get(2));
		Assertions.assertEquals(nsToAddTwo.get(1), pac.getNamespaces().get(3));
	}

	@Test
	public void withRemovedFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2", "ns3", "ns4");
		var nsToRemoveOne = "ns2";
		var nsToRemoveTwo = FluentAPITestUtils.toEList("ns3", "ns4");
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(3, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().remove(nsToRemoveOne));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(1, pac.getNamespaces().size());
		Assertions.assertEquals(pacNss.get(0), pac.getNamespaces().get(0));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo.get(0)));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo.get(1)));
	}

	@Test
	public void withExactFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2");
		var nsToRemoveOne = FluentAPITestUtils.toEList("ns3");
		var nsToRemoveTwo = FluentAPITestUtils.toEList("ns4", "ns5");
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(nsToRemoveOne.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(nsToRemoveOne));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(nsToRemoveTwo.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(nsToRemoveTwo));
	}

	@Test
	public void withAddedFeat_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1");
		var nsToAddOne = "ns2";
		var nsToAddTwo = new String[] { "ns3", "ns4" };
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddOne);
		Assertions.assertEquals(2, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddOne, pac.getNamespaces().get(1));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddTwo);
		Assertions.assertEquals(4, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddTwo[0], pac.getNamespaces().get(2));
		Assertions.assertEquals(nsToAddTwo[1], pac.getNamespaces().get(3));
	}

	@Test
	public void withRemovedFeat_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2", "ns3", "ns4");
		var nsToRemoveOne = "ns2";
		var nsToRemoveTwo = new String[] { "ns3", "ns4" };
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(3, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().remove(nsToRemoveOne));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(1, pac.getNamespaces().size());
		Assertions.assertEquals(pacNss.get(0), pac.getNamespaces().get(0));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo[0]));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo[1]));
	}

	@Test
	public void withExactFeat_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2");
		var nsToRemoveOne = new String[] { "ns3" };
		var nsToRemoveTwo = new String[] { "ns4", "ns5" };
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(nsToRemoveOne.length, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(List.of(nsToRemoveOne)));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(nsToRemoveTwo.length, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(List.of(nsToRemoveTwo)));
	}

	@Test
	public void withFeatOfContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = ClassifiersFactory.eINSTANCE.createClass();
		var cu = ContainersFactory.eINSTANCE.createCompilationUnit();

		var cuName = "cu";
		cu.setName(cuName);
		Assertions.assertEquals(cuName, cu.getName());
		cu.getClassifiers().add(cls);
		Assertions.assertEquals(cu, cls.eContainer());
		Assertions.assertNull(cls.getName());

		api.xWithFeatOfContainer(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		Assertions.assertEquals(cu.getName(), cls.getName());
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

	/**
	 * Ensures that overloading methods for BigInteger are generated
	 */
	@Test
	public void overloadedBigIntegerMethodsTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		final var lit = new DecimalIntegerLiteral[3];

		BigInteger bigIntVal = BigInteger.valueOf(1);
		Assertions.assertDoesNotThrow(
				() -> lit[0] = api.newDecimalIntegerLiteral().withDecimalValue(bigIntVal).createNow());
		Assertions.assertEquals(bigIntVal, lit[0].getDecimalValue());

		int intVal = bigIntVal.intValue();
		Assertions.assertEquals(1, intVal);
		Assertions
				.assertDoesNotThrow(() -> lit[1] = api.newDecimalIntegerLiteral().withDecimalValue(intVal).createNow());
		Assertions.assertEquals(intVal, lit[1].getDecimalValue().intValue());

		long longVal = bigIntVal.longValue();
		Assertions.assertEquals(1, longVal);
		Assertions.assertDoesNotThrow(
				() -> lit[2] = api.newDecimalIntegerLiteral().withDecimalValue(longVal).createNow());
		Assertions.assertEquals(longVal, lit[2].getDecimalValue().longValue());
	}

	/**
	 * Ensures that direct creation methods for types with no modifiable features is
	 * possible via the generated API
	 */
	@Test
	public void overloadedNewMethodsTest_NoModifiableFeatures() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		final var obj = new Abstract[1];
		Assertions.assertDoesNotThrow(() -> obj[0] = api.newAbstract());
		Assertions.assertInstanceOf(Abstract.class, obj[0]);
	}

	/**
	 * Ensures that direct creation methods for types with only one modifiable
	 * features is possible via the generated API class
	 */
	@Test
	public void overloadedNewMethodsTest_OnlyOneSingleValuedModifiableFeature() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var val = BigInteger.valueOf(1);
		final var obj = new DecimalIntegerLiteral[1];
		Assertions.assertDoesNotThrow(() -> obj[0] = api.newDecimalIntegerLiteral(val));
		Assertions.assertInstanceOf(DecimalIntegerLiteral.class, obj[0]);
		Assertions.assertEquals(val, obj[0].getDecimalValue());
	}

	/**
	 * Ensures that direct creation methods for types with only one modifiable
	 * features is possible via the generated API class
	 */
	@Test
	public void overloadedNewMethodsTest_OnlyOneSingleValuedModifiableFeature_BigInteger() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		final var obj = new DecimalIntegerLiteral[2];

		int intVal = 1;
		Assertions.assertDoesNotThrow(() -> obj[0] = api.newDecimalIntegerLiteral(intVal));
		Assertions.assertInstanceOf(DecimalIntegerLiteral.class, obj[0]);
		Assertions.assertEquals(intVal, obj[0].getDecimalValue().intValue());

		long longVal = 1;
		Assertions.assertDoesNotThrow(() -> obj[1] = api.newDecimalIntegerLiteral(longVal));
		Assertions.assertInstanceOf(DecimalIntegerLiteral.class, obj[1]);
		Assertions.assertEquals(longVal, obj[1].getDecimalValue().longValue());
	}

	/**
	 * Ensures that direct creation methods for types with only one modifiable
	 * features is possible via the generated API class, if said feature is
	 * many-valued and multiple values are passed to the creation method.
	 */
	@Test
	public void overloadedNewMethodsTest_OnlyOneManyValuedModifiableFeature_MultipleValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var val = new AnnotationAttributeSetting[] { api.newAnnotationAttributeSetting().createNow(),
				api.newAnnotationAttributeSetting().createNow() };
		final var obj = new AnnotationParameterList[1];

		Assertions.assertDoesNotThrow(() -> obj[0] = api.newAnnotationParameterList(val));
		Assertions.assertInstanceOf(AnnotationParameterList.class, obj[0]);
		Assertions.assertEquals(val[0], obj[0].getSettings().get(0));
		Assertions.assertEquals(val[1], obj[0].getSettings().get(1));
	}

	/**
	 * Ensures that direct creation methods for types with only one modifiable
	 * features is possible via the generated API class, if said feature is
	 * many-valued and a single value is passed to the creation method.
	 */
	@Test
	public void overloadedNewMethodsTest_OnlyOneManyValuedModifiableFeature_SingleValue() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var val = api.newAnnotationAttributeSetting().createNow();
		final var obj = new AnnotationParameterList[1];

		Assertions.assertDoesNotThrow(() -> obj[0] = api.newAnnotationParameterList(val));
		Assertions.assertInstanceOf(AnnotationParameterList.class, obj[0]);
		Assertions.assertEquals(1, obj[0].getSettings().size());
		Assertions.assertEquals(val, obj[0].getSettings().get(0));
	}
}
