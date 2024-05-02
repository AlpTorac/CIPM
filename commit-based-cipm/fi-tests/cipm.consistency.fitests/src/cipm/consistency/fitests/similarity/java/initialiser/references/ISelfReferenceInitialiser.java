package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;

import cipm.consistency.fitests.similarity.java.initialiser.IReferenceInitialiser;

public interface ISelfReferenceInitialiser extends IReferenceInitialiser {
	public default void setSelf(SelfReference sref, Self self) {
		if (self != null) {
			sref.setSelf(self);
			assert sref.getSelf().equals(self);
		}
	}
}
