package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.Final;

public interface IFinalInitialiser extends IModifierInitialiser {
	@Override
	public Final instantiate();

}