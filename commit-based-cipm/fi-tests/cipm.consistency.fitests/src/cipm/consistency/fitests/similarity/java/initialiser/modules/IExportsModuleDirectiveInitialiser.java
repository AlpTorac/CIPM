package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ExportsModuleDirective;

public interface IExportsModuleDirectiveInitialiser extends IAccessProvidingModuleDirectiveInitialiser {
	@Override
	public ExportsModuleDirective instantiate();
}
