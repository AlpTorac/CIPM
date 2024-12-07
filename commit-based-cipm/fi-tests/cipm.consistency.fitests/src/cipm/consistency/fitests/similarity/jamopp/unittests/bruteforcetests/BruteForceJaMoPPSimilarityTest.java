package cipm.consistency.fitests.similarity.jamopp.unittests.bruteforcetests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.IJaMoPPEObjectInitialiser;

@Disabled("Takes too long to compute")
public class BruteForceJaMoPPSimilarityTest extends AbstractJaMoPPSimilarityTest {
	private void setValueOf(EObject obj, EStructuralFeature feat, Object val) {
		if (val == null)
			return;

		if (!feat.isMany()) {
			obj.eSet(feat, val);
		} else {
			var list = new BasicEList<>();
			if (!val.getClass().isArray()) {
				list.add(val);
			} else {
				for (var valObj : (Object[]) val) {
					if (valObj != null) {
						list.add(valObj);
					}
				}
			}
			obj.eSet(feat, list);
		}
	}

	private Object generateNonEObjectValueFor(EStructuralFeature feat) {
		var type = feat.getEType().getInstanceClass();

		/*
		 * Make sure to use non-default values, in order to ensure that
		 * non-set values become apparent
		 */
		if (int.class.isAssignableFrom(type)) return 1;
		if (byte.class.isAssignableFrom(type)) return 1;
		if (char.class.isAssignableFrom(type)) return 1;
		if (boolean.class.isAssignableFrom(type)) return true;
		if (double.class.isAssignableFrom(type)) return 1d;
		if (float.class.isAssignableFrom(type)) return 1f;
		if (long.class.isAssignableFrom(type)) return 1;
		if (short.class.isAssignableFrom(type)) return 1;
		if (Integer.class.isAssignableFrom(type)) return 1;
		if (Byte.class.isAssignableFrom(type)) return 1;
		if (Character.class.isAssignableFrom(type)) return 1;
		if (Boolean.class.isAssignableFrom(type)) return Boolean.TRUE;
		if (Double.class.isAssignableFrom(type)) return 1d;
		if (Float.class.isAssignableFrom(type)) return 1f;
		if (Long.class.isAssignableFrom(type)) return 1;
		if (Short.class.isAssignableFrom(type)) return 1;
		if (String.class.isAssignableFrom(type)) return "str";

		return null;
	}

	private Collection<EObject> generateEObjectValuesFor(EStructuralFeature feat) {
		var type = feat.getEType().getInstanceClass();
		var values = new ArrayList<EObject>();
		
		var pac = this.getUsedInitialiserPackage();
		var initInstances = List.of(pac.getAllInitialiserInstances().stream()
				.filter((i) -> type.isAssignableFrom(i.instantiate().getClass()))
				.toArray(IJaMoPPEObjectInitialiser[]::new));

		for (var init : initInstances) {
			values.add(init.instantiate());
		}

		return values;
	}

	private Collection<DynamicTest> initialiseNonManyFeature(EObject obj, EStructuralFeature attr, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicTest>();
		var type = attr.getEType().getInstanceClass();
		var oldObj = this.cloneEObjWithContainers(obj);
		
		if (EObject.class.isAssignableFrom(type)) {
			var generatedVals = this.generateEObjectValuesFor(attr);
			for (var val : generatedVals) {
				tests.addAll(this.initialiseAllFeatures(val, depth - 1, arrSizes));
				this.setValueOf(obj, attr, val);
				tests.add(this.getAssertionTest(oldObj, obj));
			}
		} else {
			var generatedVal = this.generateNonEObjectValueFor(attr);
			this.setValueOf(obj, attr, generatedVal);
			tests.add(this.getAssertionTest(oldObj, obj));
		}
		
		return tests;
	}

	private Collection<DynamicTest> initialiseManyFeature(EObject obj, EStructuralFeature attr, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicTest>();
		var type = attr.getEType().getInstanceClass();
		var oldObj = this.cloneEObjWithContainers(obj);
		
		if (EObject.class.isAssignableFrom(type)) {
			var generatedVals = this.generateEObjectValuesFor(attr);
			for (var val : generatedVals) {
				tests.addAll(this.initialiseAllFeatures(val, depth - 1, arrSizes));
				var vals = new EObject[arrSizes];
				for (int i = 0; i < vals.length; i++) {
					vals[i] = this.cloneEObjWithContainers(val);
					this.setValueOf(obj, attr, vals);
					tests.add(this.getAssertionTest(oldObj, obj));
				}
			}
		} else {
			var vals = new Object[arrSizes];
			for (int i = 0; i < vals.length; i++) {
				vals[i] = this.generateNonEObjectValueFor(attr);
				this.setValueOf(obj, attr, vals);
				tests.add(this.getAssertionTest(oldObj, obj));
			}
		}
		
		return tests;
	}

	private Collection<DynamicTest> initialiseAllFeatures(EObject obj, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicTest>();
		if (depth <= 0)
			return tests;

		for (var attr : obj.eClass().getEAllStructuralFeatures()) {
			if (attr.isChangeable()) {
				if (!attr.isMany()) {
					tests.addAll(this.initialiseNonManyFeature(obj, attr, depth, arrSizes));
				} else {
					tests.addAll(this.initialiseManyFeature(obj, attr, depth, arrSizes));
				}
			}
		}
		
		return tests;
	}

	private DynamicTest getAssertionTest(EObject oldObj, EObject obj) {
		return DynamicTest.dynamicTest("ph", () -> {
			Assertions.assertTrue(this.isSimilar(oldObj, oldObj));
			Assertions.assertEquals(this.isSimilar(oldObj, obj),
					this.isSimilar(obj, oldObj));
			Assertions.assertTrue(this.isSimilar(obj, obj));
		});
	}

	@TestFactory
	public Collection<DynamicTest> test() {
		var tests = new ArrayList<DynamicTest>();
		for (var init : this.getUsedInitialiserPackage().getAllInitialiserInstances()) {
			// 0, 0 yields 0% coverage (0/4983)
			
			// 1, 0 yields 61.7% coverage (3074/4983)
			// 1, 1 yields 68.5% coverage (3411/4983)
			// 1, 2 yields 68.5% coverage (3411/4983)
			// 2, 0 yields 61.7% coverage (3074/4983) about 3 mins
			// 2, 1 yields 68.9% coverage (3432/4983) about 11 mins
			tests.addAll(this.initialiseAllFeatures((EObject) init.instantiate(), 1, 1));
		}
		return tests;
	}
}
