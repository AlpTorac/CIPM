package cipm.consistency.fitests.similarity.eobject.initialiser.java.modules;

import org.emftext.language.java.modules.ModuleDirective;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IModuleDirectiveInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleDirective instantiate();

}
