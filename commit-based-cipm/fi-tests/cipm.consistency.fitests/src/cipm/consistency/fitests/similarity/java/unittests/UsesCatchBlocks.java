package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;

import cipm.consistency.fitests.similarity.java.initialiser.statements.CatchBlockInitialiser;

public interface UsesCatchBlocks extends UsesParameters {
	public default CatchBlock createMinimalCB(OrdinaryParameter op) {
		var cbInit = new CatchBlockInitialiser();
		var cb = cbInit.instantiate();
		cbInit.setParameter(cb, op);
		return cb;
	}
	
	public default CatchBlock createMinimalCB(String paramName, String targetName) {
		return this.createMinimalCB(this.createMinimalOrdParam(paramName, targetName));
	}
}
