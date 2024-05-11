package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;

public class NamedElementGenerator<T extends NamedElement> extends EObjectGenerator<T>
	implements INamedElementGenerator {
	private NameGenerator nGen;
	
	public NamedElementGenerator() {
		super();
		this.setDefaultNameGen();
	}
	
	@Override
	public T generateElement() {
		T result = super.generateElement();
		this.setName(result);
		return result;
	}
	
	@Override
	public INamedElementInitialiser getInitialiser() {
		return (INamedElementInitialiser) super.getInitialiser();
	}

	@Override
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}

	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return null;
	}

	@Override
	public void setNameGenerator(NameGenerator nGen) {
		this.nGen = nGen;
	}
}
