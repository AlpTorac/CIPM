package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;

public interface INamespaceAwareElementGenerator {
	public <T extends NamespaceAwareElement> T generateDefaultElement();
	public EObjectInitialiser getInitialiser();
	public NamespaceGenerator getNamespaceGenerator();
	
	public default <T extends NamespaceAwareElement> void setNamespace(T obj) {
		var init = (INamespaceAwareElementInitialiser) this.getInitialiser();
		init.addNamespaces(obj, this.getNamespaceGenerator().generateDefaultElement());
	}
	
	@SuppressWarnings("unchecked")
	public default <T extends NamespaceAwareElement> T generateWithNamespace() {
		var result = this.generateDefaultElement();
		this.setNamespace(result);
		return (T) result;
	}
}
