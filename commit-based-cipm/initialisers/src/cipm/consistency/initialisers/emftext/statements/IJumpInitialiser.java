package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;

public interface IJumpInitialiser extends IStatementInitialiser {
	@Override
	public Jump instantiate();

	public default boolean setTarget(Jump jump, JumpLabel target) {
		if (target != null) {
			jump.setTarget(target);
			return jump.getTarget().equals(target);
		}
		return true;
	}
}
