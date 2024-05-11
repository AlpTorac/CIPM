package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.modifiers.ModifiersFactory;

public class ModifierFactory implements IModifierFactory {
	@Override
	public ModifiersFactory getFactory() {
		return ModifiersFactory.eINSTANCE;
	}
}
