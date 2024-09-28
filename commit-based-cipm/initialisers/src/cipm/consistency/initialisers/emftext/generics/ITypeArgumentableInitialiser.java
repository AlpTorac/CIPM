package cipm.consistency.initialisers.emftext.generics;

import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface ITypeArgumentableInitialiser extends ICommentableInitialiser {
	@Override
	public TypeArgumentable instantiate();

	public default boolean addTypeArgument(TypeArgumentable ta, TypeArgument tArg) {
		if (tArg != null) {
			ta.getTypeArguments().add(tArg);
			return ta.getTypeArguments().contains(tArg);
		}
		return true;
	}
}
