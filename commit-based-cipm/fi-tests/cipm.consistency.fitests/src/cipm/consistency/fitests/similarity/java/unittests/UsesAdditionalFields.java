package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.members.AdditionalField;

import cipm.consistency.fitests.similarity.java.initialiser.members.AdditionalFieldInitialiser;

public interface UsesAdditionalFields {
	public default AdditionalField createMinimalAF(String afName) {
		var afInit = new AdditionalFieldInitialiser();
		var af = afInit.instantiate();
		afInit.initialise(af);
		afInit.setName(af, afName);
		return af;
	}
}
