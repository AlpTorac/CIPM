package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public class PackageInitialiser implements IPackageInitialiser {
	@Override
	public boolean isSetDefaultName() {
		return true;
	}

	@Override
	public Package instantiatePackage(Map<ResourceParameters, Object> pacParam) {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createPackage();
	}
}
