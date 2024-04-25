package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.statements.Block;

public interface IBlockInitialiser extends IModifiableInitialiser, IMemberInitialiser,
	IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public Block instantiate();
	
	@Override
	public default Block minimalInstantiation() {
		return (Block) IMemberInitialiser.super.minimalInstantiation();
	}
}
