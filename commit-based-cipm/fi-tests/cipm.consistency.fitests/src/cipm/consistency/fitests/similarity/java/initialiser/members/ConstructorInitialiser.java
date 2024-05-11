package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.members.Constructor;

public class ConstructorInitialiser implements IConstructorInitialiser {
	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}

	@Override
	public ConstructorInitialiser newInitialiser() {
		return new ConstructorInitialiser();
	}
}
