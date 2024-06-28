package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IMemberHelperInitialiser;

import org.emftext.language.java.members.InterfaceMethod;

public class InterfaceMethodInitialiser implements IInterfaceMethodInitialiser, IMemberHelperInitialiser {
	private IMemberContainerInitialiser mcInit;
	
	public InterfaceMethodInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}
	
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}

	@Override
	public InterfaceMethod instantiate() {
		return MembersFactory.eINSTANCE.createInterfaceMethod();
	}
	
	@Override
	public InterfaceMethodInitialiser newInitialiser() {
		return new InterfaceMethodInitialiser();
	}
}
