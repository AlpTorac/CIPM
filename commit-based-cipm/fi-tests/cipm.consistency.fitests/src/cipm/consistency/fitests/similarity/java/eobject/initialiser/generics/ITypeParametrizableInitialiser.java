package cipm.consistency.fitests.similarity.java.eobject.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface ITypeParametrizableInitialiser extends ICommentableInitialiser {
	@Override
	public TypeParametrizable instantiate();

	public default boolean addTypeParameter(TypeParametrizable tp, TypeParameter typeParam) {
		if (typeParam != null) {
			tp.getTypeParameters().add(typeParam);
			return tp.getTypeParameters().contains(typeParam);
		}
		return true;
	}

	public default boolean addTypeParameters(TypeParametrizable tp, TypeParameter[] typeParams) {
		return this.doMultipleModifications(tp, typeParams, this::addTypeParameter);
	}
}
