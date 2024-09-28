package cipm.consistency.initialisers.eobject.java.imports;

import org.emftext.language.java.imports.PackageImport;

public interface IPackageImportInitialiser extends IImportInitialiser {
	@Override
	public PackageImport instantiate();

}
