package cipm.consistency.fitests.similarity.java;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;
import cipm.consistency.fitests.similarity.java.params.InitialiserTestSettingsProvider;
import cipm.consistency.fitests.similarity.java.params.SimilarityValues;

/**
 * Extends {@link AbstractSimilarityTest} with similarity checking methods to
 * avoid code duplication.
 * 
 * @author atora
 */
public class EObjectSimilarityTest extends AbstractSimilarityTest {
	/**
	 * The {@link IInitialiser} instance that is currently being used. <br>
	 * <br>
	 * Meant to help log parameterized tests better, since it is hard to match a
	 * certain initialiser instance to a given piece of log message otherwise.<br>
	 * <br>
	 * It is recommended to set it to null after each tests, so that
	 */
	private IInitialiser currentInit = null;

	@BeforeAll
	public static void setUpBeforeClass() {
		InitialiserTestSettingsProvider.initialise();
	}

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();

		this.setupInitialiserTestSettingsProvider();
	}

	@AfterEach
	@Override
	public void tearDown() {
		super.tearDown();

		this.resetInitialiserTestSettingsProvider();
	}

	/**
	 * Prepares {@link InitialiserTestSettingsProvider} for individual tests.
	 */
	protected void setupInitialiserTestSettingsProvider() {
		InitialiserTestSettingsProvider.getInstance().setParameters(new InitialiserParameters());
		InitialiserTestSettingsProvider.getInstance().setSimilarityValues(new SimilarityValues());
	}

	/**
	 * Resets {@link InitialiserTestSettingsProvider} after individual tests.
	 */
	protected void resetInitialiserTestSettingsProvider() {
		InitialiserTestSettingsProvider.getInstance().reset();
	}

	/**
	 * Logs a message indicating the end of a test case. <br>
	 * <br>
	 * The message is of form:
	 * {@code TestClass.testMethod [with SomethingInitialiser] ran} <br>
	 * <br>
	 * The part in square brackets is added only if the current initialiser is set
	 * via {@link #setCurrentInitialiser(IInitialiser)}.
	 */
	@Override
	protected void logTestEndMessage() {
		var endMsg = this.getResourceFileTestPrefix() + "." + this.getResourceFileTestIdentifier();

		if (this.currentInit != null) {
			endMsg += " with " + this.currentInit.getClass().getSimpleName();
		}

		endMsg += " ran";

		this.getLogger().info(endMsg);
	}

	@Override
	protected void resetAfterTest() {
		super.resetAfterTest();
		this.currentInit = null;
	}

	/**
	 * The variant of
	 * {@link #getExpectedSimilarityResult(Class, EStructuralFeature)} that uses the
	 * interface, which introduces attrKey to the {@link EObject} hierarchy first.
	 */
	public Boolean getExpectedSimilarityResult(EStructuralFeature attrKey) {
		return InitialiserTestSettingsProvider.getInstance().getSimilarityValues().getExpectedSimilarityResult(attrKey);
	}

	/**
	 * @param objCls  The interface of the {@link EObject} instances being compared
	 * @param attrKey The attribute, based on which the said instances are compared
	 * @return The expected similarity value for cases, where 2 instances of objCls
	 *         are compared, whose attribute (attrKey) is different.
	 */
	public Boolean getExpectedSimilarityResult(Class<? extends EObject> objCls, EStructuralFeature attrKey) {
		return InitialiserTestSettingsProvider.getInstance().getSimilarityValues().getExpectedSimilarityResult(objCls,
				attrKey);
	}

	/**
	 * Creates a clone copy of the given obj and its contents. <br>
	 * <br>
	 * <b>Note: DOES NOT clone the container {@code obj.eContainer()} of this
	 * object. Only copies the given object and the contents nested in it.</b>
	 * 
	 * @return A clone of obj without its container and clones of its contents.
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	public <T extends EObject> T cloneEObj(T obj) {
		return EcoreUtil.copy(obj);
	}

	/**
	 * Finds the topmost EObject (objTop) that can be reached from obj, clones
	 * objTop, finds the clone of obj among the contents of objTop and returns that
	 * clone.
	 * 
	 * @return A clone of obj, which preserves obj's place in its hierarchy. The
	 *         returned clone contains clones of obj's contents and is contained by
	 *         clones of all containers of obj.
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	@SuppressWarnings("unchecked")
	public <T extends EObject> T cloneEObjWithContainers(T obj) {
		if (obj.eContainer() == null) {
			return this.cloneEObj(obj);
		}

		EObject cObj = obj;

		while (cObj.eContainer() != null) {
			cObj = cObj.eContainer();
		}

		EObject clone = this.cloneEObj(cObj);
		var contents = clone.eAllContents();

		while (contents.hasNext()) {
			var cCloneObj = contents.next();
			if (this.getActualEquality(obj, cCloneObj)) {
				return (T) cCloneObj;
			}
		}

		Assertions.fail("Cloning unsuccessful");
		return null;
	}

	/**
	 * Creates a clone copy of all given objs.
	 * 
	 * @see {@link EcoreUtil#copyAll(Collection)}
	 */
	public <T extends EObject> Collection<T> cloneEObjList(Collection<T> objs) {
		return EcoreUtil.copyAll(objs);
	}

	/**
	 * Computes the equality of two {@link EObject} instances using
	 * {@link EcoreUtil}. <br>
	 * <br>
	 * <b>Note: The equality here is not the same as similarity checking that is
	 * being tested. This form of equality is much stricter than similarity, since
	 * there might be some attributes and/or nested classes, which are irrelevant
	 * for similarity in certain cases.</b>
	 */
	public boolean getActualEquality(EObject elem1, EObject elem2) {
		return EcoreUtil.equals(elem1, elem2);
	}

	/**
	 * Computes the equality of two lists of {@link EObject} using
	 * {@link EcoreUtil}. <br>
	 * <br>
	 * <b>Note: The equality here is not the same as similarity checking that is
	 * being tested. This form of equality is much stricter than similarity, since
	 * there might be some attributes and/or nested classes, which are irrelevant
	 * for similarity in certain cases.</b>
	 */
	public boolean getActualEquality(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		return EcoreUtil.equals(elems1, elems2);
	}

	/**
	 * Clones elem and compares it with its clone. They are expected to be similar.
	 */
	public void assertSimilar(EObject elem) {
		this.assertSimilarityResult(elem, elem, Boolean.TRUE);
	}

	/**
	 * Compares elem1 with elem2, expects the similarity result to be the same with
	 * the given expected value.
	 */
	public void assertSimilarityResult(EObject elem1, EObject elem2, Boolean expectedSimilarityResult) {
		if (expectedSimilarityResult == null) {
			this.getLogger().debug("No expected similarity result present");
		} else if ((!expectedSimilarityResult.booleanValue() && this.getActualEquality(elem1, elem2))) {
			this.getLogger().debug("Elements are expected to be different" + " in "
					+ this.getResourceFileTestIdentifier() + " but are similar according to EcoreUtil");
		}

		var objOne = this.cloneEObjWithContainers(elem1);
		var objTwo = this.cloneEObjWithContainers(elem2);

		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));

		Assertions.assertEquals(expectedSimilarityResult, this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + this.getActualEquality(objOne, objTwo));
	}

	/**
	 * Tests the similarity of the given elements:
	 * <ol>
	 * <li>Clones elem1 and compares it with its clone,
	 * <li>Clones elem2 and compares it with its clone,
	 * <li>Compares elem1 with elem2
	 * <li>Compares elem2 with elem1
	 * </ol>
	 * 
	 * @param objCls  The {@link EObject} sub-class that is the subject of
	 *                similarity checking. This parameter's purpose is providing a
	 *                way to allow manually inputting the (objCls, attr) pair. It is
	 *                important for cases, where an element contained by the given
	 *                elems is being modified indirectly.
	 * 
	 * @param attrKey The key of the attribute, based on which the similarity is
	 *                compared. If its type is {@link Boolean}, it is assumed to be
	 *                the expected result of the similarity comparing. Otherwise,
	 *                its type is assumed to be {@link EStructuralFeature}.
	 */
	public void testSimilarity(EObject elem1, EObject elem2, Class<? extends EObject> objCls, Object attrKey) {
		this.assertSimilar(elem1);
		this.assertSimilar(elem2);

		var key = attrKey instanceof Boolean ? (Boolean) attrKey
				: this.getExpectedSimilarityResult(objCls, (EStructuralFeature) attrKey);

		this.assertSimilarityResult(elem1, elem2, key);
		this.assertSimilarityResult(elem2, elem1, key);
	}

	/**
	 * The variant of {@link #testSimilarity(EObject, EObject, Class, Object)} that
	 * uses the same class as the given elems.
	 */
	@SuppressWarnings("unchecked")
	public void testSimilarity(EObject elem1, EObject elem2, Object attrKey) {
		this.testSimilarity(elem1, elem2, (Class<? extends EObject>) elem1.eClass().getInstanceClass(), attrKey);
	}

	/**
	 * Sets the currently used {@link IInitialiser} instance.<br>
	 * <br>
	 * Meant to help log parameterized tests better, since it is hard to match a
	 * certain initialiser instance to a given piece of log message otherwise.
	 */
	protected void setCurrentInitialiser(IInitialiser init) {
		this.currentInit = init;
	}
}
