package cipm.consistency.fitests.similarity.jamopp;

import java.util.Arrays;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.initialisers.jamopp.classifiers.ClassInitialiser;

public class BruteForceJaMoPPSimilarityTest extends AbstractJaMoPPSimilarityTest {
	private void setValueOf(EObject obj, EStructuralFeature feat, Object val) {
		if (!feat.isMany()) {
			obj.eSet(feat, val);
		} else {
			if (val == null)
				return;

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

	private void initialiseAllFeatures(EObject obj, int depth, int arrSizes) {
		if (depth <= 0)
			return;

		for (var attr : obj.eClass().getEAllStructuralFeatures()) {
			if (attr.isChangeable()) {
				if (!attr.isMany()) {
					var val = this.generateValueFor(attr);
					if (val != null && EObject.class.isAssignableFrom(val.getClass())) {
						this.initialiseAllFeatures((EObject) val, depth - 1, arrSizes);
					}
					this.setValueOf(obj, attr, val);
				} else {
					var vals = new Object[arrSizes];
					for (int i = 0; i < arrSizes; i++) {
						var val = this.generateValueFor(attr);
						if (val != null && EObject.class.isAssignableFrom(val.getClass())) {
							this.initialiseAllFeatures((EObject) val, depth - 1, arrSizes);
						}
						vals[i] = val;
					}
					this.setValueOf(obj, attr, vals);
				}
			}
		}
	}

	@Test
	public void test() {
		var obj1 = new ClassInitialiser().instantiate();
		var obj2 = new ClassInitialiser().instantiate();
		
		this.initialiseAllFeatures(obj1, 2, 2);
		this.initialiseAllFeatures(obj2, 2, 2);

		Assertions.assertTrue(this.isSimilar(obj1, obj2));
	}
}
