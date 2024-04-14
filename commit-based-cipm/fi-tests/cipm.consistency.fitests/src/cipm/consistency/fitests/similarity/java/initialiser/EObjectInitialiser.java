package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface EObjectInitialiser {
	public void resetCurrentObject();
	public EObject getCurrentObject();
	public void instantiate();
	
	public default EObject build() {
		var result = this.getCurrentObject();
		this.resetCurrentObject();
		return result;
	}
	
	public default void addToResource(Resource res) {
		var cObj = this.getCurrentObject();
		
		if (res != null && !res.getContents().contains(cObj)) {
			res.getContents().add(cObj);
		}
	}
}
