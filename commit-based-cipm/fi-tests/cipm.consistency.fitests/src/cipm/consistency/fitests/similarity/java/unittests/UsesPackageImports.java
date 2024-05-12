package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.java.initialiser.imports.IPackageImportInitialiser;

public interface UsesPackageImports {
	public default PackageImport createMinimalPackageImport(IPackageImportInitialiser initialiser, String[] nss) {
		PackageImport result = initialiser.instantiate();
		
		initialiser.minimalInitialisation(result);
		initialiser.initialiseNamespaces(result, nss);
		
		return result;
	}
}