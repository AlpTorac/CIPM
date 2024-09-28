package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.Default;

public interface IDefaultInitialiser extends IModifierInitialiser {
	@Override
	public Default instantiate();

}
