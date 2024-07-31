package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;

public interface ICatchBlockInitialiser extends IBlockContainerInitialiser, IStatementListContainerInitialiser {

	@Override
	public CatchBlock instantiate();

	public default boolean setParameter(CatchBlock cb, OrdinaryParameter param) {
		if (param != null) {
			cb.setParameter(param);
			return cb.getParameter().equals(param);
		}
		return true;
	}
}
