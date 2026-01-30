package cipm.consistency.fluentapi.test;

import java.math.BigInteger;
import java.util.stream.Collectors;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.modifiers.Abstract;
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
