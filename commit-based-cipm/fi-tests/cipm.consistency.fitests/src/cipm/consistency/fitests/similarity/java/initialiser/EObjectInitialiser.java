package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

public interface EObjectInitialiser {
	public EObject instantiate();
	
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
