package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

public class PackageInitialiser implements IPackageInitialiser {
	@Override
	public Package instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createPackage();
	}

	@Override
	public IPackageInitialiser newInitialiser() {
		return new PackageInitialiser();
	}
}
