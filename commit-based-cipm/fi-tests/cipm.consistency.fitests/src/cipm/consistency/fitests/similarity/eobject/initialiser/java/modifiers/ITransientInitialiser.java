package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Transient;

public interface ITransientInitialiser extends IModifierInitialiser {
	@Override
	public Transient instantiate();

}
