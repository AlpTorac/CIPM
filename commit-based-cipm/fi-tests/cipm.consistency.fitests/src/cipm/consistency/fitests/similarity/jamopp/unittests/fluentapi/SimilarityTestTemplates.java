package cipm.consistency.fitests.similarity.jamopp.unittests.fluentapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fluentapi.api.ApiFactory;

public class SimilarityTestTemplates extends AbstractJaMoPPSimilarityTest {
	private void setFeatVal(EObject objToModify, EStructuralFeature feat, Object featVal) {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		if (!feat.isMany()) {
			api.xWithFeat(objToModify, feat, featVal);
		} else {
			api.xWithExactFeat(objToModify, feat, featVal);
		}
	}

	private Object cloneFeatVal(Object obj) {
		if (obj instanceof EObject)
			return EcoreUtil.copy((EObject) obj);
		return obj;
	}

	private Object[] cloneFeatVal(Object[] objs) {
		var objArr = new Object[objs.length];
		for (int i = 0; i < objs.length; i++) {
			objArr[i] = cloneFeatVal(objs[i]);
		}
		return objArr;
	}

	@Test
	public void testSameX(EClass eCls, EStructuralFeature feat, Object featVal) {
		if (feat.isMany())
			throw new IllegalArgumentException("Feat must be single-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls);
		setFeatVal(obj1, feat, cloneFeatVal(featVal));
		var obj2 = api.newX(eCls);
		setFeatVal(obj2, feat, cloneFeatVal(featVal));
		this.testSimilarity(obj1, obj2, feat);
	}

	@Test
	public void testDifferentX(EClass eCls, EStructuralFeature feat, Object lhsFeatVal, Object rhsFeatVal) {
		if (feat.isMany())
			throw new IllegalArgumentException("Feat must be single-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls);
		setFeatVal(obj1, feat, cloneFeatVal(lhsFeatVal));
		var obj2 = api.newX(eCls);
		setFeatVal(obj2, feat, cloneFeatVal(rhsFeatVal));
		this.testSimilarity(obj1, obj2, feat);
	}

	@Test
	public void testNullCheckX(EClass eCls, EStructuralFeature feat, Object featVal) {
		if (feat.isMany())
			throw new IllegalArgumentException("Feat must be single-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls);
		setFeatVal(obj1, feat, cloneFeatVal(featVal));
		var obj2 = api.newX(eCls);
		this.testSimilarity(obj1, obj2, feat);
	}

	@Test
	public void testSameXs(EClass eCls, EStructuralFeature feat, Object[] featVal) {
		if (!feat.isMany())
			throw new IllegalArgumentException("Feat must be many-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls);
		setFeatVal(obj1, feat, cloneFeatVal(featVal));
		var obj2 = api.newX(eCls);
		setFeatVal(obj2, feat, cloneFeatVal(featVal));
		this.testSimilarity(obj1, obj2, feat);
	}

	@Test
	public void testDifferentXs(EClass eCls, EStructuralFeature feat, Object[] lhsFeatVal, Object[] rhsFeatVal) {
		if (!feat.isMany())
			throw new IllegalArgumentException("Feat must be many-valued");
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var obj1 = api.newX(eCls);
		setFeatVal(obj1, feat, cloneFeatVal(lhsFeatVal));
		var obj2 = api.newX(eCls);
		setFeatVal(obj2, feat, cloneFeatVal(rhsFeatVal));
		this.testSimilarity(obj1, obj2, feat);
	}
}
