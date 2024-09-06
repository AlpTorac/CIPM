package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.parameters.Parametrizable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesParameters;

public class ParametrizableTest extends EObjectSimilarityTest implements UsesParameters {
	protected Parametrizable initElement(IParametrizableInitialiser init, Parameter[] params) {
		Parametrizable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addParameters(result, params));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ParametrizableTestParams.class)
	public void testParameters(IParametrizableInitialiser init) {
		var objOne = this.initElement(init, new Parameter[] { this.createMinimalOrdParamWithClsTarget("p1", "t1") });
		var objTwo = this.initElement(init, new Parameter[] { this.createMinimalOrdParamWithClsTarget("p2", "t2") });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS);
	}

	@ParameterizedTest
	@ArgumentsSource(ParametrizableTestParams.class)
	public void testParametersSize(IParametrizableInitialiser init) {
		var objOne = this.initElement(init, new Parameter[] { this.createMinimalOrdParamWithClsTarget("p1", "t1"),
				this.createMinimalOrdParamWithClsTarget("p2", "t2") });
		var objTwo = this.initElement(init, new Parameter[] { this.createMinimalOrdParamWithClsTarget("p1", "t1") });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS);
	}

	@ParameterizedTest
	@ArgumentsSource(ParametrizableTestParams.class)
	public void testParametersNullCheck(IParametrizableInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init, new Parameter[] { this.createMinimalOrdParamWithClsTarget("p1", "t1") }), init,
				true, ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS);
	}
}
