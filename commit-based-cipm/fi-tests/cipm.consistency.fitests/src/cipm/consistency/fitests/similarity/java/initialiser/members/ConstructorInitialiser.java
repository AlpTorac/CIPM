package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberInitialiser;

import org.emftext.language.java.members.Constructor;

public class ConstructorInitialiser extends MemberInitialiser implements IConstructorInitialiser {
	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}
	
	@Override
	public ConstructorInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (ConstructorInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new ConstructorInitialiser();
	}
}
