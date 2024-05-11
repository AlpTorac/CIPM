package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;

public class AnnotationInstanceGenerator extends EObjectGenerator<AnnotationInstance>
	implements INamespaceAwareElementGenerator {
	private NamespaceGenerator nsGen;

	public AnnotationInstanceGenerator() {
		super();
		this.setDefaultNamespaceGenerator();
	}

	@Override
	public NamespaceGenerator getNamespaceGenerator() {
		return this.nsGen;
	}

	@Override
	public void setNamespaceGenerator(NamespaceGenerator nsGen) {
		this.nsGen = nsGen;
	}

	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new AnnotationInstanceInitialiser();
	}
	
	@Override
	public AnnotationInstance generateElement() {
		AnnotationInstance result = super.generateElement();
		this.setNamespace(result);
		return result;
	}
}
