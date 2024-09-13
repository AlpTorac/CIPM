package cipm.consistency.fitests.similarity.eobject.initialiser.java.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.members.Constructor;

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
