package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.List;

import org.emftext.language.java.commons.NamespaceAwareElement;

public interface INamespaceAwareElementInitialiser extends ICommentableInitialiser {
	@Override
	public NamespaceAwareElement getCurrentObject();
	
	public default void initialiseNamespaces(String[] namespaces) {
		if (namespaces != null) {
			for (var ns : namespaces) {
				this.initialiseNamespace(ns);
			}
			
			assert this.getCurrentObject().getNamespaces().containsAll(List.of(namespaces));
		}
	}
	
	public default void initialiseNamespace(String namespace) {
		if (namespace != null) {
			this.getCurrentObject().getNamespaces().add(namespace);
			assert this.getCurrentObject().getNamespaces().contains(namespace);
		}
	}
	
	@Override
	public default NamespaceAwareElement build() {
		return (NamespaceAwareElement) ICommentableInitialiser.super.build();
	}
}
