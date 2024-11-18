package cipm.consistency.fitests.similarity.jamopp.unittests.mocktests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.variables.LocalVariable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.params.JaMoPPInitialiserParameters;
import cipm.consistency.fitests.similarity.jamopp.unittests.IStatementPositionTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.eobject.IEObjectInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementListContainerInitialiser;

/**
 * Contains tests that check whether the necessary null checks are present in
 * cases, where the focus lies on relative statement positions
 * (preceding/proceeding statements). <br>
 * <br>
 * Although the scenarios described here may be currently unreachable, testing
 * for them ensures that changes to the order, in which preceding/proceeding
 * statements are checked, do not introduce errors to the similarity checking
 * process.
 * 
 * @author Alp Torac Genc
 */
public class StatementPositionMockTest extends AbstractJaMoPPSimilarityTest
		implements UsesStatements, IMockTest, IStatementPositionTest {
	/**
	 * @return A list of all initialisers that implement
	 *         {@link IStatementListContainerInitialiser}. If an initialiser is
	 *         adaptable, it will be adapted. Non-adaptable initialisers will be
	 *         unaffected.
	 */
	private static List<IStatementListContainerInitialiser> getAllSLCInitInstances() {
		var res = new ArrayList<IStatementListContainerInitialiser>();
		var inits = new JaMoPPInitialiserParameters()
				.getEachInitialiserOnceBySuper(IStatementListContainerInitialiser.class);
		inits.forEach((i) -> res.add(((IStatementListContainerInitialiser) i)));
		return res;
	}

	/**
	 * @return A list of all initialisers that implement
	 *         {@link IStatementInitialiser}. If an initialiser is adaptable, it
	 *         will be adapted. Non-adaptable initialisers will be unaffected.
	 */
	private static List<IStatementInitialiser> getAllStatementInitInstances() {
		var res = new ArrayList<IStatementInitialiser>();
		var inits = new JaMoPPInitialiserParameters().getEachInitialiserOnceBySuper(IStatementInitialiser.class);
		inits.forEach((i) -> res.add(((IStatementInitialiser) i)));
		return res;
	}

	/**
	 * @return Parameters for the test methods in this test class. Refer to their
	 *         documentation for more information.
	 */
	private static Stream<Arguments> genTestParams() {
		var res = new ArrayList<Arguments>();

		for (var stInit : getAllStatementInitInstances()) {
			for (var slcInit : getAllSLCInitInstances()) {
				var displayName = stInit.getClass().getSimpleName() + " in " + slcInit.getClass().getSimpleName();

				res.add(Arguments.of(displayName, slcInit, stInit));
			}
		}

		return res.stream();
	}

	/**
	 * TODO Move to IEObjectInitialiser in the future
	 * 
	 * @param init An initialiser
	 * @return The type of what the given initialiser instantiates.
	 */
	private Class<?> getInstanceClassOfInitialiser(IEObjectInitialiser init) {
		return init.instantiate().eClass().getInstanceClass();
	}

	/**
	 * @return A mock of {@link LocalVariableStatement} in form of an instance,
	 *         whose container is the given one and whose local variable is the
	 *         given one.
	 * 
	 * @see {@link #mockEObject(Class)}
	 * @see {@link #mockEObjectWithContainer(Class, EObject)}
	 */
	private LocalVariableStatement mockLVS(LocalVariable lVar, EObject container) {
		var mockLVS = this.mockEObjectWithContainer(LocalVariableStatement.class, container);
		when(mockLVS.getVariable()).thenReturn(lVar);
		return mockLVS;
	}

	/**
	 * Tests similarity checking of {@link Statement} and
	 * {@link StatementListContainer} sub-types for the case, where
	 * {@code statementListContainer.getStatements()} returns null after certain
	 * amounts of calls. <br>
	 * <br>
	 * Runs {@link #testBody(Class, Class, int, int)} for each combination (i, j);
	 * where i,j = {0, ..., N} are the int parameters, for each combination of
	 * statement-statementListContainer sub-types. N should be chosen as the largest
	 * number, such that at least one null checking mechanism regarding
	 * {@code statementListContainer.getStatements()} is reached. This way, all
	 * relevant null checking mechanisms can be tested. If N is too high, some test
	 * runs will fail. <br>
	 * <br>
	 * <i><b>!!! Note that i and j have to be synchronised with changes to the
	 * similarity checking process !!!</b></i> <br>
	 * <br>
	 * The aim of this test method is to ensure the robustness of similarity
	 * checking regarding statement positions (preceding/proceeding statements). In
	 * particular, this test covers some branches that are currently unreachable,
	 * yet may become relevant in the future (for example if the order of
	 * preceding/proceeding statement checks are changed).
	 * 
	 * @param displayName   The display name of the test
	 * @param containerInit The initialiser corresponding to the statement list
	 *                      container sub-type
	 * @param containeeInit The initialiser corresponding to the statement that will
	 *                      be added to the container
	 */
	@SuppressWarnings("unchecked")
	@ParameterizedTest
	@MethodSource("genTestParams")
	public void test_StatementPosition_MalfunctioningStatementRetrieval(String displayName,
			IStatementListContainerInitialiser containerInit, IStatementInitialiser containeeInit) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.testBody(
						(Class<? extends StatementListContainer>) this.getInstanceClassOfInitialiser(containerInit),
						(Class<? extends Statement>) this.getInstanceClassOfInitialiser(containeeInit), i, j);
			}
		}
	}

	/**
	 * Tests if {@link Statement} mocks contained by their respective
	 * {@link StatementListContainer} mocks are similar, if
	 * {@code statementListContainer.getStatements()} malfunctions for both sides at
	 * some point.
	 * 
	 * @param containerCls               The class of the statement list container
	 *                                   sub-type, which will be mocked and used as
	 *                                   the container
	 * @param containeeCls               The class of the statement that will be
	 *                                   placed into the mocked container
	 * @param lhsStatementRetrievalCount The amount of times the left hand side
	 *                                   statement list container's
	 *                                   {@code getStatements()} method works as
	 *                                   intended. Once this count drops to 0, the
	 *                                   said method will return null instead.
	 * @param rhsStatementRetrievalCount The amount of times the right hand side
	 *                                   statement list container's
	 *                                   {@code getStatements()} method works as
	 *                                   intended. Once this count drops to 0, the
	 *                                   said method will return null instead.
	 */
	public void testBody(Class<? extends StatementListContainer> containerCls, Class<? extends Statement> containeeCls,
			int lhsStatementRetrievalCount, int rhsStatementRetrievalCount) {
		var slc1 = this.mockEObject(containerCls);
		var slc2 = this.mockEObject(containerCls);

		/*
		 * Mock the surrounding statements as well as the statements under test, because
		 * there is no other way to add them as statements to slc1 or slc2.
		 */

		var pred1 = this.mockLVS(this.createMinimalLV("lv1"), slc1);
		var st1 = this.mockEObjectWithContainer(containeeCls, slc1);
		var succ1 = this.mockLVS(this.createMinimalLV("lv2"), slc1);

		var pred2 = this.mockLVS(this.createMinimalLV("lv1"), slc2);
		var st2 = this.mockEObjectWithContainer(containeeCls, slc2);
		var succ2 = this.mockLVS(this.createMinimalLV("lv2"), slc2);

		var sts1 = new Statement[] { pred1, st1, succ1 };
		var sts2 = new Statement[] { pred2, st2, succ2 };

		var getStsCount1 = this.setUpSLCMock(slc1, sts1, lhsStatementRetrievalCount);
		var getStsCount2 = this.setUpSLCMock(slc2, sts2, rhsStatementRetrievalCount);

		// Make sure that the surrounding expressions
		// are similar/not similar as intended
		this.assertSimilarityResultEquals(pred1, pred2, true, getStsCount1, lhsStatementRetrievalCount, getStsCount2,
				rhsStatementRetrievalCount);
		this.assertSimilarityResultEquals(pred1, succ2, false, getStsCount1, lhsStatementRetrievalCount, getStsCount2,
				rhsStatementRetrievalCount);
		this.assertSimilarityResultEquals(succ1, pred2, false, getStsCount1, lhsStatementRetrievalCount, getStsCount2,
				rhsStatementRetrievalCount);
		this.assertSimilarityResultEquals(succ1, succ2, true, getStsCount1, lhsStatementRetrievalCount, getStsCount2,
				rhsStatementRetrievalCount);

		/*
		 * The construction above leads to preceding and proceeding statements to be
		 * detected as similar, as statement retrieval fails. This in return causes the
		 * similarity result to be true, since both surrounding statements are assumed
		 * to be similar.
		 * 
		 * Keep in mind that this part will fail, if retrievalCounts are chosen too
		 * high. If the similarity result becomes irrelevant, one could modify the
		 * assertion method to instead check whether isSimilar(st1, st2) and
		 * isSimilar(st2, st1) are equal. Do not forget to reset the retrievalCounts
		 * after each isSimilar call.
		 */
		this.assertSimilarityResultEquals(st1, st2, true, getStsCount1, lhsStatementRetrievalCount, getStsCount2,
				rhsStatementRetrievalCount);
	}

	/**
	 * Asserts that the similarity checking result of obj1 and obj2 is expectedVal
	 * and resets the getStatement call counts.
	 */
	private void assertSimilarityResultEquals(Object obj1, Object obj2, boolean expectedVal, final int[] lhsCallCount,
			int lhsStatementRetrievalCount, final int[] rhsCallCount, int rhsStatementRetrievalCount) {
		Assertions.assertEquals(expectedVal, this.isSimilar(obj1, obj2));
		// Reset call counts after each similarity check
		lhsCallCount[0] = lhsStatementRetrievalCount;
		rhsCallCount[0] = rhsStatementRetrievalCount;
	}

	/**
	 * Sets up the given {@link StatementListContainer} mock {@code slc} in a way
	 * that {@code slc.getStatements()} returns the expected output
	 * (statementsToContain in list form) only statementRetrievalCount times. Once
	 * it reaches 0, the said method starts to return null instead.
	 * 
	 * @param slc                     A {@link StatementListContainer} mock to set
	 *                                up or reset
	 * @param statementsToContain     The statements that slc is supposed to contain
	 * @param statementRetrievalCount The amount of times
	 *                                {@code slc.getStatements()} returns the
	 *                                expected output (statementsToContain in list
	 *                                form) only statementRetrievalCount times
	 * 
	 * @return A final int array, which stores the amount of times
	 *         {@code slc.getStatements()} returns the expected value. Once the int
	 *         within reaches 0, the said method starts to return null.
	 */
	private final int[] setUpSLCMock(StatementListContainer slc, Statement[] statementsToContain,
			int statementRetrievalCount) {

		final int[] getStatementsCallCount = new int[] { statementRetrievalCount };
		when(slc.getStatements()).thenAnswer(new Answer<EList<Statement>>() {
			@Override
			public EList<Statement> answer(InvocationOnMock arg0) throws Throwable {
				if (getStatementsCallCount[0] > 0) {
					getStatementsCallCount[0] -= 1;
					var list = new UniqueEList<Statement>();
					for (var st : statementsToContain) {
						list.add(st);
					}
					return list;
				} else {
					return null;
				}
			}
		});

		return getStatementsCallCount;
	}
}
