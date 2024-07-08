package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.SynchronizedBlock;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface ISynchronizedBlockInitialiser extends IBlockContainerInitialiser,
	IStatementInitialiser,
	IStatementListContainerInitialiser {
	@Override
	public SynchronizedBlock instantiate();
	@ModificationMethod
	public default boolean setLockProvider(SynchronizedBlock sb, Expression expr) {
		if (expr != null) {
			sb.setLockProvider(expr);
			return sb.getLockProvider().equals(expr);
		}
		return true;
	}
}
