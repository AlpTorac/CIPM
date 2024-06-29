package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IAnnotableInitialiser extends ICommentableInitialiser {
	public default boolean addAnnotation(Annotable aObj, AnnotationInstance ai) {
		if (ai != null) {
			aObj.getAnnotations().add(ai);
			return aObj.getAnnotations().contains(ai);
		}
		
		return false;
	}
	
	public default boolean addAnnotations(Annotable aObj, AnnotationInstance[] ais) {
		return this.addXs(aObj, ais, this::addAnnotation);
	}
}
