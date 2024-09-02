package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import org.emftext.language.java.members.Constructor;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ConstructorInitialiser extends AbstractInitialiserBase implements IConstructorInitialiser {
	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}

	@Override
	public ConstructorInitialiser newInitialiser() {
		return new ConstructorInitialiser();
	}
}
