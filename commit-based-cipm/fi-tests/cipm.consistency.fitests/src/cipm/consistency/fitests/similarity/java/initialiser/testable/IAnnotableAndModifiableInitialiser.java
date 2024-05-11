package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;
import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserVisibilityModifier;

public interface IAnnotableAndModifiableInitialiser extends ICommentableInitialiser {
	public default void addModifier(AnnotableAndModifiable aam, Modifier modif) {
		if (modif != null) {
			aam.addModifier(modif);
			assert aam.getAnnotationsAndModifiers().contains(modif);
			assert aam.hasModifier(modif.getClass());
			assert aam.getModifiers().contains(modif);
		}
	}
	
	public default void addModifiers(AnnotableAndModifiable aam, Modifier[] modifs) {
		this.addXs(aam, modifs, this::addModifier);
	}
	
	public default void addAnnotationInstance(AnnotableAndModifiable aam, AnnotationInstance ai) {
		if (aam != null) {
			aam.getAnnotationsAndModifiers().add(ai);
			assert aam.getAnnotationsAndModifiers().contains(ai);
			assert aam.getAnnotationInstances().contains(ai);
		}
	}
	
	public default void addAnnotationInstances(AnnotableAndModifiable aam, AnnotationInstance[] ais) {
		this.addXs(aam, ais, this::addAnnotationInstance);
	}
	
	public default void setVisibility(AnnotableAndModifiable aam, InitialiserVisibilityModifier modifier) {
		if (modifier != null) {
			switch (modifier) {
				case PRIVATE: 
					this.makePrivate(aam);
					assert aam.isPrivate();
					break;
				case PROTECTED: 
					this.makeProtected(aam); 
					assert aam.isProtected();
					break;
				case PUBLIC: 
					this.makePublic(aam); 
					assert aam.isPublic();
					break;
				default: throw new IllegalArgumentException("Invalid InitialiserVisibilityModifier");
			}
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
