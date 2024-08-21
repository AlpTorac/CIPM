package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementListContainerInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link ClassMethod} instances. <br>
 * <br>
 * <b>Note: No statements can be added to {@link ClassMethod} via
 * {@code cm.getStatements().add(statement)}</b>
 * 
 * @author atora
 *
 */
public interface IClassMethodInitialiser extends IMethodInitialiser, IStatementListContainerInitialiser {
	@Override
	public ClassMethod instantiate();

	@Override
	public default boolean canContainStatements(StatementListContainer slc) {
		return ((ClassMethod) slc).getBlock() != null;
	}
}
