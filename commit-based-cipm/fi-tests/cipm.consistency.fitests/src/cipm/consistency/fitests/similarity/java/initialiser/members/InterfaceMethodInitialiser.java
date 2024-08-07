package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
