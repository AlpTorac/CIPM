package cipm.consistency.fitests.similarity.eobject.initialiser.java.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.members.AdditionalField;

public class AdditionalFieldInitialiser extends AbstractInitialiserBase implements IAdditionalFieldInitialiser {
	@Override
	public IAdditionalFieldInitialiser newInitialiser() {
		return new AdditionalFieldInitialiser();
	}

	@Override
	public AdditionalField instantiate() {
		return MembersFactory.eINSTANCE.createAdditionalField();
	}
}
