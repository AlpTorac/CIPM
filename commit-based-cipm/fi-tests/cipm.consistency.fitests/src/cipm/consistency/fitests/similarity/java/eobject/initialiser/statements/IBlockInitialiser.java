package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.IModifiableInitialiser;

public interface IBlockInitialiser
		extends IModifiableInitialiser, IMemberInitialiser, IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public Block instantiate();

	@Override
	public default boolean canContainStatements(StatementListContainer slc) {
		return true;
	}
}
