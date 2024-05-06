package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface ITypedElementInitialiser extends ICommentableInitialiser {
	public default void setTypeReference(TypedElement te, TypeReference tref) {
		if (tref != null) {
			te.setTypeReference(tref);
			assert te.getTypeReference().equals(tref);
		}
	}
}
