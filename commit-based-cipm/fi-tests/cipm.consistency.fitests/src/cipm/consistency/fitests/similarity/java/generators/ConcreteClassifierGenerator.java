package cipm.consistency.fitests.similarity.java.generators;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public class ConcreteClassifierGenerator<T extends ConcreteClassifier> extends EObjectGenerator<T>
	implements INamedElementGenerator, IPackageContaineeGenerator,
		ICompilationUnitContaineeGenerator {
	
	private NameGenerator nGen;
	private PackageGenerator pacGen;
	private CompilationUnitGenerator cuGen;
	
	public ConcreteClassifierGenerator() {
		super();
		this.setDefaultNameGen();
		this.setDefaultPacGen();
		this.setDefaultCUGen();
	}
	
	public void setPacGen(PackageGenerator pacGen) {
		this.pacGen = pacGen;
	}
	
	public PackageGenerator getPacGen() {
		return this.pacGen;
	}
	
	@Override
	public void reset() {
		super.reset();
		nGen.reset();
	}
	
	@Override
	public IConcreteClassifierInitialiser getInitialiser() {
		return (IConcreteClassifierInitialiser) super.getInitialiser();
	}

	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new ClassInitialiser();
	}
	
	@Override
	public T generateElement() {
		T result = super.generateElement();
		
		this.setName(result);
		this.setPackage(result);
		this.setCU(result);
		
		return result;
	}

	@Override
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}
	
	@Override
	public void setNameGenerator(NameGenerator nGen) {
		this.nGen = nGen;
	}

	@Override
	public <C extends EObject> void initialiserSetPackage(Package pac, C obj) {
		this.getInitialiser().setPackage((ConcreteClassifier) obj,
				pac);
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
