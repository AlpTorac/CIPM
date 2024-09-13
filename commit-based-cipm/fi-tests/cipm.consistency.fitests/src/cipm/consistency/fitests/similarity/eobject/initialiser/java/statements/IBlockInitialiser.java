package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers.IModifiableInitialiser;

public interface IBlockInitialiser
		extends IModifiableInitialiser, IMemberInitialiser, IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public Block instantiate();

	@Override
	public default boolean canContainStatements(StatementListContainer slc) {
		return true;
	}
}
