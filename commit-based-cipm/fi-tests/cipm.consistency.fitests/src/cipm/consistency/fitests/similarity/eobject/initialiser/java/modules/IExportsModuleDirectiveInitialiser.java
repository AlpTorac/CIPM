package cipm.consistency.fitests.similarity.eobject.initialiser.java.modules;

import org.emftext.language.java.modules.ExportsModuleDirective;

public interface IExportsModuleDirectiveInitialiser extends IAccessProvidingModuleDirectiveInitialiser {
	@Override
	public ExportsModuleDirective instantiate();
}
