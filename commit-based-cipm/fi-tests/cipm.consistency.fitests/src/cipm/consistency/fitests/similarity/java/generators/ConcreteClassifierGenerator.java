package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public class ConcreteClassifierGenerator extends EObjectGenerator<ConcreteClassifier>
	implements INamedElementGenerator {
	
	private NameGenerator nGen = new NameGenerator() {{
		setNamePrefix("classifier");
	}};
	private PackageGenerator pacGen = new PackageGenerator();
	
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
	
	protected void setPackage(ConcreteClassifier cc) {
		this.getInitialiser().setPackage(cc,
				this.pacGen.generateDefaultElement());
	}
	
	public ConcreteClassifier generateWithPackage() {
		var result = this.createDefaultElement();
		this.setPackage(result);
		return result;
	}
	
	public ConcreteClassifier generateWithPackageAndName() {
		ConcreteClassifier result = this.generateWithName();
		this.setPackage(result);
		return result;
		
	}

	@Override
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}
}
