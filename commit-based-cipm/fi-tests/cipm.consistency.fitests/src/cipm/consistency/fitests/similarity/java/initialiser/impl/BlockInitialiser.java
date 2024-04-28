package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Block;

import cipm.consistency.fitests.similarity.java.initialiser.IBlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;

public class BlockInitialiser extends MemberInitialiser implements IBlockInitialiser {
	public BlockInitialiser() {
		super();
	}
	
	public BlockInitialiser(IMemberContainerInitialiser mcInit) {
		super(mcInit);
	}

	@Override
	public Block instantiate() {
		return StatementsFactory.eINSTANCE.createBlock();
	}
}
