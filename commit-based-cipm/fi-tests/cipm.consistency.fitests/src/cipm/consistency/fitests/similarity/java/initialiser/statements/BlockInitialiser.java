package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberInitialiser;

import org.emftext.language.java.statements.Block;

public class BlockInitialiser extends MemberInitialiser implements IBlockInitialiser {
	@Override
	public Block instantiate() {
		return StatementsFactory.eINSTANCE.createBlock();
	}
	
	@Override
	public BlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (BlockInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new BlockInitialiser();
	}
}