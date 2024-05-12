package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesParameters;

public class ParametrizableTest extends EObjectSimilarityTest implements UsesParameters {
	private Parameter param1;
	private Parameter param2;
	private Parameter param3;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.param1 = this.createMinimalParamWithClsTarget("p1", "t1");
		this.param2 = this.createMinimalParamWithClsTarget("p2", "t2");
		this.param3 = this.createMinimalParamWithClsTarget("p3", "t3");
		
		super.setUp();
	}
	
	protected Parametrizable initElement(IParametrizableInitialiser init, Parameter[] params) {
		Parametrizable result = init.instantiate();
		init.minimalInitialisation(result);
		init.addParameters(result, params);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ParametrizableTestParams.class)
	public void testParameters(IParametrizableInitialiser init) {
		this.setResourceFileTestIdentifier("testParameters");
		
		var objOne = this.initElement(init, new Parameter[] {param1});
		var objTwo = this.initElement(init, new Parameter[] {param2, param3});
		
		this.testX(objOne, objTwo, false);
	}
}
