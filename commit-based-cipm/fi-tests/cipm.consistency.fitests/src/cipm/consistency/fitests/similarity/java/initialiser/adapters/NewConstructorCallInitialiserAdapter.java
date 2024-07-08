package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.instantiations.NewConstructorCall;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.INewConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypeReferenceInitialiser;

public class NewConstructorCallInitialiserAdapter
		implements IInitialiserAdapterStrategy {

	private ITypeReferenceInitialiser tRefInit;
	private IClassifierInitialiser clsInit;
	
	public NewConstructorCallInitialiserAdapter(ITypeReferenceInitialiser tRefInit,
			IClassifierInitialiser clsInit) {
		this.tRefInit = tRefInit;
		this.clsInit = clsInit;
	}

	public ITypeReferenceInitialiser gettRefInit() {
		return tRefInit;
	}

	public IClassifierInitialiser getClsInit() {
		return clsInit;
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedInit = (INewConstructorCallInitialiser) init;
		var castedO = (NewConstructorCall) obj;
		
		if (castedO.getTypeReference() == null) {
			var cls = this.getClsInit().instantiate();
			
			var tref = this.gettRefInit().instantiate();
			
			return this.getClsInit().initialise(cls) &&
					this.gettRefInit().initialise(tref) &&
					this.gettRefInit().setTarget(tref, cls) &&
					castedInit.setTypeReference(castedO, tref) &&
					castedO.getTypeReference().equals(tref);
		}
		
		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new NewConstructorCallInitialiserAdapter(
				(ITypeReferenceInitialiser) this.gettRefInit().newInitialiser(),
				(IClassifierInitialiser) this.getClsInit().newInitialiser());
	}
}
