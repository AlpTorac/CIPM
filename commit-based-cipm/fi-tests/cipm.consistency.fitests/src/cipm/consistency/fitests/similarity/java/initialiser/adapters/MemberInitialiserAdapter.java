package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;

public class MemberInitialiserAdapter implements
	IInitialiserAdapterStrategy {
	private IMemberContainerInitialiser mcInit;
	
	public MemberInitialiserAdapter(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
	}
	
	public MemberInitialiserAdapter withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}
	
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedO = (Member) obj;

		if (castedO.eContainer() == null) {
			var mcInit = this.getMCInit();
			
			MemberContainer mc = mcInit.instantiate();
			return mcInit.initialise(mc) &&
					mcInit.addMember(mc, castedO);
		}
		
		return true;
	}

	@Override
	public MemberInitialiserAdapter newStrategy() {
		return new MemberInitialiserAdapter(
				(IMemberContainerInitialiser) this.getMCInit().newInitialiser());
	}
}
