package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.Transient;

public interface ITransientInitialiser extends IModifierInitialiser {
	@Override
	public Transient instantiate();

}
