package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractInitialiserBase implements IInitialiserBase {
	private Collection<IInitialiserAdapterStrategy> adaptingInits;

	public AbstractInitialiserBase() {
		this(null);
	}
	
	public AbstractInitialiserBase(IInitialiserAdapterStrategy[] adaptingInits) {
		this.adaptingInits = this.createAICol();
		
		if (adaptingInits != null) {
			this.addAdaptingInitialisers(adaptingInits);
		}
	}
	protected Collection<IInitialiserAdapterStrategy> createAICol() {
		return new ArrayList<IInitialiserAdapterStrategy>();
	}
	
	@Override
	public void addAdaptingInitialiser(IInitialiserAdapterStrategy init) {
		this.adaptingInits.add(init);
	}


	@Override
	public void removeAdaptingInitialiser(IInitialiserAdapterStrategy init) {
		this.adaptingInits.remove(init);
	}

	@Override
	public void cleanAdaptingInitialiser() {
		this.adaptingInits.clear();
	}

	@Override
	public Collection<IInitialiserAdapterStrategy> getAdaptingInitialisers() {
		var res = this.createAICol();
		res.addAll(this.adaptingInits);
		return res;
	}
}
