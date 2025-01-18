package cipm.consistency.fitests.similarity.jamopp.unittests.bruteforcetests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.commons.Commentable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.IStructuralFeatureTest;
import cipm.consistency.initialisers.jamopp.IJaMoPPEObjectInitialiser;

// TODO Rename methods and add commentary

// TODO Decide whether this test class should be kept
@Disabled("Disabled till removal")
public class BruteForceJaMoPPSimilarityTest extends AbstractJaMoPPSimilarityTest
	implements IStructuralFeatureTest {

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

	private Collection<DynamicNode> initialiseNonManyFeature(EObject obj, EStructuralFeature attr, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicNode>();
		var type = attr.getEType().getInstanceClass();
		var objToChange = this.cloneEObjWithContainers(obj);
		
		var newDepth = depth - 1;
		var newArrSizes = arrSizes;
		
		if (EObject.class.isAssignableFrom(type)) {
			var generatedValsForAttr = this.generateEObjectValuesFor(attr);
			for (var attrVal : generatedValsForAttr) {
				var dc = DynamicContainer.dynamicContainer(
						this.getRecursionDisplay(newDepth, newArrSizes),
						this.initialiseAllFeatures(attrVal, newDepth, arrSizes));
				this.setValueOf(objToChange, attr, attrVal);
				var test = this.getAssertionTest(obj, objToChange);
				tests.add(DynamicContainer.dynamicContainer(this.getObjDisplay(attrVal, newDepth, newArrSizes),
						List.of(dc, test)));
			}
		} else {
			var generatedVal = this.generateNonEObjectValueFor(attr);
			this.setValueOf(objToChange, attr, generatedVal);
			tests.add(this.getAssertionTest(obj, objToChange));
		}
		
		return tests;
	}
	
	private Collection<DynamicNode> initialiseManyFeature(EObject obj, EStructuralFeature attr, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicNode>();
		var type = attr.getEType().getInstanceClass();
		var objToChange = this.cloneEObjWithContainers(obj);
		
		var newDepth = depth - 1;
		var newArrSizes = arrSizes;
		
		if (EObject.class.isAssignableFrom(type)) {
			var vals = new EObject[newArrSizes];
			for (int i = 0; i < vals.length; i++) {
//				var dc = DynamicContainer.dynamicContainer(
//						this.getRecursionDisplay(newDepth, newArrSizes),
//						this.initialiseManyFeature(objToChange, attr, newDepth, newArrSizes, vals, i));
//				var test = this.getAssertionTest(obj, objToChange);
				tests.add(DynamicContainer.dynamicContainer(this.getArrayDisplay(obj, attr, newDepth, newArrSizes, i),
						this.initialiseManyFeature(objToChange, attr, newDepth, newArrSizes, vals, i)));
			}
		} else {
			var vals = new Object[newArrSizes];
			for (int i = 0; i < vals.length; i++) {
				vals[i] = this.generateNonEObjectValueFor(attr);
				this.setValueOf(objToChange, attr, vals);
				tests.add(this.getAssertionTest(obj, objToChange));
			}
		}
		
		return tests;
	}
	
	private Collection<DynamicNode> initialiseManyFeature(EObject obj, EStructuralFeature attr, int depth, int arrSizes,
			EObject[] attrVals, int attrValIdx) {
		var tests = new ArrayList<DynamicNode>();
		var objToChange = this.cloneEObjWithContainers(obj);

		var generatedValsForAttr = this.generateEObjectValuesFor(attr);
		for (var attrVal : generatedValsForAttr) {
			var dc = DynamicContainer.dynamicContainer(
					this.getRecursionDisplay(depth, arrSizes),
					this.initialiseAllFeatures(attrVal, depth, arrSizes));
			attrVals[attrValIdx] = this.cloneEObjWithContainers(attrVal);
			this.setValueOf(objToChange, attr, attrVals);
			var test = this.getAssertionTest(obj, objToChange);
			tests.add(DynamicContainer.dynamicContainer(this.getObjDisplay(attrVal, depth, arrSizes),
					List.of(dc, test)));
		}
		
		return tests;
	}

	private Collection<DynamicNode> initialiseAllFeatures(EObject obj, int depth, int arrSizes) {
		var tests = new ArrayList<DynamicNode>();
		if (depth <= 0)
			return tests;

		var objECls = obj.eClass();
		for (var attr : objECls.getEAllStructuralFeatures()) {
			
			// Skip attributes from Commentable, since they are irrelevant here
			if (attr.getContainerClass().isAssignableFrom(Commentable.class)) {
				continue;
			}
			
			if (attr.isChangeable()) {
				var isMany = attr.isMany();
				if (!isMany) {
					tests.add(DynamicContainer.dynamicContainer(this.getAttrDisplay(attr, depth, arrSizes), this.initialiseNonManyFeature(obj, attr, depth, arrSizes)));
				} else {
					tests.add(DynamicContainer.dynamicContainer(this.getAttrDisplay(attr, depth, arrSizes), this.initialiseManyFeature(obj, attr, depth, arrSizes)));
				}
			}
		}
		
		return tests;
	}

	private DynamicTest getAssertionTest(EObject obj, EObject objToChange) {
		// FIXME: Find a better display name for tests
		return DynamicTest.dynamicTest("Similarity check", () -> {
			Assertions.assertTrue(this.isSimilar(obj, obj));
			Assertions.assertEquals(this.isSimilar(obj, objToChange),
					this.isSimilar(objToChange, obj));
			Assertions.assertTrue(this.isSimilar(objToChange, objToChange));
		});
	}

	@TestFactory
	public Collection<DynamicNode> test() {
		var tests = new ArrayList<DynamicNode>();
		for (var init : this.getUsedInitialiserPackage().getAllInitialiserInstances()) {
			// 0, 0 yields 0% coverage (0/4983)
			
			// 1, 0 yields 61.7% coverage (3074/4983)
			// 1, 1 yields 68.5% coverage (3411/4983)
			// 1, 2 yields 68.5% coverage (3411/4983)
			// 2, 0 yields 61.7% coverage (3074/4983) about 3 mins
			// 2, 1 yields 68.9% coverage (3432/4983) about 11 mins
			var obj = (EObject) init.instantiate();
			var depth = 2;
			var arrSize = 2;
			tests.add(DynamicContainer.dynamicContainer(
					this.getObjDisplay(obj, depth, arrSize),
					this.initialiseAllFeatures(obj, depth, arrSize)));
		}
		return tests;
	}
	
	private String getObjClassName(EObject obj) {
		return obj.eClass().getInstanceClass().getSimpleName();
	}

	private String getAttrName(EStructuralFeature attr) {
		return attr.getName();
	}
	
	private String getRecursionDisplay(int depth, int arrSize) {
		return String.format("Recursion: depth=%d arrSize=%d", depth, arrSize);
	}
	
	private String getObjDisplay(EObject obj, int depth, int arrSize) {
		return String.format("%s", this.getObjClassName(obj), depth, arrSize);
	}

	private String getAttrDisplay(EStructuralFeature attr, int depth, int arrSize) {
		var isManyDisplay = attr.isMany() ? "Many" : "Non-many";
		var isEObj = EObject.class.isAssignableFrom(attr.getEType().getInstanceClass()) ? "EObject" : "non-EObject";
		return String.format("%s (%s, %s)", this.getAttrName(attr), isManyDisplay, isEObj, depth, arrSize);
	}

	private String getArrayDisplay(EObject obj, EStructuralFeature attr, int depth, int arrSize, int arrIdx) {
		return String.format("%s[%d]", this.getAttrName(attr), arrIdx, depth, arrSize);
	}
}
