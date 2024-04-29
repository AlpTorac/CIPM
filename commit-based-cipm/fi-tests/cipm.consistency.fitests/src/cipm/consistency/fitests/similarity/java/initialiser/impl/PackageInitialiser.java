package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IPackageInitialiser;

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
