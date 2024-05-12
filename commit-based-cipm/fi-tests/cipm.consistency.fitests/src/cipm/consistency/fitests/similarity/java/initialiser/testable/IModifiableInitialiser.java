package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IModifiableInitialiser extends ICommentableInitialiser {
	public default void addModifier(Modifiable modifiable, Modifier modifier) {
		if (modifier != null) {
			modifiable.getModifiers().add(modifier);
			assert modifiable.getModifiers().contains(modifier);
		}
	}
	
	public default void addModifiers(Modifiable modifiable, Modifier[] modifiers) {
		this.addXs(modifiable, modifiers, this::addModifier);
	}
 }
