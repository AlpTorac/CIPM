package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.IPackageInitialiser;

public class PackageInitialiser implements IPackageInitialiser, IInitialiser<Package> {
	@Override
	public Package instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createPackage();
	}
}
