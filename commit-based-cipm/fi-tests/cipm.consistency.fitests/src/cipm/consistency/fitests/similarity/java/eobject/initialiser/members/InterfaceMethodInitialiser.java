package cipm.consistency.fitests.similarity.java.eobject.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.members.InterfaceMethod;

public class InterfaceMethodInitialiser extends AbstractInitialiserBase implements IInterfaceMethodInitialiser {
	@Override
	public InterfaceMethod instantiate() {
		return MembersFactory.eINSTANCE.createInterfaceMethod();
	}

	@Override
	public InterfaceMethodInitialiser newInitialiser() {
		return new InterfaceMethodInitialiser();
	}
}
