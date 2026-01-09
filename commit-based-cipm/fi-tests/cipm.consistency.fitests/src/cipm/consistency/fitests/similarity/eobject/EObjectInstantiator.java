package cipm.consistency.fitests.similarity.eobject;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class EObjectInstantiator {
	private EObjectCreator creator;
	private List<IEObjectAdaptationStrategy> adaptationStrats;
	private Class<? extends EObject> cls;

	public EObjectInstantiator(EObjectCreator creator, Class<? extends EObject> cls) {
		this(creator, cls, null);
	}

	public EObjectInstantiator(EObjectCreator creator, Class<? extends EObject> cls,
			List<IEObjectAdaptationStrategy> adaptationStrats) {
		this.creator = creator;
		this.cls = cls;
		this.adaptationStrats = adaptationStrats;
		if (this.adaptationStrats == null) {
			this.adaptationStrats = new ArrayList<IEObjectAdaptationStrategy>();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends EObject> T createEObject() {
		// Return the class object for T
		var obj = (T) this.creator.createEObject(cls);
		this.adaptationStrats.forEach((s) -> s.apply(obj));
		return obj;
	}

	public Class<? extends EObject> getTypeToInstantiate() {
		return cls;
	}
}
