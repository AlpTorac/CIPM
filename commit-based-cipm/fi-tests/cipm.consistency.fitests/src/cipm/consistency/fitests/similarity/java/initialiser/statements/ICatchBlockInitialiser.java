package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;

public interface ICatchBlockInitialiser extends IBlockContainerInitialiser,
	IStatementListContainerInitialiser {
	
	public default void setParameter(CatchBlock cb, OrdinaryParameter oParam) {
		if (oParam != null) {
			cb.setParameter(oParam);
			assert cb.getParameter().equals(oParam);
		}
	}
}
