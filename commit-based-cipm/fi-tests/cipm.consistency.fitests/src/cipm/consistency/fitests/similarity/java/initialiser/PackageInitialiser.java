package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public class PackageInitialiser implements IPackageInitialiser {
	private Resource res;
	
	@Override
	public boolean isSetDefaultName() {
		return true;
	}

	@Override
	public Package instantiatePackage(Map<ResourceParameters, Object> pacParam) {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createPackage();
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
