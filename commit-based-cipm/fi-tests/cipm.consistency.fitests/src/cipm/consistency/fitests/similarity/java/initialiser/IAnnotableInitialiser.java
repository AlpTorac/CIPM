package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Collection;
import java.util.List;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;

public interface IAnnotableInitialiser extends ICommentableInitialiser {
	public default void addAnnotation(Annotable aObj, AnnotationInstance ai) {
		if (ai != null) {
			aObj.getAnnotations().add(ai);
			assert aObj.getAnnotations().contains(ai);
		}
	}
	
	public default void addAnnotations(Annotable aObj, AnnotationInstance[] ais) {
		if (ais != null) {
			this.addAnnotations(aObj, List.of(ais));
		}
	}
	
	public default void addAnnotations(Annotable aObj, Collection<AnnotationInstance> ais) {
		if (ais != null) {
			for (var ai : ais) {
				this.addAnnotation(aObj, ai);
			}
			
			assert aObj.getAnnotations().containsAll(ais);
		}
	}
}
