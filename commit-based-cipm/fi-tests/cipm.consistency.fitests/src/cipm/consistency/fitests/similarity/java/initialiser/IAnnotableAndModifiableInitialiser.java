package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;

public interface IAnnotableAndModifiableInitialiser extends ICommentableInitialiser {
	public default void addModifier(AnnotableAndModifiable aam, Modifier modif) {
		if (modif != null) {
			aam.addModifier(modif);
			assert aam.getAnnotationsAndModifiers().contains(modif);
			assert aam.hasModifier(modif.getClass());
			assert aam.getModifiers().contains(modif);
		}
	}
	
	public default void addAnnotationInstance(AnnotableAndModifiable aam, AnnotationInstance ai) {
		if (aam != null) {
			aam.getAnnotationInstances().add(ai);
			assert aam.getAnnotationInstances().contains(ai);
		}
	}
	
	public default void makePrivate(AnnotableAndModifiable aam) {
		aam.makePrivate();
	}
	
	public default void makeProtected(AnnotableAndModifiable aam) {
		aam.makeProtected();
	}
	
	public default void makePublic(AnnotableAndModifiable aam) {
		aam.makePublic();
	}
}
