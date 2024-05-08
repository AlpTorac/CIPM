package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;

public interface INamedElementGenerator {
	public <T extends NamedElement> T generateDefaultElement();
	public <T extends INamedElementInitialiser> T getInitialiser();
	public NameGenerator getNameGenerator();
	
	public default <T extends NamedElement> void setName(T obj) {
		var init = (INamedElementInitialiser) this.getInitialiser();
		init.initialiseName(obj, this.getNameGenerator().generateDefaultElement());
	}
	
	@SuppressWarnings("unchecked")
	public default <T extends NamedElement> T generateWithName() {
		var result = (NamedElement) this.generateDefaultElement();
		this.setName(result);
		return (T) result;
	}
}
