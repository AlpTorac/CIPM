package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface ITypeParametrizableInitialiser extends ICommentableInitialiser {
    @Override
    public TypeParametrizable instantiate();
    @ModificationMethod
	public default boolean addTypeParameter(TypeParametrizable tp, TypeParameter param) {
		if (param != null) {
			tp.getTypeParameters().add(param);
			return tp.getTypeParameters().contains(param);
		}
		return true;
	}
	
	public default boolean addTypeParameters(TypeParametrizable tp, TypeParameter[] params) {
		return this.addXs(tp, params, this::addTypeParameter);
	}
}
