package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportInitialiser;

public class ImportGenerator<T extends Import> extends EObjectGenerator<T> {
	private ConcreteClassifierGenerator<?> clsGen = new ConcreteClassifierGenerator<>();
	
	public void setCCInit(IConcreteClassifierInitialiser init) {
		this.clsGen.setInitialiser(init);
	}
	
	@Override
	public IImportInitialiser getInitialiser() {
		return (IImportInitialiser) super.getInitialiser();
	}
	
	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new ClassifierImportInitialiser();
	}

	@Override
	public T createElement() {
		T imp = super.createElement();
		this.getInitialiser().setClassifier(imp, this.clsGen.generateElement());
		return imp;
	}
}
