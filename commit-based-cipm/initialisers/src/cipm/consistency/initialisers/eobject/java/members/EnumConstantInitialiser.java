package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

import org.emftext.language.java.members.EnumConstant;

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
