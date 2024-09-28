package cipm.consistency.initialisers.eobject.java.modules;

import org.emftext.language.java.modules.ModuleDirective;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IModuleDirectiveInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleDirective instantiate();

}
