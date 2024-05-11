package cipm.consistency.fitests.similarity.java.initialiser.helper;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public interface IMemberHelperInitialiser extends EObjectInitialiser {
	public IMemberInitialiser withMCInit(IMemberContainerInitialiser mcInit);
	public IMemberContainerInitialiser getMCInit();
	
	public default IMemberContainerInitialiser getDefaultMCInit() {
		return new ClassInitialiser();
	}
	
//	@Override
//	public default EObject minimalInitialisationWithContainer(EObject member) {
//		var castedO = (Member) member;
//		this.minimalInitialisation(castedO);
//		
//		var mcInit = this.getMCInit();
//		if (mcInit == null) {
//			mcInit = this.getDefaultMCInit();
//		}
//		
//		MemberContainer mc = mcInit.instantiate();
//		
//		var root = mcInit.minimalInitialisationWithContainer(mc);
//		mcInit.addMember(mc, castedO);
//		
//		return root;
//	}
}
