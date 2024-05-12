package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;

public interface UsesPackageImports {
	public default PackageImport createMinimalPackageImport(String[] nss) {
		var initialiser = new PackageImportInitialiser();
		PackageImport result = initialiser.instantiate();
		
		initialiser.minimalInitialisation(result);
		initialiser.initialiseNamespaces(result, nss);
		
		return result;
	}
}