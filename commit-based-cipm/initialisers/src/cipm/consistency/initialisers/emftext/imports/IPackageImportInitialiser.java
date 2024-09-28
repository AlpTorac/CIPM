package cipm.consistency.initialisers.emftext.imports;

import org.emftext.language.java.imports.PackageImport;

public interface IPackageImportInitialiser extends IImportInitialiser {
	@Override
	public PackageImport instantiate();

}
