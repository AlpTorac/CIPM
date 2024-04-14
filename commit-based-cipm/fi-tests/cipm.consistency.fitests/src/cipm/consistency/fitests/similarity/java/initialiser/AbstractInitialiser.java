package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;

public abstract class AbstractInitialiser implements EObjectInitialiser {
	private EObject obj;
	
	protected void setCurrentObject(EObject obj) {
		this.obj = obj;
	}
	
	@Override
	public EObject getCurrentObject() {
		return this.obj;
	}
	
	@Override
	public void resetCurrentObject() {
		this.obj = null;
	}
}
