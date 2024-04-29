package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IEnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

public class EnumConstantInitialiser implements IEnumConstantInitialiser {
	@Override
	public EnumConstant instantiate() {
		return MembersFactory.eINSTANCE.createEnumConstant();
	}

	@Override
	public IEnumConstantInitialiser newInitialiser() {
		return new EnumConstantInitialiser();
	}
}
