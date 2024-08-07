package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.Static;

public interface IStaticInitialiser extends IModifierInitialiser, IModuleRequiresModifierInitialiser {
	@Override
	public Static instantiate();
}
