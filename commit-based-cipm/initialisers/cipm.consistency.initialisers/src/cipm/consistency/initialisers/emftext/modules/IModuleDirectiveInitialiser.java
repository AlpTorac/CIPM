package cipm.consistency.initialisers.emftext.modules;

import org.emftext.language.java.modules.ModuleDirective;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IModuleDirectiveInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleDirective instantiate();

}
