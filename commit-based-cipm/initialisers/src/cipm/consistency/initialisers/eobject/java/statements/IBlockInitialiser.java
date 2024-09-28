package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.initialisers.eobject.java.members.IMemberInitialiser;
import cipm.consistency.initialisers.eobject.java.modifiers.IModifiableInitialiser;

public interface IBlockInitialiser
		extends IModifiableInitialiser, IMemberInitialiser, IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public Block instantiate();

	@Override
	public default boolean canContainStatements(StatementListContainer slc) {
		return true;
	}
}
