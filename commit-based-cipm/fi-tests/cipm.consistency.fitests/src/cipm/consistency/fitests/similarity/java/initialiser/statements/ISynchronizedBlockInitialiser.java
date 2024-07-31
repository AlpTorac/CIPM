package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.SynchronizedBlock;

public interface ISynchronizedBlockInitialiser
		extends IBlockContainerInitialiser, IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public SynchronizedBlock instantiate();

	public default boolean setLockProvider(SynchronizedBlock sb, Expression lockProvider) {
		if (lockProvider != null) {
			sb.setLockProvider(lockProvider);
			return sb.getLockProvider().equals(lockProvider);
		}
		return true;
	}
}
