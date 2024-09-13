package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.Transitive;

public interface ITransitiveInitialiser extends IModuleRequiresModifierInitialiser {
	@Override
	public Transitive instantiate();

}
