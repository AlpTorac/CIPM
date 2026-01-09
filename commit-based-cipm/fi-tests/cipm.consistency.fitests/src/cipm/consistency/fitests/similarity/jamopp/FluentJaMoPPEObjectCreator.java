package cipm.consistency.fitests.similarity.jamopp;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import cipm.consistency.fitests.similarity.eobject.EObjectCreator;
import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.api.FluentEObjectAPI;

public class FluentJaMoPPEObjectCreator extends EObjectCreator {
	private static final FluentEObjectAPI api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EObject> T createEObject(Class<T> eObjCls) {
		return (T) createEObject(getEClassFor(eObjCls));
	}

	@Override
	protected EClass getEClassFor(Class<? extends EObject> eObjCls) {
		return JaMoPPHelper.getEClassForJavaElement(eObjCls);
	}

	@Override
	public EObject createEObject(EClass eCls) {
		return api.newX(eCls).createNow();
	}

	@Override
	public List<EClass> getAllSupportedTypes() {
		throw new UnsupportedOperationException("Implement method");
	}
}
