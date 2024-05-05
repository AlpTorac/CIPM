package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.StaticClassifierImport;

import cipm.consistency.fitests.similarity.java.initialiser.ImportInitialiser;

public class StaticClassifierImportInitialiser extends ImportInitialiser implements IStaticClassifierImportInitialiser {
	@Override
	public IStaticClassifierImportInitialiser newInitialiser() {
		return new StaticClassifierImportInitialiser();
	}

	@Override
	public StaticClassifierImport instantiate() {
		return ImportsFactory.eINSTANCE.createStaticClassifierImport();
	}
}