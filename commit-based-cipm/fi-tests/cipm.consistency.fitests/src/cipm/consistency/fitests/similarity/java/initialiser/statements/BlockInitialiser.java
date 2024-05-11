package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.helper.IMemberHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Block;

public class BlockInitialiser implements IBlockInitialiser {
	@Override
	public Block instantiate() {
		return StatementsFactory.eINSTANCE.createBlock();
	}

	@Override
	public BlockInitialiser newInitialiser() {
		return new BlockInitialiser();
	}
}