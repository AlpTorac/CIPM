package cipm.consistency.initialisers.jamopp.references;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;

public interface IElementReferenceInitialiser extends IReferenceInitialiser {
	@Override
	public ElementReference instantiate();

	public default boolean setContainedTarget(ElementReference eref, ReferenceableElement conTarget) {
		if (conTarget != null) {
			eref.setContainedTarget(conTarget);
			return eref.getContainedTarget().equals(conTarget);
		}
		return true;
	}

	public default boolean setTarget(ElementReference eref, ReferenceableElement target) {
		if (target != null) {
			eref.setTarget(target);
			return eref.getTarget().equals(target);
		}
		return true;
	}
}
