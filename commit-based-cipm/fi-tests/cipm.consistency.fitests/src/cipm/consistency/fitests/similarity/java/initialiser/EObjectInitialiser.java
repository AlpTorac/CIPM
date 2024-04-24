package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

public interface EObjectInitialiser {
	public EObject instantiate();
	
	/**
	 * A method for creating a minimal valid instance to spare
	 * irrelevant instantiation methods.
	 * <br><br>
	 * Use this instead of {@link #instantiate()}, if an optional
	 * part of the instance is to be tested.
	 * <br><br>
	 * <b>Note: This only generates a valid instance of the EObject
	 * sub-class denoted by the Initialiser interface's name, meaning
	 * that containers required for the said instance will NOT be
	 * instantiated by this method.</b>
	 * 
	 * @return A valid, minimal instance
	 */
	public default EObject minimalInstantiation() {
		return this.instantiate();
	}
	
	public default EObject clone(EObject obj) {
		return EcoreUtil.copy(obj);
	}
	
	public default Collection<EObject> clone(Collection<? extends EObject> objs) {
		return EcoreUtil.copyAll(objs);
	}
	
	public default void addToResource(Resource res, EObject obj) {
		if (res != null && !res.getContents().contains(obj)) {
			res.getContents().add(obj);
			assert res.getContents().contains(obj);
		}
	}
}
