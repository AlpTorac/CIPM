package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.classifiers.Annotation;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IAnnotationInitialiser;

public class AnnotationGenerator extends EObjectGenerator<Annotation>
	implements INamedElementGenerator {

	private NameGenerator nGen;
	private AnnotationInstanceGenerator aiGen;
	
	public AnnotationGenerator() {
		super();
		this.setDefaultNameGen();
		this.aiGen = new AnnotationInstanceGenerator();
	}
	
	public AnnotationInstanceGenerator getAIGen() {
		return this.aiGen;
	}
	
	public void setAIGen(AnnotationInstanceGenerator aiiGen) {
		this.aiGen = aiiGen;
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
	public IAnnotationInitialiser getInitialiser() {
		return (IAnnotationInitialiser) super.getInitialiser();
	}
	
	@Override
	protected IAnnotationInitialiser getDefaultInitialiser() {
		return new AnnotationInitialiser();
	}
	
	@Override
	public Annotation generateElement() {
		Annotation result = super.generateElement();
		this.setName(result);
		this.getInitialiser().addAnnotationInstance(result,
				this.getAIGen().generateElement());
		return result;
	}
}
