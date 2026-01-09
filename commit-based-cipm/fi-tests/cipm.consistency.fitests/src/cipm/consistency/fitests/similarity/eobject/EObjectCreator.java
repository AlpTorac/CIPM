package cipm.consistency.fitests.similarity.eobject;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public abstract class EObjectCreator {
	protected abstract EClass getEClassFor(Class<? extends EObject> eObjCls);
	public abstract EObject createEObject(EClass eCls);
	public abstract <T extends EObject> T createEObject(Class<T> eObjCls);
	public abstract List<EClass> getAllSupportedTypes();
}
