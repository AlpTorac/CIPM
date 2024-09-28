package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.Transitive;

public interface ITransitiveInitialiser extends IModuleRequiresModifierInitialiser {
	@Override
	public Transitive instantiate();

}
