package cipm.consistency.fitests.similarity.java.eobject.initialiser.modules;

import org.emftext.language.java.modules.UsesModuleDirective;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypedElementInitialiser;

public interface IUsesModuleDirectiveInitialiser extends IModuleDirectiveInitialiser, ITypedElementInitialiser {
	@Override
	public UsesModuleDirective instantiate();
}
