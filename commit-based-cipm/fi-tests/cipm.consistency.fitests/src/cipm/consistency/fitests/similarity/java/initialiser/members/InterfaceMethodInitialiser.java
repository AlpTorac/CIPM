package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

import org.emftext.language.java.members.InterfaceMethod;

public class InterfaceMethodInitialiser extends MemberContaineeInitialiser implements IInterfaceMethodInitialiser {
	@Override
	public InterfaceMethod instantiate() {
		return MembersFactory.eINSTANCE.createInterfaceMethod();
	}
	
	@Override
	public InterfaceMethodInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (InterfaceMethodInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberContaineeInitialiser newInitialiser() {
		return new InterfaceMethodInitialiser();
	}
}
