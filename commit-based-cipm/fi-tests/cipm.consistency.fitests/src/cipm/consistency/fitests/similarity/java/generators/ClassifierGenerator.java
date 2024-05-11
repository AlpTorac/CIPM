package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IClassifierInitialiser;

public class ClassifierGenerator<T extends Classifier> extends EObjectGenerator<T>
	implements INamedElementGenerator, ICompilationUnitContaineeGenerator {

	private CompilationUnitGenerator cuGen;
	private NameGenerator nGen;
	
	public ClassifierGenerator() {
		super();
		this.setDefaultNameGen();
	}
	
	@Override
	public void setNameGenerator(NameGenerator nGen) {
		this.nGen = nGen;
	}

	@Override
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}

	@Override
	public IClassifierInitialiser getInitialiser() {
		return (IClassifierInitialiser) super.getInitialiser();
	}

	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return null;
	}

	@Override
	public T generateElement() {
		T result = super.generateElement();
		this.setName(result);
		
		if (result instanceof ConcreteClassifier) {
			this.setCU((ConcreteClassifier) result);
		}
		
		return result;
	}
	
	@Override
	public void setCUGen(CompilationUnitGenerator cuGen) {
		this.cuGen = cuGen;
	}

	@Override
	public CompilationUnitGenerator getCUGen() {
		return this.cuGen;
	}
}
