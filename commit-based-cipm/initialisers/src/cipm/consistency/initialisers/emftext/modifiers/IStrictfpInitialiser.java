package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.Strictfp;

public interface IStrictfpInitialiser extends IModifierInitialiser {
	@Override
	public Strictfp instantiate();

}
