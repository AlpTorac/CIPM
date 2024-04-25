package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Constructor;

import cipm.consistency.fitests.similarity.java.initialiser.IConstructorInitialiser;

public class ConstructorInitialiser implements IConstructorInitialiser {
	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}
}
