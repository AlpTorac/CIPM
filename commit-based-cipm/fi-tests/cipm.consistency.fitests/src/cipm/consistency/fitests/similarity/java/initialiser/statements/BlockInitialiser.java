package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Block;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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