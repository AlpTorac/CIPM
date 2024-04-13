package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface IJavaRootInitialiser extends INameInitialiser, INamespaceAwareElementInitialiser {
	public default JavaRoot initialiseOrigin(JavaRoot elem, Origin origin) {
		elem.setOrigin(origin);
		assert elem.getOrigin().equals(origin);
		return elem;
	}
	
	public default JavaRoot initialiseJavaRoot(JavaRoot elem, Map<ResourceParameters, Object> params) {
		this.initialiseOrigin(elem, this.getOriginParam(params));
		this.initialiseNamedElement(elem, params);
		this.initialiseNamespaceAwareElement(elem, params);
		
		return elem;
	}
	
	public default Origin getOriginParam(Map<ResourceParameters, Object> params) {
		return (Origin) params.get(ResourceParameters.ORIGIN);
	}
}
