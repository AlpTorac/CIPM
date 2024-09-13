package cipm.consistency.fitests.similarity.java.eobject.initialiser.modules;

import org.emftext.language.java.modules.OpensModuleDirective;

public interface IOpensModuleDirectiveInitialiser extends IAccessProvidingModuleDirectiveInitialiser {
	@Override
	public OpensModuleDirective instantiate();

}
