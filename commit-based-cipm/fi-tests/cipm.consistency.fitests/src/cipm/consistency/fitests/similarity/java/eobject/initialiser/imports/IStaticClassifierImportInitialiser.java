package cipm.consistency.fitests.similarity.java.eobject.initialiser.imports;

import org.emftext.language.java.imports.StaticClassifierImport;

public interface IStaticClassifierImportInitialiser extends IStaticImportInitialiser {
	@Override
	public StaticClassifierImport instantiate();
}
