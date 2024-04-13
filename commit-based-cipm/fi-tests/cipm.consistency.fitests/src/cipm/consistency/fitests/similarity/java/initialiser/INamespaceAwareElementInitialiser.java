package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.emftext.language.java.commons.NamespaceAwareElement;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface INamespaceAwareElementInitialiser extends EObjectInitialiser {
	public default NamespaceAwareElement initialiseNamespaces(NamespaceAwareElement elem, String[] namespaces) {
		if (namespaces != null) {
			for (var ns : namespaces) {
				elem.getNamespaces().add(ns);
			}
		}
		return elem;
	}
	
	public default NamespaceAwareElement initialiseNamespaceAwareElement(NamespaceAwareElement elem, Map<ResourceParameters, Object> params) {
		return this.initialiseNamespaces(elem, this.getNamespacesParam(params));
	}
	
	public default String[] getNamespacesParam(Map<ResourceParameters, Object> params) {
		return (String[]) params.get(ResourceParameters.NAMESPACE);
	}
}
