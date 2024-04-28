package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Constructor;

import cipm.consistency.fitests.similarity.java.initialiser.IConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;

public class ConstructorInitialiser extends MemberInitialiser implements IConstructorInitialiser {
	public ConstructorInitialiser() {
		super();
	}
	
	public ConstructorInitialiser(IMemberContainerInitialiser mcInit) {
		super(mcInit);
	}

	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}
}
