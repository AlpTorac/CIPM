package cipm.consistency.fitests.similarity.jamopp.unittests.fluentapi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPHelper;
import cipm.consistency.fitests.similarity.jamopp.unittests.SimilarityTestUtility;
import cipm.consistency.fluentapi.api.ApiFactory;

public class SimilarityTestTemplates extends AbstractJaMoPPSimilarityTest {
	private void setFeatVal(EObject objToModify, EStructuralFeature feat, Object featVal) {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		api.xWithFeat(objToModify, feat, featVal);
	}

	private void setFeatVal(EObject objToModify, EStructuralFeature feat, Object[] featVal) {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		api.xWithExactFeat(objToModify, feat, SimilarityTestUtility.toEList(featVal));
	}

	private Object[] generateLiterals(Class<?> cls, int count) {
		var vals = new Object[count];
		for (int i = 0; i < vals.length; i++) {
			if (String.class.equals(cls)) {
				vals[i] = String.valueOf(i);
			} else if (Character.class.equals(cls)) {
				vals[i] = Character.valueOf((char) i);
			} else if (char.class.equals(cls)) {
				vals[i] = (char) i;
			}

			else if (Boolean.class.equals(cls)) {
				vals[i] = i % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
			} else if (boolean.class.equals(cls)) {
				vals[i] = i % 2 == 0 ? true : false;
			}

			else if (Byte.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).byteValue();
			} else if (Short.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).shortValue();
			} else if (Long.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).longValue();
			} else if (Double.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).doubleValue();
			} else if (Float.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).floatValue();
			} else if (Integer.class.equals(cls)) {
				vals[i] = Integer.valueOf(i);
			} else if (BigDecimal.class.equals(cls)) {
				vals[i] = BigDecimal.valueOf(i);
			} else if (BigInteger.class.equals(cls)) {
				vals[i] = BigInteger.valueOf(i);
			}

			else if (byte.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).byteValue();
			} else if (short.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).shortValue();
			} else if (long.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).longValue();
			} else if (double.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).doubleValue();
			} else if (float.class.equals(cls)) {
				vals[i] = Integer.valueOf(i).floatValue();
			} else if (int.class.equals(cls)) {
				vals[i] = i;
			}

			else if (Origin.class.equals(cls)) {
				vals[i] = Origin.values()[i % Origin.values().length];
			}

			else {
				throw new IllegalArgumentException(cls.getName() + " is not a literal in EMF sense");
			}
		}

		return vals;
	}

	private EObject[] generateEObjectsFor(EStructuralFeature feat, int count) {
		var vals = new EObject[count];

		final var featType = (EClass) feat.getEType();
		var valECls = featType;

		// Handle feat.getEType() being potentially abstract / interface
		if (valECls.isAbstract() || valECls.isInterface()) {
			valECls = new JaMoPPHelper().getAllEClasses().stream()
					.filter((eCls) -> featType.isSuperTypeOf(eCls) && !eCls.isAbstract() && !eCls.isInterface())
					.findFirst().get();
		}

		for (int i = 0; i < vals.length; i++) {
			vals[i] = ApiFactory.eINSTANCE.createFluentEObjectAPI().newX(valECls).createNow();
		}
		return vals;
	}

	private Object[] getFeatValsFor(EStructuralFeature feat, int count) {
		var featType = feat.getEType().getInstanceClass();
		if (EObject.class.isAssignableFrom(featType)) {
			return generateEObjectsFor(feat, count);
		} else {
			return generateLiterals(featType, count);
		}
	}

	private Object getFeatValFor(EStructuralFeature feat) {
		var featType = feat.getEType().getInstanceClass();
		if (EObject.class.isAssignableFrom(featType)) {
			return generateEObjectsFor(feat, 1)[0];
		} else {
			return generateLiterals(featType, 1)[0];
		}
	}

	private void assertFeatValSimilarity(Object featVal1, Object featVal2, boolean areValsSimilar) {
		if (EObject.class.isAssignableFrom(featVal1.getClass())) {
			this.testSimilarity((EObject) featVal1, (EObject) featVal2, areValsSimilar);
		} else {
			Assertions.assertEquals(areValsSimilar, featVal1.equals(featVal2));
		}
	}

	private void overrideSimilarityForFeatVal(Object elem1, Object elem2, Boolean similarityResultOverride) {
		if (EObject.class.isAssignableFrom(elem1.getClass()) && EObject.class.isAssignableFrom(elem2.getClass())) {
			this.getSCC().overrideFor(elem1, elem2, similarityResultOverride);
		}
	}

	public void testSameX(EClass eCls, EStructuralFeature feat) {
		if (feat.isMany())
			throw new IllegalArgumentException("Feat must be single-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls).createNow();
		var obj2 = api.newX(eCls).createNow();

		var val1 = getFeatValFor(feat);
		var val2 = getFeatValFor(feat);
		overrideSimilarityForFeatVal(val1, val2, true);
		assertFeatValSimilarity(val1, val2, true);

		setFeatVal(obj1, feat, val1);
		setFeatVal(obj2, feat, val2);

//		this.testSimilarity(obj1, obj2, true);
	}

	public void testDifferentX(EClass eCls, EStructuralFeature feat) {
		if (feat.isMany())
			throw new IllegalArgumentException("Feat must be single-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls).createNow();
		var obj2 = api.newX(eCls).createNow();

		var vals = getFeatValsFor(feat, 2);
		var val1 = vals[0];
		var val2 = vals[1];
		overrideSimilarityForFeatVal(val1, val2, false);
		assertFeatValSimilarity(val1, val2, false);

		setFeatVal(obj1, feat, val1);
		setFeatVal(obj2, feat, val2);

//		this.testSimilarity(obj1, obj2, (Class<? extends EObject>) eCls.getInstanceClass(), feat);
	}

	public void testNullCheckX(EClass eCls, EStructuralFeature feat) {
		if (feat.isMany())
			throw new IllegalArgumentException("Feat must be single-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls).createNow();
		var obj2 = api.newX(eCls).createNow();

		var val1 = getFeatValFor(feat);
		setFeatVal(obj1, feat, val1);

//		this.testSimilarity(obj1, obj2, (Class<? extends EObject>) eCls.getInstanceClass(), feat);
	}

	public void testSameXs(EClass eCls, EStructuralFeature feat) {
		if (!feat.isMany())
			throw new IllegalArgumentException("Feat must be many-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls).createNow();
		var obj2 = api.newX(eCls).createNow();

		var vals1 = getFeatValsFor(feat, 2);
		var vals2 = getFeatValsFor(feat, 2);

		overrideSimilarityForFeatVal(vals1[0], vals2[0], true);
		assertFeatValSimilarity(vals1[0], vals2[0], true);

		overrideSimilarityForFeatVal(vals1[1], vals2[0], false);
		assertFeatValSimilarity(vals1[1], vals2[0], false);

		overrideSimilarityForFeatVal(vals1[0], vals2[1], false);
		assertFeatValSimilarity(vals1[0], vals2[1], false);

		overrideSimilarityForFeatVal(vals1[1], vals2[1], true);
		assertFeatValSimilarity(vals1[1], vals2[1], true);

		setFeatVal(obj1, feat, vals1);
		setFeatVal(obj2, feat, vals2);

//		this.testSimilarity(obj1, obj2, true);
	}

	public void testDifferentXs(EClass eCls, EStructuralFeature feat) {
		if (!feat.isMany())
			throw new IllegalArgumentException("Feat must be many-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls).createNow();
		var obj2 = api.newX(eCls).createNow();

		var vals = getFeatValsFor(feat, 4);
		var vals1 = new Object[] { vals[0], vals[1] };
		var vals2 = new Object[] { vals[2], vals[3] };

		overrideSimilarityForFeatVal(vals1[0], vals2[0], false);
		assertFeatValSimilarity(vals1[0], vals2[0], false);

		overrideSimilarityForFeatVal(vals1[1], vals2[0], false);
		assertFeatValSimilarity(vals1[1], vals2[0], false);

		overrideSimilarityForFeatVal(vals1[0], vals2[1], false);
		assertFeatValSimilarity(vals1[0], vals2[1], false);

		overrideSimilarityForFeatVal(vals1[1], vals2[1], false);
		assertFeatValSimilarity(vals1[1], vals2[1], false);

		setFeatVal(obj1, feat, vals1);
		setFeatVal(obj2, feat, vals2);

//		this.testSimilarity(obj1, obj2, (Class<? extends EObject>) eCls.getInstanceClass(), feat);
	}

	// TODO Fix test templates, enable commented out tests

	@Test
	public void test() {
		var helper = new JaMoPPHelper();

		for (var eCls : helper.getAllEClasses().stream().filter((e) -> !e.isAbstract() && !e.isInterface())
				.collect(Collectors.toUnmodifiableList())) {
			for (var feat : eCls.getEAllStructuralFeatures().stream()
					.filter((f) -> !f.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class))
					.collect(Collectors.toUnmodifiableList())) {
				System.out.println(eCls.getInstanceClass().getSimpleName() + "." + feat.getName());

				this.getSCC().resetOverrides();

				if (!feat.isMany()) {
					testSameX(eCls, feat);
					this.getSCC().resetOverrides();
					testDifferentX(eCls, feat);
					this.getSCC().resetOverrides();
					testNullCheckX(eCls, feat);
				} else {
					testSameXs(eCls, feat);
					this.getSCC().resetOverrides();
					testDifferentXs(eCls, feat);
				}

				this.getSCC().resetOverrides();
			}
		}
	}
}
