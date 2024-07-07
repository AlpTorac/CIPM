package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IElementReferenceInitialiser extends IReferenceInitialiser {
    @Override
    public ElementReference instantiate();
    @ModificationMethod
	public default boolean setContainedTarget(ElementReference eref, ReferenceableElement re) {
		if (re != null) {
			eref.setContainedTarget(re);
			return eref.getContainedTarget().equals(re);
		}
		return true;
	}
    @ModificationMethod
	public default boolean setTarget(ElementReference eref, ReferenceableElement re) {
		if (re != null) {
			eref.setTarget(re);
			return eref.getTarget().equals(re);
		}
		return true;
	}
}
