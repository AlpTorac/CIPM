package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.base.JavaSimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.jamopp.params.JaMoPPSimilarityValues;
import cipm.consistency.fitests.similarity.params.ISimilarityValues;

public class JaMoPPSimilarityTestGenerator {
	private ISimilarityCheckerContainer simCon = new JavaSimilarityCheckerContainer();
	
	/**
	 * Declared as static final, since its initialisation is costly and it is not
	 * dynamically changed
	 */
	private static final ISimilarityValues simVals = new JaMoPPSimilarityValues();

	public Collection<DynamicTest> generateTestsFor(EObject lhsObj, EObject rhsObj, Class<? extends EObject> objCls,
			Object attrKey) {
		var tests = new ArrayList<DynamicTest>();
		final var expectedSimilarityResult = this.getExpectedSimilarityResult(objCls, attrKey);

		final var lhsSelfSimilarityResult = new boolean[1];
		tests.add(DynamicTest.dynamicTest("isSimilar(lhs, lhs) == true", () -> {
			var res = this.isSimilar(lhsObj, lhsObj);
			lhsSelfSimilarityResult[0] = res;
			Assertions.assertEquals(Boolean.TRUE, res);
		}));

		final var rhsSelfSimilarityResult = new boolean[1];
		tests.add(DynamicTest.dynamicTest("isSimilar(rhs, rhs) == true", () -> {
			var res = this.isSimilar(rhsObj, rhsObj);
			rhsSelfSimilarityResult[0] = res;
			Assertions.assertEquals(Boolean.TRUE, res);
		}));

		final var lhsRhsIsSimilarResult = new boolean[1];
		tests.add(DynamicTest.dynamicTest("isSimilar(lhs, rhs) as expected", () -> {
			var res = this.isSimilar(lhsObj, rhsObj);
			lhsRhsIsSimilarResult[0] = res;
			Assertions.assertEquals(expectedSimilarityResult, res);
		}));

		final var rhsLhsIsSimilarResult = new boolean[1];
		tests.add(DynamicTest.dynamicTest("isSimilar(rhs, lhs) as expected", () -> {
			var res = this.isSimilar(rhsObj, lhsObj);
			rhsLhsIsSimilarResult[0] = res;
			Assertions.assertEquals(expectedSimilarityResult, res);
		}));

		tests.add(DynamicTest.dynamicTest("isSimilar(lhs, rhs) == isSimilar(rhs, lhs)",
				() -> Assertions.assertEquals(lhsRhsIsSimilarResult[0], rhsLhsIsSimilarResult[0])));

		final var lhsRhsAreSimilarResult = new boolean[1];
		tests.add(DynamicTest.dynamicTest("areSimilar(lhs, rhs) as expected", () -> {
			var res = this.areSimilar(List.of(lhsObj), List.of(rhsObj));
			lhsRhsAreSimilarResult[0] = res;
			Assertions.assertEquals(expectedSimilarityResult, res);
		}));
		tests.add(DynamicTest.dynamicTest("isSimilar(lhs, rhs) == areSimilar(lhs, rhs)",
				() -> Assertions.assertEquals(lhsRhsIsSimilarResult[0], lhsRhsAreSimilarResult[0])));

		final var rhsLhsAreSimilarResult = new boolean[1];
		tests.add(DynamicTest.dynamicTest("areSimilar(rhs, lhs) as expected", () -> {
			var res = this.areSimilar(List.of(rhsObj), List.of(lhsObj));
			rhsLhsAreSimilarResult[0] = res;
			Assertions.assertEquals(expectedSimilarityResult, res);
		}));
		tests.add(DynamicTest.dynamicTest("isSimilar(rhs, lhs) == areSimilar(rhs, lhs)",
				() -> Assertions.assertEquals(rhsLhsIsSimilarResult[0], rhsLhsAreSimilarResult[0])));

		tests.add(DynamicTest.dynamicTest("areSimilar(lhs, rhs) == areSimilar(rhs, lhs)",
				() -> Assertions.assertEquals(lhsRhsAreSimilarResult[0], rhsLhsAreSimilarResult[0])));

		return tests;
	}
	
	public Collection<DynamicTest> generateTestsFor(EObject lhsObj, EObject rhsObj,
			Object attrKey) {
		return this.generateTestsFor(lhsObj, rhsObj, lhsObj.getClass(), attrKey);
	}

	public void refreshSimilarityChecker() {
		this.simCon.newSimilarityChecker();
	}

	private Boolean areSimilar(Collection<EObject> list1, Collection<EObject> list2) {
		return this.simCon.areSimilar(list1, list2);
	}

	private Boolean isSimilar(EObject obj1, EObject obj2) {
		return this.simCon.isSimilar(obj1, obj2);
	}

	private Boolean getExpectedSimilarityResult(Class<? extends EObject> objCls, Object attrKey) {
		return simVals.getExpectedSimilarityResult(objCls, attrKey);
	}
}
