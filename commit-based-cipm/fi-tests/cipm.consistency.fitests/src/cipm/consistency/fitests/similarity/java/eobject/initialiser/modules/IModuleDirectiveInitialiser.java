package cipm.consistency.fitests.similarity.java.eobject.initialiser.modules;

import org.emftext.language.java.modules.ModuleDirective;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IModuleDirectiveInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleDirective instantiate();

}
