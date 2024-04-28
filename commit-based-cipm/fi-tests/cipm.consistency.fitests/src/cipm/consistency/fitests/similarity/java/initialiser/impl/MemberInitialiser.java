package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.members.Member;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;

public abstract class MemberInitialiser implements IMemberInitialiser {
	private IMemberContainerInitialiser mcInit;
	
	public MemberInitialiser() {}
	
	public MemberInitialiser(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
	}
	
	protected IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}
	
	@Override
	public EObject minimalInitialisationWithContainer(EObject member) {
		var castedO = (Member) member;
		this.minimalInitialisation(castedO);
		
		var mcInit = this.getMCInit();
		
		var mc = mcInit.instantiate();
		
		var root = mcInit.minimalInitialisationWithContainer(mc);
		mcInit.addMember(mc, castedO);
		
		return root;
	}
}
