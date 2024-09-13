package cipm.consistency.fitests.similarity.java.eobject.initialiser.imports;

import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ClassifierImportInitialiser extends AbstractInitialiserBase implements IClassifierImportInitialiser {
	@Override
	public IClassifierImportInitialiser newInitialiser() {
		return new ClassifierImportInitialiser();
	}

	@Override
	public ClassifierImport instantiate() {
		return ImportsFactory.eINSTANCE.createClassifierImport();
	}
}