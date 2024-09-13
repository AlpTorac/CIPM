package cipm.consistency.fitests.similarity.java.eobject.initialiser.imports;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class PackageImportInitialiser extends AbstractInitialiserBase implements IPackageImportInitialiser {
	@Override
	public IPackageImportInitialiser newInitialiser() {
		return new PackageImportInitialiser();
	}

	@Override
	public PackageImport instantiate() {
		return ImportsFactory.eINSTANCE.createPackageImport();
	}
}