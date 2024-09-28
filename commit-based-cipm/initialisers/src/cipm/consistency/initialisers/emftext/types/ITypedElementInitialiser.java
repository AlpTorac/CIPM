package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface ITypedElementInitialiser extends ICommentableInitialiser {
	@Override
	public TypedElement instantiate();

	public default boolean setTypeReference(TypedElement te, TypeReference tRef) {
		if (tRef != null) {
			te.setTypeReference(tRef);
			return te.getTypeReference().equals(tRef);
		}
		return true;
	}
}
