package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;

public class NamedElementInitialiserAdapter implements
	IInitialiserAdapterStrategy {
	
	public String getDefaultName() {
		return "";
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedInit = (INamedElementInitialiser) init;
		var castedO = (NamedElement) obj;
		
		if (castedO.getName() == null) {
			return castedInit.setName(castedO, this.getDefaultName());
		}
		
		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new NamedElementInitialiserAdapter();
	}
}
