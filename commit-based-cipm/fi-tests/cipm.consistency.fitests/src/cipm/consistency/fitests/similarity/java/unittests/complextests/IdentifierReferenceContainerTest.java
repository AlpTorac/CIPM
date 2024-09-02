package cipm.consistency.fitests.similarity.java.unittests.complextests;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.IdentifierReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.ExplicitConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IdentifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ExpressionStatementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class IdentifierReferenceContainerTest extends EObjectSimilarityTest implements UsesConcreteClassifiers {

	/**
	 * Realises {@code JaMoPPElementUtil.getFirstContainerNotOfGivenType(...)}
	 */
	protected EObject getFirstEligibleContainer(IdentifierReference ref) {
		var currentContainer = ref.eContainer();

		while (currentContainer != null
				&& (currentContainer instanceof Expression || currentContainer instanceof ArraySelector)) {
			currentContainer = currentContainer.eContainer();
		}

		if (!(currentContainer instanceof Expression) && !(currentContainer instanceof ArraySelector)) {
			return currentContainer;
		}

		return null;
	}

	/**
	 * @return Whether the similarity of {@code ref_i.getTarget().eContainer()} will
	 *         be computed, where i = {1, 2}.
	 */
	protected boolean isTargetContainerSimilarityCheckReached(IdentifierReference ref1, IdentifierReference ref2) {
		var ref1Container = this.getFirstEligibleContainer(ref1);
		var ref2Container = this.getFirstEligibleContainer(ref2);

		var target1 = ref1.getTarget();
		var target2 = ref2.getTarget();

		EObject target1Container = null;
		if (target1 != null) {
			target1Container = target1.eContainer();
		}

		EObject target2Container = null;
		if (target2 != null) {
			target2Container = target2.eContainer();
		}

		return target1Container != ref1Container && target2Container != ref2Container &&

		// refX cannot be null and there is currently no EObject implementor that can be
		// the target of an IdentifierReference IR and have IR as its container (?)
		// Impossible to break the following conditions (?)
				target1Container != ref1 && target2Container != ref2;
	}

	/**
	 * Nests an {@link ExpressionStatement} es instance within an
	 * {@link ExplicitConstructorCall} ecc instance and sets ref's container to ecc.
	 * <br>
	 * <br>
	 * Can be used to add a container to ref (as in {@code ref.eContainer()}). <br>
	 * <br>
	 * <b>Note: ref's eligible container {@code this.getFirstEligibleContainer(ref)}
	 * will be es.</b>
	 */
	protected void initialiseIdentifierReference(IdentifierReference ref) {
		var insInit = new ExplicitConstructorCallInitialiser();
		var ecc = insInit.instantiate();
		Assertions.assertTrue(insInit.addArgument(ecc, ref));

		var esInit = new ExpressionStatementInitialiser();
		var es = esInit.instantiate();
		Assertions.assertTrue(esInit.setExpression(es, ecc));

		Assertions.assertEquals(ref.eContainer(), ecc);
		Assertions.assertEquals(this.getFirstEligibleContainer(ref), es);
	}

	/**
	 * Neither IdentifierReference instances have a container, neither targets have
	 * an eligible container
	 */
	@Test
	public void testBothTargetsNullContainer() {
		this.setResourceFileTestIdentifier("testBothTargetsNullContainer");

		var targetOne = this.createMinimalClass();
		var targetTwo = this.createMinimalClass();

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		// targetContainer == refContainer == null
		Assertions.assertFalse(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.TRUE);
	}

	/**
	 * Neither IdentifierReference instances have a container, only one target has
	 * an eligible container
	 */
	@Test
	public void testOneTargetNullContainer() {
		this.setResourceFileTestIdentifier("testOneTargetNullContainer");

		var targetOne = this.createMinimalClassWithCU();
		var targetTwo = this.createMinimalClass();

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		// targetContainer == refContainer == null
		Assertions.assertFalse(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.TRUE);
	}

	/**
	 * Neither IdentifierReference instances have a container, both targets have
	 * similar containers
	 */
	@Test
	public void testBothTargetsSimilarContainer() {
		this.setResourceFileTestIdentifier("testBothTargetsSimilarContainer");

		var targetOne = this.createMinimalClassWithCU();
		var targetTwo = this.createMinimalClassWithCU();

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		Assertions.assertTrue(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.TRUE);
	}

	/**
	 * Neither IdentifierReference instances have a container, both targets have
	 * different containers
	 */
	@Test
	public void testBothTargetsDifferentContainer() {
		this.setResourceFileTestIdentifier("testBothTargetsDifferentContainer");

		var targetOne = this.createMinimalClassifierWithCU(new ClassInitialiser(), "cls1", "cu1");
		var targetTwo = this.createMinimalClassifierWithCU(new ClassInitialiser(), "cls1", "cu2");

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		Assertions.assertTrue(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.FALSE);
	}

	/**
	 * One IdentifierReference instance has a container, same IdentifierReference
	 * has a target with an eligible container
	 */
	@Test
	public void testOneIROneTargetHaveContainer() {
		this.setResourceFileTestIdentifier("testOneIROneTargetHaveContainer");

		var targetOne = this.createMinimalClassWithCU();
		var targetTwo = this.createMinimalClass();

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		this.initialiseIdentifierReference(objOne);

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		// targetContainer == refContainer == null
		Assertions.assertFalse(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.TRUE);
	}

	/**
	 * One IdentifierReference instance has a container, the other
	 * IdentifierReference has a target with an eligible container
	 */
	@Test
	public void testDifferentIRAndTargetHaveContainer() {
		this.setResourceFileTestIdentifier("testDifferentIRAndTargetHaveContainer");

		var targetOne = this.createMinimalClass();
		var targetTwo = this.createMinimalClassWithCU();

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		this.initialiseIdentifierReference(objOne);

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		Assertions.assertTrue(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.FALSE);
	}

	/**
	 * Both IdentifierReference instances have a container each, Both targets have
	 * eligible similar containers
	 */
	@Test
	public void testBothSimilarTargetsHaveSimilarContainers() {
		this.setResourceFileTestIdentifier("testBothSimilarTargetsHaveSimilarContainers");

		var targetOne = this.createMinimalClassifierWithCU(new ClassInitialiser(), "cls1", "cu1");
		var targetTwo = this.createMinimalClassifierWithCU(new ClassInitialiser(), "cls1", "cu1");

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		this.initialiseIdentifierReference(objOne);

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		this.initialiseIdentifierReference(objTwo);

		Assertions.assertTrue(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.TRUE);
	}

	/**
	 * Both IdentifierReference instances have a container each, Both targets have
	 * eligible different containers
	 */
	@Test
	public void testBothSimilarTargetsHaveDifferentContainers() {
		this.setResourceFileTestIdentifier("testBothSimilarTargetsHaveDifferentContainers");

		var targetOne = this.createMinimalClassifierWithCU(new ClassInitialiser(), "cls1", "cu1");
		var targetTwo = this.createMinimalClassifierWithCU(new ClassInitialiser(), "cls1", "cu2");

		var objInit = new IdentifierReferenceInitialiser();
		var objOne = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objOne, targetOne));

		this.initialiseIdentifierReference(objOne);

		var objTwo = objInit.instantiate();
		Assertions.assertTrue(objInit.setTarget(objTwo, targetTwo));

		this.initialiseIdentifierReference(objTwo);

		Assertions.assertTrue(this.isTargetContainerSimilarityCheckReached(objOne, objTwo));
		this.testSimilarity(objOne, objTwo, Boolean.FALSE);
	}
}
