package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IAnnotableInitialiser extends ICommentableInitialiser {
	@Override
	public Annotable instantiate();

	public default boolean addAnnotation(Annotable aObj, AnnotationInstance anno) {
		if (anno != null) {
			aObj.getAnnotations().add(anno);
			return aObj.getAnnotations().contains(anno);
		}

		return true;
	}

	public default boolean addAnnotations(Annotable aObj, AnnotationInstance[] annos) {
		return this.doMultipleModifications(aObj, annos, this::addAnnotation);
	}
}