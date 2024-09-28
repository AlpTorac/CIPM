package cipm.consistency.initialisers.eobject.java.modifiers;

import org.emftext.language.java.modifiers.Transitive;

public interface ITransitiveInitialiser extends IModuleRequiresModifierInitialiser {
	@Override
	public Transitive instantiate();

}
