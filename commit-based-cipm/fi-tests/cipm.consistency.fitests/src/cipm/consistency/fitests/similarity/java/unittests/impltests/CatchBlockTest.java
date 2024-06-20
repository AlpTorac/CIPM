package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.CatchBlockInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesParameters;

public class CatchBlockTest extends EObjectSimilarityTest implements UsesParameters {
	protected CatchBlock initElement(OrdinaryParameter oParam) {
		var cbInit = new CatchBlockInitialiser();
		var cb = cbInit.instantiate();
		cbInit.minimalInitialisation(cb);
		cbInit.setParameter(cb, oParam);
		return cb;
	}
	
	@Test
	public void testParameter() {
		this.setResourceFileTestIdentifier("testParameter");
		
		var objOne = this.initElement(this.createMinimalOrdParam("param1", "cls1"));
		var objTwo = this.initElement(this.createMinimalOrdParam("param2", "cls2"));
		
		this.testX(objOne, objTwo, false);
	}
}
