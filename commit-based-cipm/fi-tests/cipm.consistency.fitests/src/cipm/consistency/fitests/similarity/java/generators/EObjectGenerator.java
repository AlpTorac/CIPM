package cipm.consistency.fitests.similarity.java.generators;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;

public abstract class EObjectGenerator<T extends EObject> extends AbstractParameterGenerator<T> {
	private EObjectInitialiser init;
	
	public EObjectGenerator() {
		super();
		this.init = this.getDefaultInitialiser();
	}
	
	@Override
	protected T createDefaultElement() {
		return this.minimallyInstantiateObj();
	}
	
	protected abstract EObjectInitialiser getDefaultInitialiser();
	
	protected T instantiateObj() {
		return this.getInitialiser().instantiate();
	}
	
	protected T minimallyInstantiateObj() {
		var result = this.instantiateObj();
		this.getInitialiser().minimalInitialisation(result);
		return result;
	}
	
	protected T cloneObj(T obj) {
		return this.getInitialiser().clone(obj);
	}
	
	public EObjectInitialiser getInitialiser() {
		return this.init;
	}
	
	public void setInitialiser(EObjectInitialiser init) {
		if (init != null) {
			this.init = init;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @return A clone of the element at the given index
	 * cloned by {@link #init}
	 */
	public T cloneObjAt(int index) {
		return this.cloneObj(this.getObjAt(index));
	}
}
