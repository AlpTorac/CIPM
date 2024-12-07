package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.initialisers.jamopp.IJaMoPPEObjectInitialiser;
import cipm.consistency.initialisers.jamopp.classifiers.ClassInitialiser;

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

		// If type is primitive, null will be converted to a default value
		if (type.isPrimitive()) return null;
		if (Integer.class.isAssignableFrom(type)) return 0;
		if (Byte.class.isAssignableFrom(type)) return 0;
		if (Character.class.isAssignableFrom(type)) return 0;
		if (Boolean.class.isAssignableFrom(type)) return Boolean.FALSE;
		if (Double.class.isAssignableFrom(type)) return 0;
		if (Float.class.isAssignableFrom(type)) return 0;
		if (Long.class.isAssignableFrom(type)) return 0;
		if (Short.class.isAssignableFrom(type)) return 0;
		if (String.class.isAssignableFrom(type)) return "str";

		return null;
	}

	private Collection<EObject> generateEObjectValuesFor(EStructuralFeature feat) {
		var type = feat.getEType().getInstanceClass();
		var values = new ArrayList<EObject>();
		
		var pac = this.getUsedInitialiserPackage();
		var initInstances = List.of(pac.getAllInitialiserInstances().stream()
				.filter((i) -> i.isInitialiserFor(type)).toArray(IJaMoPPEObjectInitialiser[]::new));

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
				}
				this.setValueOf(obj, attr, vals);
				tests.add(this.getAssertionTest(oldObj, obj));
			}
		} else {
			var vals = new Object[arrSizes];
			for (int i = 0; i < vals.length; i++) {
				vals[i] = this.generateNonEObjectValueFor(attr);
			}
			this.setValueOf(obj, attr, vals);
			tests.add(this.getAssertionTest(oldObj, obj));
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
			
			// 1, 0 yields 63.1% coverage (3143/4983)
			// 1, 1 yields 64% coverage (3191/4983)
			// 1, 2 yields 64% coverage (3191/4983)
			// 1, 3 yields 64% coverage (3191/4983)
			
			// 2, 0 yields 63.1% coverage (3143/4983)
			// 2, 1 yields 64.7% coverage (3223/4983)
			// 2, 2 yields 64.7% coverage (3223/4983)
			// 2, 3 yields 64.7% coverage (3223/4983)
			
			// 3, 0 yields 63.1% coverage (3143/4983)
			// 3, 1 yields 64.7% coverage (3223/4983)
			tests.addAll(this.initialiseAllFeatures((EObject) init.instantiate(), 5, 2));
		}
		return tests;
	}
}
