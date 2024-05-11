package cipm.consistency.fitests.similarity.java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.params.SimilarityTestParameter;

public class SimilarityTest extends AbstractEObjectSimilarityTest {
	@ParameterizedTest(name = "[{index}] - {0}")
	@ArgumentsSource(SimilarityTestParams.class)
	public void testSimilarity(SimilarityTestParameter param) {
		this.setResourceFileTestIdentifier(param.getTestType());
		
		var objOne = param.getObjLeft();
		var objTwo = param.getObjRight();
		
		var expectedResult = param.getExpectedSimResult();
		
		this.testX(objOne, objTwo, expectedResult);
	}
}
