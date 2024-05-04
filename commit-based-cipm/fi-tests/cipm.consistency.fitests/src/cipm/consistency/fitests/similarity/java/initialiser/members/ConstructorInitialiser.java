package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

import org.emftext.language.java.members.Constructor;

public class ConstructorInitialiser extends MemberContaineeInitialiser implements IConstructorInitialiser {
	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}
	
	@Override
	public ConstructorInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (ConstructorInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberContaineeInitialiser newInitialiser() {
		return new ConstructorInitialiser();
	}
}
