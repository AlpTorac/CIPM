package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface ITypeParametrizableInitialiser extends ICommentableInitialiser {
	public default void addTypeParameter(TypeParametrizable tp, TypeParameter param) {
		if (param != null) {
			tp.getTypeParameters().add(param);
			assert tp.getTypeParameters().contains(param);
		}
	}
	
	public default void addTypeParameters(TypeParametrizable tp, TypeParameter[] params) {
		this.addXs(tp, params, (o,p)->this.addTypeParameter(o, p));
	}
}
