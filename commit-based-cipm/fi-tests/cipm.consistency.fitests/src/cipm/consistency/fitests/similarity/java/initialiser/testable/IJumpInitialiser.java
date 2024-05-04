package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IJumpInitialiser extends IStatementInitialiser {
	public default void setTarget(Jump jump, JumpLabel jl) {
		if (jl != null) {
			jump.setTarget(jl);
			assert jump.getTarget().equals(jl);
		}
	}
}
