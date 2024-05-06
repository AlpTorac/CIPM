package cipm.consistency.fitests.similarity.java.initialiser.statements;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IStatementListContainerInitialiser;

public interface IBlockInitialiser extends IModifiableInitialiser, IMemberInitialiser,
	IStatementInitialiser, IStatementListContainerInitialiser {
}
