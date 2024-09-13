package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Transitive;

public interface ITransitiveInitialiser extends IModuleRequiresModifierInitialiser {
	@Override
	public Transitive instantiate();

}
