package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.List;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;

public interface IAnnotableInitialiser extends ICommentableInitialiser {
	@Override
	public Annotable getCurrentObject();
	
	@Override
	public default Annotable build() {
		return (Annotable) ICommentableInitialiser.super.build();
	}
	
	public default void addAnnotation(AnnotationInstance ai) {
		if (ai != null) {
			this.getCurrentObject().getAnnotations().add(ai);
			assert this.getCurrentObject().getAnnotations().contains(ai);
		}
	}
	
	public default void addAnnotations(AnnotationInstance[] ais) {
		if (ais != null) {
			for (var ai : ais) {
				this.addAnnotation(ai);
			}
			
			assert this.getCurrentObject().getAnnotations().containsAll(List.of(ais));
		}
	}
}
