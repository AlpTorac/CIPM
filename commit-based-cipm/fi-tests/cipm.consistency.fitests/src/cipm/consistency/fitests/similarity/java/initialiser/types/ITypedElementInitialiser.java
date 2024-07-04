package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

import org.emftext.language.java.types.TypedElement;

public interface ITypedElementInitialiser extends ICommentableInitialiser {
    @Override
    public TypedElement instantiate();
	public default boolean setTypeReference(TypedElement te, TypeReference tref) {
		if (tref != null) {
			te.setTypeReference(tref);
			return te.getTypeReference().equals(tref);
		}
		return true;
	}
}
