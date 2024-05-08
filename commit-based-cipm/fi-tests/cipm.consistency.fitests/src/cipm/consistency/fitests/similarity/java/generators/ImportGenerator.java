package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;

public class ImportGenerator<T extends Import> extends EObjectGenerator<T> {
	private ConcreteClassifierGenerator clsGen = new ConcreteClassifierGenerator();
	
	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new ClassifierImportInitialiser();
	}

	@Override
	public T createDefaultElement() {
		// TODO: Implement import generator
		return null;
	}
}
