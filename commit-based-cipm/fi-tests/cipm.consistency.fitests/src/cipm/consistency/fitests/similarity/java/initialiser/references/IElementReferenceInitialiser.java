package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;

public interface IElementReferenceInitialiser extends IReferenceInitialiser {
	public default void setContainedTarget(ElementReference eref, ReferenceableElement re) {
		if (re != null) {
			eref.setContainedTarget(re);
			assert eref.getContainedTarget().equals(re);
		}
	}
	
	public default void setTarget(ElementReference eref, ReferenceableElement re) {
		if (re != null) {
			eref.setTarget(re);
			assert eref.getTarget().equals(re);
		}
	}
}
