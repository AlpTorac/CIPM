package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;

public interface INamespaceAwareElementGenerator {
	public <T extends NamespaceAwareElement> T generateElement();
	public EObjectInitialiser getInitialiser();
	public NamespaceGenerator getNamespaceGenerator();
	public void setNamespaceGenerator(NamespaceGenerator nsGen);
	public default void setDefaultNamespaceGenerator() {
		this.setNamespaceGenerator(new NamespaceGenerator());
	}
	
	public default <T extends NamespaceAwareElement> void setNamespace(T obj) {
		var gen = this.getNamespaceGenerator();
		if (gen != null) {
			var init = (INamespaceAwareElementInitialiser) this.getInitialiser();
			init.addNamespaces(obj, gen.generateElement());
		}
	}
}
