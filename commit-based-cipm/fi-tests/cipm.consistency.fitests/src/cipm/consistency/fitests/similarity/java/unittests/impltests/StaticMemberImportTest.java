package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.imports.StaticMemberImportInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesMethods;

public class StaticMemberImportTest extends EObjectSimilarityTest implements UsesMethods {
	protected StaticMemberImport initElement(ReferenceableElement[] res) {
		var smiInit = new StaticMemberImportInitialiser();
		var smi = smiInit.instantiate();
		smiInit.minimalInitialisation(smi);
		smiInit.addStaticMembers(smi, res);
		return smi;
	}
	
	@Test
	public void testStaticMember() {
		this.setResourceFileTestIdentifier("testStaticMember");
		
		var objOne = this.initElement(new ReferenceableElement[] {this.createMinimalClsMethod("met1")});
		var objTwo = this.initElement(new ReferenceableElement[] {this.createMinimalClsMethod("met2")});
		
		this.compareX(objOne, objTwo, false);
	}
}
