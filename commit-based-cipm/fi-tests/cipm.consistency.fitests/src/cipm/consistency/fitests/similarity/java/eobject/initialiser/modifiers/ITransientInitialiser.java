package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.Transient;

public interface ITransientInitialiser extends IModifierInitialiser {
	@Override
	public Transient instantiate();

}
