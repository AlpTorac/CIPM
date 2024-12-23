package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.base.JavaSimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.eobject.EcoreUtilHelper;
import cipm.consistency.fitests.similarity.jamopp.gens.AbstractGenerator;
import cipm.consistency.fitests.similarity.jamopp.gens.AdditiveExpressionGen;
import cipm.consistency.fitests.similarity.jamopp.gens.AndExpressionGen;
import cipm.consistency.fitests.similarity.jamopp.gens.GeneratorOutput;
import cipm.consistency.fitests.similarity.jamopp.params.JaMoPPSimilarityValues;
import cipm.consistency.fitests.similarity.params.ISimilarityValues;

public class GenTest {
	private AbstractGenerator[] gens = new AbstractGenerator[] {
			new AdditiveExpressionGen(),
			new AndExpressionGen()
	};

	private ISimilarityValues sVals = new JaMoPPSimilarityValues();

	private Boolean getSimValFor(Class<? extends EObject> objCls, Object attrKey) {
		return sVals.getExpectedSimilarityResult(objCls, attrKey);
	}

	private EObject cloneEObject(EObject obj) {
		return new EcoreUtilHelper().cloneEObjWithContainers(obj);
	}

	@TestFactory
	public Collection<DynamicTest> test() {
		var simChecker = new JavaSimilarityCheckerContainer();
		var tests = new ArrayList<DynamicTest>();
		var objs = new ArrayList<GeneratorOutput>();
		for (var gen : gens) {
			objs.addAll(gen.generateAll());
		}
		for (var obj : objs) {
			var attrs = obj.getChangedAttrs();
			var res = Boolean.TRUE;
			var lhs = obj.getLhs();
			var rhs = obj.getRhs();
			if (attrs != null) {
				for (var attr : attrs) {
					res = res && this.getSimValFor(lhs.getClass(), attr);
				}
			}
			final var finalRes = res;
			tests.add(DynamicTest.dynamicTest("lhs reference similarity test", () -> {
				Assertions.assertTrue(simChecker.isSimilar(lhs, lhs));
			}));
			tests.add(DynamicTest.dynamicTest("rhs reference similarity test", () -> {
				Assertions.assertTrue(simChecker.isSimilar(rhs, rhs));
			}));
			tests.add(DynamicTest.dynamicTest("lhs clone similarity test", () -> {
				Assertions.assertTrue(simChecker.isSimilar(lhs, cloneEObject(lhs)));
			}));
			tests.add(DynamicTest.dynamicTest("rhs clone similarity test", () -> {
				Assertions.assertTrue(simChecker.isSimilar(rhs, cloneEObject(rhs)));
			}));
			tests.add(DynamicTest.dynamicTest("lhs clone symmetry test", () -> {
				Assertions.assertTrue(simChecker.isSimilar(cloneEObject(lhs), lhs));
			}));
			tests.add(DynamicTest.dynamicTest("rhs clone symmetry test", () -> {
				Assertions.assertTrue(simChecker.isSimilar(cloneEObject(rhs), rhs));
			}));
			tests.add(DynamicTest.dynamicTest("isSimilar test", () -> {
				Assertions.assertEquals(simChecker.isSimilar(lhs, rhs), finalRes);
			}));
			tests.add(DynamicTest.dynamicTest("isSimilar symmetry test", () -> {
				Assertions.assertEquals(simChecker.isSimilar(rhs, lhs), finalRes);
			}));
			tests.add(DynamicTest.dynamicTest("areSimilar test", () -> {
				Assertions.assertEquals(simChecker.areSimilar(List.of(lhs), List.of(rhs)), finalRes);
			}));
			tests.add(DynamicTest.dynamicTest("areSimilar symmetry test", () -> {
				Assertions.assertEquals(simChecker.areSimilar(List.of(rhs), List.of(lhs)), finalRes);
			}));
		}
		return tests;
	}
}
