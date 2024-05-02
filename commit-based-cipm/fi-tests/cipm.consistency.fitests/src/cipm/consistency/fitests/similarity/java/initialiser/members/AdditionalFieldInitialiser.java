package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.AdditionalField;

public class AdditionalFieldInitialiser implements IAdditionalFieldInitialiser {
	@Override
	public IAdditionalFieldInitialiser newInitialiser() {
		return new AdditionalFieldInitialiser();
	}

	@Override
	public AdditionalField instantiate() {
		return MembersFactory.eINSTANCE.createAdditionalField();
	}
}
