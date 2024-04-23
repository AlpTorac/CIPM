package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.types.Type;

public interface ITypeInitialiser extends ICommentableInitialiser {
	@Override
	public Type instantiate();
}
