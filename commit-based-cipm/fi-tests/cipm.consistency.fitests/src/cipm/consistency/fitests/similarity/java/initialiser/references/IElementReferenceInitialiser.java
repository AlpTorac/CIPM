package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;

public interface IElementReferenceInitialiser extends IReferenceInitialiser {
	public default boolean setContainedTarget(ElementReference eref, ReferenceableElement re) {
		if (re != null) {
			eref.setContainedTarget(re);
			return eref.getContainedTarget().equals(re);
		}
		return true;
	}
	
	public default boolean setTarget(ElementReference eref, ReferenceableElement re) {
		if (re != null) {
			eref.setTarget(re);
			return eref.getTarget().equals(re);
		}
		return true;
	}
}
