package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.Transient;

public interface ITransientInitialiser extends IModifierInitialiser {
	@Override
	public Transient instantiate();

}
