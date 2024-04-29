package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

public interface ITypeParametrizableInitialiser extends ICommentableInitialiser {
	public default void addTypeParameter(TypeParametrizable tp, TypeParameter param) {
		if (param != null) {
			tp.getTypeParameters().add(param);
			assert tp.getTypeParameters().contains(param);
		}
	}
}
