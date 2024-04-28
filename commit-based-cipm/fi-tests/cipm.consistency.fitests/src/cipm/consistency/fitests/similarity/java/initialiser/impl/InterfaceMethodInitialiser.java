package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.InterfaceMethod;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;

public class InterfaceMethodInitialiser extends MemberInitialiser implements IInterfaceMethodInitialiser {
	@Override
	public InterfaceMethod instantiate() {
		return MembersFactory.eINSTANCE.createInterfaceMethod();
	}
	
	@Override
	public InterfaceMethodInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (InterfaceMethodInitialiser) super.withMCInit(mcInit);
	}
}
