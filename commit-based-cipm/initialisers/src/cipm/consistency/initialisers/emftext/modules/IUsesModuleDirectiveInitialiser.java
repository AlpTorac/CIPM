package cipm.consistency.initialisers.emftext.modules;

import org.emftext.language.java.modules.UsesModuleDirective;

import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IUsesModuleDirectiveInitialiser extends IModuleDirectiveInitialiser, ITypedElementInitialiser {
	@Override
	public UsesModuleDirective instantiate();
}
