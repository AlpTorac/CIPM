package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface ISelfReferenceInitialiser extends IReferenceInitialiser {
    @Override
    public SelfReference instantiate();
    @ModificationMethod
	public default boolean setSelf(SelfReference sref, Self self) {
		if (self != null) {
			sref.setSelf(self);
			return sref.getSelf().equals(self);
		}
		return true;
	}
}
