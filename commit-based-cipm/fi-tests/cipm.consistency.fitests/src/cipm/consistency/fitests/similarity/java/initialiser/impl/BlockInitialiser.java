package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Block;

import cipm.consistency.fitests.similarity.java.initialiser.IBlockInitialiser;

public class BlockInitialiser implements IBlockInitialiser, IInitialiser {
	@Override
	public Block instantiate() {
		return StatementsFactory.eINSTANCE.createBlock();
	}
}
