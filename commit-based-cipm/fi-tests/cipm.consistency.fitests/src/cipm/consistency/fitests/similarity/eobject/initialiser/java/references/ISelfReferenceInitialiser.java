package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;

public interface ISelfReferenceInitialiser extends IReferenceInitialiser {
	@Override
	public SelfReference instantiate();

	public default boolean setSelf(SelfReference sref, Self self) {
		if (self != null) {
			sref.setSelf(self);
			return sref.getSelf().equals(self);
		}
		return true;
	}
}
