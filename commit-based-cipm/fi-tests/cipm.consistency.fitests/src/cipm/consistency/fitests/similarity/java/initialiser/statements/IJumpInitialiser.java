package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IJumpInitialiser extends IStatementInitialiser {
    @Override
    public Jump instantiate();
    @ModificationMethod
	public default boolean setTarget(Jump jump, JumpLabel jl) {
		if (jl != null) {
			jump.setTarget(jl);
			return jump.getTarget().equals(jl);
		}
		return true;
	}
}
