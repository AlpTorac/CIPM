package cipm.consistency.fitests.similarity.java.utils;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface IJavaModelConstructor {
	public default Resource initResource(URI uri) {
		ResourceSet rSet = new ResourceSetImpl();
		return rSet.createResource(uri);
	}
	
	public default void saveResource(Resource res) throws IOException {
		res.save(null);
	}
	
	public default Resource createResource(URI uri, Map<ResourceParameters, Object> params) throws IOException {
		var res = this.initResource(uri);
		this.fillResource(res, params);
		this.saveResource(res);
		return res;
	}
	
	public void fillResource(Resource res, Map<ResourceParameters, Object> params);
}
