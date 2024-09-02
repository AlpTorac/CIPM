package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class EnumConstantInitialiser extends AbstractInitialiserBase implements IEnumConstantInitialiser {
	@Override
	public EnumConstant instantiate() {
		return MembersFactory.eINSTANCE.createEnumConstant();
	}

	@Override
	public IEnumConstantInitialiser newInitialiser() {
		return new EnumConstantInitialiser();
	}
}
