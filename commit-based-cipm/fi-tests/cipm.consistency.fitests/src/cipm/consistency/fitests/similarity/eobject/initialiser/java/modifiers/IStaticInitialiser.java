package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Static;

public interface IStaticInitialiser extends IModifierInitialiser, IModuleRequiresModifierInitialiser {
	@Override
	public Static instantiate();
}
