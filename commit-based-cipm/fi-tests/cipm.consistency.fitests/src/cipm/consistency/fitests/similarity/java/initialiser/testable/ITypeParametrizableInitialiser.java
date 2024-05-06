package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface ITypeParametrizableInitialiser extends ICommentableInitialiser {
	public default void addTypeParameter(TypeParametrizable tp, TypeParameter param) {
		if (param != null) {
			tp.getTypeParameters().add(param);
			assert tp.getTypeParameters().contains(param);
		}
	}
}