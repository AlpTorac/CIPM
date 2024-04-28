package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;
import org.emftext.language.java.statements.StatementListContainer;

public interface ITypeArgumentableInitialiser extends ICommentableInitialiser {
	@Override
	public TypeArgumentable instantiate();
	
	public default void addTypeArgument(TypeArgumentable ta, TypeArgument targ) {
		if (targ != null) {
			ta.getTypeArguments().add(targ);
			assert ta.getTypeArguments().contains(targ);
		}
	}
}
