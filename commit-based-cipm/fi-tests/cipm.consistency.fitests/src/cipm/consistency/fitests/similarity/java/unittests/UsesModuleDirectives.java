package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.ExportsModuleDirective;
import org.emftext.language.java.modules.OpensModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.modules.ExportsModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.OpensModuleDirectiveInitialiser;

public interface UsesModuleDirectives extends UsesPackages, UsesModuleReferences {
	public default ExportsModuleDirective createMinimalEMD(Package pac) {
		var init = new ExportsModuleDirectiveInitialiser();
		ExportsModuleDirective result = init.instantiate();
		init.minimalInitialisation(result);
		init.setAccessablePackage(result, pac);
		return result;
	}
	
	public default OpensModuleDirective createMinimalOMD(Package pac) {
		var init = new OpensModuleDirectiveInitialiser();
		OpensModuleDirective result = init.instantiate();
		init.minimalInitialisation(result);
		init.setAccessablePackage(result, pac);
		return result;
	}
	
	public default ExportsModuleDirective createMinimalEMD(String[] nss) {
		return this.createMinimalEMD(this.createMinimalPackage(nss));
	}
	
	public default OpensModuleDirective createMinimalOMD(String[] nss) {
		return this.createMinimalOMD(this.createMinimalPackage(nss));
	}
}
