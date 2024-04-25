package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;

public interface ITypeArgumentableInitialiser extends ICommentableInitialiser {
	@Override
	public TypeArgumentable instantiate();
	
	@Override
	public default TypeArgumentable minimalInstantiation() {
		return (TypeArgumentable) ICommentableInitialiser.super.minimalInstantiation();
	}
	
	public default void addTypeArgument(TypeArgumentable ta, TypeArgument targ) {
		if (targ != null) {
			ta.getTypeArguments().add(targ);
			assert ta.getTypeArguments().contains(targ);
		}
	}
}
