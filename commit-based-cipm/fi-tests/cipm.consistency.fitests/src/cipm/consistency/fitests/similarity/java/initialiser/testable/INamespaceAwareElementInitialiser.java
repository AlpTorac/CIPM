package cipm.consistency.fitests.similarity.java.initialiser.testable;

import java.util.Collection;

import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface INamespaceAwareElementInitialiser extends ICommentableInitialiser {
	public default void addNamespaces(NamespaceAwareElement nae, String[] namespaces) {
		this.addXs(nae, (o,s) -> this.addNamespace(o, s), namespaces);
	}
	
	public default void addNamespaces(NamespaceAwareElement nae, Collection<String> namespaces) {
		this.addXs(nae, (o, s) -> this.addNamespace(o,s), namespaces);
	}
	
	public default void addNamespace(NamespaceAwareElement nae, String namespace) {
		if (namespace != null) {
			nae.getNamespaces().add(namespace);
			assert nae.getNamespaces().contains(namespace);
		}
	}
}
