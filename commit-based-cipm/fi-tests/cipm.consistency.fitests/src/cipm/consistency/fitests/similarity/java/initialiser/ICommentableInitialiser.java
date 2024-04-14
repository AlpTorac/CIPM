package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;

public interface ICommentableInitialiser extends EObjectInitialiser {
	@Override
	public Commentable instantiate();
	
	@Override
	public default Commentable clone(EObject obj) {
		return (Commentable) EObjectInitialiser.super.clone(obj);
	}
}
