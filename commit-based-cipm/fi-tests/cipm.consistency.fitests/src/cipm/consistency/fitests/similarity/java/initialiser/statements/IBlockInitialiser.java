package cipm.consistency.fitests.similarity.java.initialiser.statements;

import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IModifiableInitialiser;

import org.emftext.language.java.statements.Block;

public interface IBlockInitialiser
		extends IModifiableInitialiser, IMemberInitialiser, IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public Block instantiate();
}
