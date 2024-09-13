package cipm.consistency.fitests.similarity.eobject.initialiser.java.modules;

import org.emftext.language.java.modules.UsesModuleDirective;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IUsesModuleDirectiveInitialiser extends IModuleDirectiveInitialiser, ITypedElementInitialiser {
	@Override
	public UsesModuleDirective instantiate();
}
