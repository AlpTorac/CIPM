package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

	private Object generateValueFor(EStructuralFeature feat) {
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
		
		var pac = this.getUsedInitialiserPackage();
		var init = pac.getInitialiserInstanceFor(type);
		if (init != null) {
			return (EObject) init.instantiate();
		}
		
		return null;
	}

	private Collection<DynamicTest> initialiseAllFeatures(EObject obj, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicTest>();
		if (depth <= 0)
			return tests;

		for (var attr : obj.eClass().getEAllStructuralFeatures()) {
			if (attr.isChangeable()) {
				if (!attr.isMany()) {
					var val = this.generateValueFor(attr);
					if (val != null && EObject.class.isAssignableFrom(val.getClass())) {
						tests.addAll(this.initialiseAllFeatures((EObject) val, depth - 1, arrSizes));
					}
					this.setValueOf(obj, attr, val);
					tests.add(this.getAssertionTest(obj));
				} else {
					var vals = new Object[arrSizes];
					for (int i = 0; i < arrSizes; i++) {
						var val = this.generateValueFor(attr);
						if (val != null && EObject.class.isAssignableFrom(val.getClass())) {
							tests.addAll(this.initialiseAllFeatures((EObject) val, depth - 1, arrSizes));
						}
						vals[i] = val;
					}
					this.setValueOf(obj, attr, vals);
					tests.add(this.getAssertionTest(obj));
				}
			}
		}
		
		return tests;
	}

	private DynamicTest getAssertionTest(EObject obj) {
		return DynamicTest.dynamicTest("ph", () -> Assertions.assertTrue(this.isSimilar(obj,
				this.cloneEObjWithContainers(obj))));
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
			tests.addAll(this.initialiseAllFeatures((EObject) init.instantiate(), 3, 1));
		}
		return tests;
	}
}
