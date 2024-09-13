package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IModifiableInitialiser extends ICommentableInitialiser {
	@Override
	public Modifiable instantiate();

	public default boolean addModifier(Modifiable modifiable, Modifier modifier) {
		if (modifier != null) {
			modifiable.getModifiers().add(modifier);
			return modifiable.getModifiers().contains(modifier);
		}
		return true;
	}

	public default boolean addModifiers(Modifiable modifiable, Modifier[] modifiers) {
		return this.doMultipleModifications(modifiable, modifiers, this::addModifier);
	}
}
