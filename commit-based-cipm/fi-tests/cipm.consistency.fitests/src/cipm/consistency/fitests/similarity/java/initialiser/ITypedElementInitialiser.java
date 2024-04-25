package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;

public interface ITypedElementInitialiser extends ICommentableInitialiser {
	@Override
	public TypedElement instantiate();
	
	@Override
	public default TypedElement minimalInstantiation() {
		return (TypedElement) ICommentableInitialiser.super.minimalInstantiation();
	}
	
	public default void setTypeReference(TypedElement te, TypeReference tref) {
		if (tref != null) {
			te.setTypeReference(tref);
			assert te.getTypeReference().equals(tref);
		}
	}
}
