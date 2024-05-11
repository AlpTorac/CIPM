package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;

public interface INamedElementGenerator {
	public void setNameGenerator(NameGenerator nGen);
	public NameGenerator getNameGenerator();
	public INamedElementInitialiser getInitialiser();
	
	public default <T extends NamedElement> void setName(T obj) {
		var gen = this.getNameGenerator();
		if (gen != null)
			this.getInitialiser().initialiseName(obj,
					gen.generateElement());
	}
	
	public default void setDefaultNameGen() {
		this.setNameGenerator(new NameGenerator());
	}
}
