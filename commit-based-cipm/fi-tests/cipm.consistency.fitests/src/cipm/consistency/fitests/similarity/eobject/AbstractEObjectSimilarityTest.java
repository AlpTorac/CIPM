package cipm.consistency.fitests.similarity.eobject;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.initialisers.eobject.IEObjectInitialiser;
import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.fitests.similarity.params.InitialiserTestSettingsProvider;

/**
 * An abstract class that extends {@link AbstractResourceSimilarityTest} with
 * various methods that can be used to test the similarity checking of
 * {@link EObject} instances. <br>
 * <br>
 * Integrates {@link InitialiserTestSettingsProvider}, in order to grant access
 * to expected similarity values.
 * 
 * @author atora
 * @see {@link InitialiserTestSettingsProvider#getSimilarityValues()} for more
 *      information on expected similarity values.
 */
public abstract class AbstractEObjectSimilarityTest extends AbstractResourceSimilarityTest {
	/**
	 * A helper class instance that can be used to perform various operations on
	 * {@link EObject} instances.
	 */
	private EcoreUtilHelper ecoreHelper;

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);
		this.ecoreHelper = new EcoreUtilHelper();
	}

	@AfterEach
	@Override
	public void tearDown() {
		this.ecoreHelper = null;
		this.resetInitialiserTestSettingsProvider();

		super.tearDown();
	}

	/**
	 * Consider adding default initialisation for
	 * {@link InitialiserTestSettingsProvider} if plausible to spare code
	 * duplication.
	 * 
	 * @return The {@link InitialiserTestSettingsProvider} that will be used in
	 *         tests.
	 */
	public abstract InitialiserTestSettingsProvider getInitialiserTestSettingsProvider();

	/**
	 * @return A helper class instance that can be used to perform various
	 *         operations on {@link EObject} instances.
	 */
	protected EcoreUtilHelper getEcoreUtilHelper() {
		return this.ecoreHelper;
	}

	/**
	 * Resets {@link #getInitialiserTestSettingsProvider()} after individual tests.
	 */
	protected void resetInitialiserTestSettingsProvider() {
		this.getInitialiserTestSettingsProvider().reset();
	}

	/**
	 * @return The {@link IInitialiserPackage} that is used to generate initialiser
	 *         parameters for tests.
	 */
	public IInitialiserPackage getUsedInitialiserPackage() {
		return this.getInitialiserTestSettingsProvider().getUsedInitialiserPackage();
	}

	/**
	 * The variant of
	 * {@link #getExpectedSimilarityResult(Class, EStructuralFeature)} that uses the
	 * interface, which introduces attrKey to the {@link EObject} hierarchy first.
	 */
	public Boolean getExpectedSimilarityResult(EStructuralFeature attrKey) {
		return this.getInitialiserTestSettingsProvider().getSimilarityValues().getExpectedSimilarityResult(attrKey);
	}

	/**
	 * @param objCls  The type of the {@link EObject} instances being compared
	 * @param attrKey The attribute, based on which the said instances are compared
	 * @return The expected similarity value for cases, where 2 instances of objCls
	 *         are compared, whose attribute (attrKey) is different.
	 */
	public Boolean getExpectedSimilarityResult(Class<? extends EObject> objCls, EStructuralFeature attrKey) {
		return this.getInitialiserTestSettingsProvider().getSimilarityValues().getExpectedSimilarityResult(objCls,
				attrKey);
	}

	/**
	 * See {@link EcoreUtilHelper#cloneEObj(EObject)}
	 */
	public <T extends EObject> T cloneEObj(T obj) {
		return this.getEcoreUtilHelper().cloneEObj(obj);
	}

	/**
	 * See {@link EcoreUtilHelper#cloneEObjWithContainers(EObject)}
	 */
	public <T extends EObject> T cloneEObjWithContainers(T obj) {
		var clone = this.getEcoreUtilHelper().cloneEObjWithContainers(obj);
		if (clone != null)
			return clone;

		Assertions.fail("Cloning with cloneEObjWithContainers failed");
		return null;
	}

	/**
	 * See {@link EcoreUtilHelper#cloneEObjList(Collection)}
	 */
	public <T extends EObject> Collection<T> cloneEObjList(Collection<T> objs) {
		return this.getEcoreUtilHelper().cloneEObjList(objs);
	}

	/**
	 * See {@link EcoreUtilHelper#getActualEquality(EObject, EObject)}
	 */
	public boolean getActualEquality(EObject elem1, EObject elem2) {
		return this.getEcoreUtilHelper().getActualEquality(elem1, elem2);
	}

	/**
	 * See {@link EcoreUtilHelper#getActualEquality(List, List)}
	 */
	public boolean getActualEquality(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		return this.getEcoreUtilHelper().getActualEquality(elems1, elems2);
	}

	/**
	 * Clones elem and compares it with its clone. They are expected to be
	 * similar.<br>
	 * <br>
	 * <b>Note: All given {@link EObject} instances will be cloned before tests to
	 * make sure that there are no side effects caused by the given {@link EObject}
	 * instances changing their container.</b> <br>
	 * <br>
	 */
	public void assertSimilar(EObject elem) {
		this.assertSimilarityResult(elem, elem, Boolean.TRUE);
	}

	/**
	 * Compares elem1 with elem2, expects the similarity result to be the same with
	 * the given expected value. <br>
	 * <br>
	 * <b>Note: All given {@link EObject} instances will be cloned before tests to
	 * make sure that there are no side effects caused by the given {@link EObject}
	 * instances changing their container.</b> <br>
	 * <br>
	 */
	public void assertSimilarityResult(EObject elem1, EObject elem2, Boolean expectedSimilarityResult) {
		if (expectedSimilarityResult == null) {
			this.getLogger().debug("No expected similarity result present");
		} else if ((!expectedSimilarityResult.booleanValue() && this.getActualEquality(elem1, elem2))) {
			this.getLogger().debug("Elements are expected to be different" + " in " + this.getCurrentTestMethodName()
					+ " but are similar according to EcoreUtil");
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
	 * <b>Note: All given {@link EObject} instances will be cloned before tests to
	 * make sure that there are no side effects caused by the given {@link EObject}
	 * instances changing their container.</b> <br>
	 * <br>
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
	 * uses the same class as the given elems. <br>
	 * <br>
	 * <b>Note: All given {@link EObject} instances will be cloned before tests to
	 * make sure that there are no side effects caused by the given {@link EObject}
	 * instances changing their container.</b> <br>
	 * <br>
	 */
	@SuppressWarnings("unchecked")
	public void testSimilarity(EObject elem1, EObject elem2, Object attrKey) {
		this.testSimilarity(elem1, elem2, (Class<? extends EObject>) elem1.eClass().getInstanceClass(), attrKey);
	}

	/**
	 * A variant of {@link #testSimilarity(EObject, EObject, Class, Object)} that
	 * constructs a minimal second element with the given
	 * {@link IEObjectInitialiser} instance and uses it as the second parameter in
	 * the said method. <br>
	 * <br>
	 * If initialiseSecondElement is set to true, constructs the second element with
	 * {@code init.initialise(init.instantiate())}. Otherwise uses
	 * {@code init.instantiate()}. <br>
	 * <br>
	 * Can be used to summarise the null check tests.<br>
	 * <br>
	 * <b>Note: All given {@link EObject} instances will be cloned before tests to
	 * make sure that there are no side effects caused by the given {@link EObject}
	 * instances changing their container.</b> <br>
	 * <br>
	 * 
	 * @param init                    The {@link IEObjectInitialiser} used in the
	 *                                construction of elem
	 * @param initialiseSecondElement Denotes whether {@code init.initialise(...)}
	 *                                will be used in the construction of the second
	 *                                element.
	 */
	public void testSimilarityNullCheck(EObject elem, IEObjectInitialiser init, boolean initialiseSecondElement,
			Class<? extends EObject> objCls, Object attrKey) {
		var elem2 = init.instantiate();

		if (initialiseSecondElement) {
			Assertions.assertTrue(init.initialise(elem2));
		}

		this.testSimilarity(elem, elem2, objCls, attrKey);
	}

	/**
	 * A variant of
	 * {@link #testSimilarityNullCheck(EObject, IEObjectInitialiser, boolean, Class, Object)}
	 * that computes the {@code objCls} parameter from the {@code elem}
	 * parameter.<br>
	 * <br>
	 * <b>Note: All given {@link EObject} instances will be cloned before tests to
	 * make sure that there are no side effects caused by the given {@link EObject}
	 * instances changing their container.</b> <br>
	 * <br>
	 */
	@SuppressWarnings("unchecked")
	public void testSimilarityNullCheck(EObject elem, IEObjectInitialiser init, boolean initialiseSecondElement,
			Object attrKey) {
		this.testSimilarityNullCheck(elem, init, initialiseSecondElement,
				(Class<? extends EObject>) elem.eClass().getInstanceClass(), attrKey);
	}
}
