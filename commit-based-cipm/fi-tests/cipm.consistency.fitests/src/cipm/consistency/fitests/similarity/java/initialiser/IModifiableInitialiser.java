package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;

public interface IModifiableInitialiser extends ICommentableInitialiser {
	@Override
	public Modifiable instantiate();
	
	@Override
	public default Modifiable minimalInstantiation() {
		return (Modifiable) ICommentableInitialiser.super.minimalInstantiation();
	}
	
	// ToDo: Make a factory for Modifier
	public default void addModifier(Modifiable modifiable, Modifier modifier) {
		if (modifier != null) {
			modifiable.getModifiers().add(modifier);
			assert modifiable.getModifiers().contains(modifier);
		}
	}
 }
