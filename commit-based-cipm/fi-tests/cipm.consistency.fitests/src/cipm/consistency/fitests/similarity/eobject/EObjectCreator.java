package cipm.consistency.fitests.similarity.eobject;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class EObjectCreator {

	/*
	 * TODO Remove EObjectCreator and EObjectInstantiator, simply use the Fluent API
	 * (without encapsulating / hiding it). Use the adaptations directly in unit
	 * tests, so that there are no surprises later on.
	 */

	private boolean actionsPerformedAsExpected = true;
	private EObject currentEObject;

	public abstract EObjectCreator setFeatureValue(EStructuralFeature feat, Object newFeatVal);

	public abstract EObjectCreator addFeatureValue(EStructuralFeature feat, Object valToAdd);

	public abstract EObjectCreator removeFeatureValue(EStructuralFeature feat, Object valToRemove);

	public EObjectCreator addFeatureValue(EStructuralFeature feat, Object[] valToAdd) {
		for (var val : valToAdd) {
			addFeatureValue(feat, val);
		}
		return this;
	}

	public EObjectCreator removeFeatureValue(EStructuralFeature feat, Object[] valToRemove) {
		for (var val : valToRemove) {
			removeFeatureValue(feat, val);
		}
		return this;
	}

	public EObjectCreator initialiseEObject(EClass eCls) {
		currentEObject = createEObject(eCls);
		return this;
	}

	public <T extends EObject> EObjectCreator initialiseEObject(Class<T> eObjCls) {
		currentEObject = createEObject(eObjCls);
		return this;
	}

	public abstract EObject createEObject(EClass eCls);

	public abstract <T extends EObject> T createEObject(Class<T> eObjCls);

	protected abstract EClass getEClassFor(Class<?> eObjCls);

	public abstract List<EClass> getAllSupportedEClasses();

	public abstract List<Class<?>> getAllSupportedTypes();

	public boolean areActionsPerformedAsExpected() {
		return this.actionsPerformedAsExpected;
	}

	protected void setActionsPerformedAsExpected(boolean actionsPerformedAsExpected) {
		this.actionsPerformedAsExpected = actionsPerformedAsExpected;
	}

	protected EObject getCurrentEObject() {
		return this.currentEObject;
	}
}
