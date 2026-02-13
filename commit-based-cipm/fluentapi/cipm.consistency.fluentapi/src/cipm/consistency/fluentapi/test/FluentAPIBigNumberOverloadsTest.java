package cipm.consistency.fluentapi.test;

import java.math.BigInteger;

import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIBigNumberOverloadsTest {
	/**
	 * Ensures that overloading methods for BigInteger are generated
	 */
	@Test
	public void overloadedBigIntegerMethodsTest_SingleValue() {
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
	 * Ensures that direct creation methods for types with only one modifiable
	 * features is possible via the generated API class
	 */
	@Test
	public void overloadedBigIntegerMethodsTest_OnlyOneSingleValuedModifiableFeature() {
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
}
