package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;

public interface ISelfReferenceInitialiser extends IReferenceInitialiser {
	public default boolean setSelf(SelfReference sref, Self self) {
		if (self != null) {
			sref.setSelf(self);
			return sref.getSelf().equals(self);
		}
		return true;
	}
}
