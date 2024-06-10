package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.splevo.jamopp.diffing.similarity.switches.CommonsSimilaritySwitch;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.java.utils.DummySimilaritySwitch;
import cipm.consistency.fitests.similarity.java.utils.InnerSwitchFactory;

public class NamedElementSimilarityTest extends EObjectSimilarityTest {
	@Override
	public InnerSwitchFactory initSwitchFactory() {
		return new InnerSwitchFactory() {
			@Override
			public List<Switch<Boolean>> createSwitchesFor(DummySimilaritySwitch dss) {
				return List.of(new CommonsSimilaritySwitch(dss, true) {
					@Override
					public Boolean caseCommentable(Commentable object) {
						this.logMessage("this.caseCommentable - " + object.getClass().getSimpleName());
						return this.caseNamedElement((NamedElement) object);
					}
					
					@Override
					public Boolean caseNamespaceAwareElement(NamespaceAwareElement object) {
						this.logMessage("this.caseNamespaceAwareElement - " + object.getClass().getSimpleName());
						return this.caseNamedElement((NamedElement) object);
					}
					
					@Override
					public Boolean caseNamedElement(NamedElement element1) {
						this.logMessage("this.caseNamedElement - " + element1.getClass().getSimpleName());
						this.logMessage("name = " + element1.getName());
						return super.caseNamedElement(element1);
					}
					
					@Override
					public Boolean defaultCase(EObject object) {
						this.logMessage("this.defaultCase - " + object.getClass().getSimpleName());
						return super.defaultCase(object);
					}
				});
			}
		};
	}
	
	protected NamedElement initElement(INamedElementInitialiser initialiser, String name) {
		NamedElement result = initialiser.instantiate();
		initialiser.initialiseName(result, name);
		return result;
	}
	
	/**
	 * Name differences break the similarity
	 */
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testName(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testName");
		
		var objOne = this.initElement(initialiser, "name11");
		var objTwo = this.initElement(initialiser, "name22");
		
		this.compareX(objOne, objTwo, false);
		this.getLogger().info("-------------------TEST DONE------------------------" + initialiser.getClass().getSimpleName());
	}
}
