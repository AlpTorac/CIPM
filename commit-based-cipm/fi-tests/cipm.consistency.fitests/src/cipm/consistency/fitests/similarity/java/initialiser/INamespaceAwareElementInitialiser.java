package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Collection;
import java.util.List;

import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.NamespaceAwareElement;

public interface INamespaceAwareElementInitialiser extends ICommentableInitialiser {
	public default void initialiseNamespaces(NamespaceAwareElement nae, String[] namespaces) {
		if (namespaces != null) {
			this.initialiseNamespaces(nae, List.of(namespaces));
		}
	}
	
	public default void initialiseNamespaces(NamespaceAwareElement nae, Collection<String> namespaces) {
		if (namespaces != null) {
			for (var ns : namespaces) {
				this.initialiseNamespace(nae, ns);
			}
			
			assert nae.getNamespaces().containsAll(namespaces);
		}
	}
	
	public default void initialiseNamespace(NamespaceAwareElement nae, String namespace) {
		if (namespace != null) {
			nae.getNamespaces().add(namespace);
			assert nae.getNamespaces().contains(namespace);
		}
	}
}
