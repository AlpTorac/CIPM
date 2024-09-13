package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.statements.Block;

public class BlockInitialiser extends AbstractInitialiserBase implements IBlockInitialiser {
	@Override
	public Block instantiate() {
		return StatementsFactory.eINSTANCE.createBlock();
	}

	@Override
	public BlockInitialiser newInitialiser() {
		return new BlockInitialiser();
	}
}