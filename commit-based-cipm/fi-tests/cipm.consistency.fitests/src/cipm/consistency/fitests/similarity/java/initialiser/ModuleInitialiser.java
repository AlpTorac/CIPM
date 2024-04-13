package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public class ModuleInitialiser implements IModuleInitialiser {
	private Resource res;
	
	@Override
	public boolean isSetDefaultName() {
		return true;
	}

	@Override
	public IPackageInitialiser getPackageInitialiserFor(Map<ResourceParameters, Object> pacParam) {
		return new PackageInitialiser();
	}

	@Override
	public Module instantiateModule(Map<ResourceParameters, Object> modParam) {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createModule();
	}
	
	@Override
	public void setResource(Resource res) {
		this.res = res;
	}

	@Override
	public Resource getResource() {
		return this.res;
	}
}
