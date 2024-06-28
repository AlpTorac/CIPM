package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.SynchronizedBlock;

public interface ISynchronizedBlockInitialiser extends IBlockContainerInitialiser,
	IStatementInitialiser,
	IStatementListContainerInitialiser {
	
	public default void setLockProvider(SynchronizedBlock sb, Expression expr) {
		if (expr != null) {
			sb.setLockProvider(expr);
			assert sb.getLockProvider().equals(expr);
		}
	}
}
