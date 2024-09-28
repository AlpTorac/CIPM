package cipm.consistency.initialisers.eobject.java.modules;

import org.emftext.language.java.modules.UsesModuleDirective;

import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IUsesModuleDirectiveInitialiser extends IModuleDirectiveInitialiser, ITypedElementInitialiser {
	@Override
	public UsesModuleDirective instantiate();
}
