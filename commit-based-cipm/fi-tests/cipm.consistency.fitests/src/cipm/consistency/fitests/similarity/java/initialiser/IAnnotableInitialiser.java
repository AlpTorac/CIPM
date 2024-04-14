package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;

public interface IAnnotableInitialiser extends ICommentableInitialiser {
	@Override
	public Annotable instantiate();
	
	@Override
	public default Annotable clone(EObject obj) {
		return (Annotable) ICommentableInitialiser.super.clone(obj);
	}
	
	public default void addAnnotation(Annotable aObj, AnnotationInstance ai) {
		if (ai != null) {
			aObj.getAnnotations().add(ai);
			assert aObj.getAnnotations().contains(ai);
		}
	}
	
	public default void addAnnotations(Annotable aObj, AnnotationInstance[] ais) {
		if (ais != null) {
			for (var ai : ais) {
				this.addAnnotation(aObj, ai);
			}
			
			assert aObj.getAnnotations().containsAll(List.of(ais));
		}
	}
}
