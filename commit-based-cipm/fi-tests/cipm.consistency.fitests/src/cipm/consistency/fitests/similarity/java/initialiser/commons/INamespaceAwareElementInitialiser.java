package cipm.consistency.fitests.similarity.java.initialiser.commons;

import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface INamespaceAwareElementInitialiser extends ICommentableInitialiser {
    @Override
    public NamespaceAwareElement instantiate();
	public default boolean addNamespaces(NamespaceAwareElement nae, String[] namespaces) {
		return this.addXs(nae, namespaces, this::addNamespace);
	}
	
	@ModificationMethod
	public default boolean addNamespace(NamespaceAwareElement nae, String namespace) {
		if (namespace != null) {
			nae.getNamespaces().add(namespace);
			return nae.getNamespaces().contains(namespace);
		}
		return true;
	}
}
