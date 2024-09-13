package cipm.consistency.fitests.similarity.java.eobject.initialiser.imports;

import org.emftext.language.java.imports.PackageImport;

public interface IPackageImportInitialiser extends IImportInitialiser {
	@Override
	public PackageImport instantiate();

}
