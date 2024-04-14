package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.NamespaceAwareElement;

public interface INamespaceAwareElementInitialiser extends ICommentableInitialiser {
	@Override
	public NamespaceAwareElement instantiate();
	
	@Override
	public default NamespaceAwareElement clone(EObject obj) {
		return (NamespaceAwareElement) ICommentableInitialiser.super.clone(obj);
	}
	
	public default void initialiseNamespaces(NamespaceAwareElement nae, String[] namespaces) {
		if (namespaces != null) {
			for (var ns : namespaces) {
				this.initialiseNamespace(nae, ns);
			}
			
			assert nae.getNamespaces().containsAll(List.of(namespaces));
		}
	}
	
	public default void initialiseNamespace(NamespaceAwareElement nae, String namespace) {
		if (namespace != null) {
			nae.getNamespaces().add(namespace);
			assert nae.getNamespaces().contains(namespace);
		}
	}
}
