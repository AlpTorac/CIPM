package cipm.consistency.fitests.similarity.jamopp;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fitests.similarity.eobject.EObjectCreator;
import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.api.FluentEObjectAPI;

public class FluentJaMoPPEObjectCreator extends EObjectCreator {
	private static final FluentEObjectAPI api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EObject> T createEObject(Class<T> eObjCls) {
		return (T) createEObject(getEClassFor(eObjCls));
	}

	@Override
	protected EClass getEClassFor(Class<?> eObjCls) {
		return JaMoPPHelper.getEClassForJavaElement(eObjCls);
	}

	@Override
	public EObject createEObject(EClass eCls) {
		return api.newX(eCls).createNow();
	}

	@Override
	public List<Class<?>> getAllSupportedTypes() {
		return api.getAllSupportedClasses();
	}

	@Override
	public List<EClass> getAllSupportedEClasses() {
		return this.getAllSupportedTypes().stream().map((cls) -> getEClassFor(cls)).collect(Collectors.toList());
	}

	@Override
	public FluentJaMoPPEObjectCreator setFeatureValue(EObject obj, EStructuralFeature feat, Object newFeatVal) {
		api.xWithFeat(obj, feat, newFeatVal);
		this.setActionsPerformedAsExpected(obj.eGet(feat) == newFeatVal || obj.eGet(feat).equals(newFeatVal));
		return this;
	}

	@Override
	public FluentJaMoPPEObjectCreator addFeatureValue(EObject obj, EStructuralFeature feat, Object valToAdd) {
		api.xWithAddedFeat(obj, feat, valToAdd);
		this.setActionsPerformedAsExpected(((List) obj.eGet(feat)).contains(valToAdd));
		return this;
	}

	@Override
	public FluentJaMoPPEObjectCreator removeFeatureValue(EObject obj, EStructuralFeature feat, Object valToRemove) {
		api.xWithRemovedFeat(obj, feat, valToRemove);
		this.setActionsPerformedAsExpected(!((List) obj.eGet(feat)).contains(valToRemove));
		return this;
	}
}
