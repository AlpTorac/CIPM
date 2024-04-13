package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface EObjectInitialiser {
	public EObject initialise(EObject obj, Map<ResourceParameters, Object> params);
	public EObject instantiate(Map<ResourceParameters, Object> params);
	
	public default EObject instantiate() {
		return this.instantiate(null);
	}
	
	public EObject build(Map<ResourceParameters, Object> params);
	public EObject build();
	
	public void setResource(Resource res);
	public Resource getResource();
}
