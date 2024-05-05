package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.java.initialiser.ImportInitialiser;

public class PackageImportInitialiser extends ImportInitialiser implements IPackageImportInitialiser {
	@Override
	public IPackageImportInitialiser newInitialiser() {
		return new PackageImportInitialiser();
	}

	@Override
	public PackageImport instantiate() {
		return ImportsFactory.eINSTANCE.createPackageImport();
	}
}