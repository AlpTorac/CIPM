package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public abstract class MemberContaineeInitialiser implements EObjectInitialiser {
	private IMemberContainerInitialiser mcInit;
	
	/**
	 * Same function as {@link #setMCInit(IMemberContainerInitialiser)}.
	 * Implemented to keep initialiser lists tidier.
	 * @return This
	 */
	public MemberContaineeInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.setMCInit(mcInit);
		return this;
	}
	
	public void setMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
	}
	
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}
	
	@Override
	public EObject minimalInitialisationWithContainer(EObject member) {
		var castedO = (Member) member;
		this.minimalInitialisation(castedO);
		
		var mcInit = this.getMCInit();
		
		MemberContainer mc = mcInit.instantiate();
		
		var root = mcInit.minimalInitialisationWithContainer(mc);
		mcInit.addMember(mc, castedO);
		
		return root;
	}
	
	@Override
	public abstract MemberContaineeInitialiser newInitialiser();
}