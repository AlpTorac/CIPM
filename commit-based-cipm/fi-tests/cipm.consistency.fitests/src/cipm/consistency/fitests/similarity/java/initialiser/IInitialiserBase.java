package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Collection;

public interface IInitialiserBase extends IInitialiser {
	public void addAdaptingInitialiser(IInitialiserAdapterStrategy init);
	public void removeAdaptingInitialiser(IInitialiserAdapterStrategy init);
	public void cleanAdaptingInitialiser();
	public Collection<IInitialiserAdapterStrategy> getAdaptingInitialisers();
	public default void addAdaptingInitialisers(IInitialiserAdapterStrategy[] init) {
		for (var i: init) {
			this.addAdaptingInitialiser(i);
		}
	}
	@Override
	public default boolean initialise(Object obj) {
		boolean result = true;
		
		for (var init : this.getAdaptingInitialisers()) {
			result = result && init.apply(this, obj);
		}
		
		return result;
	}
	public default Object insAndInit() {
		var res = this.instantiate();
		this.initialise(res);
		return res;
	}
}