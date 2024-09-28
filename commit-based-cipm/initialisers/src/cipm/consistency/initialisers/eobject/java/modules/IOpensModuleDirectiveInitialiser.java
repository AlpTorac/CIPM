package cipm.consistency.initialisers.eobject.java.modules;

import org.emftext.language.java.modules.OpensModuleDirective;

public interface IOpensModuleDirectiveInitialiser extends IAccessProvidingModuleDirectiveInitialiser {
	@Override
	public OpensModuleDirective instantiate();

}
