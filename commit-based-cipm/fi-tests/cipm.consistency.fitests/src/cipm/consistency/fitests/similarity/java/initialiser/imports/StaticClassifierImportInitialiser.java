package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.StaticClassifierImport;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class StaticClassifierImportInitialiser extends AbstractInitialiserBase
		implements IStaticClassifierImportInitialiser {
	@Override
	public IStaticClassifierImportInitialiser newInitialiser() {
		return new StaticClassifierImportInitialiser();
	}

	@Override
	public StaticClassifierImport instantiate() {
		return ImportsFactory.eINSTANCE.createStaticClassifierImport();
	}
}