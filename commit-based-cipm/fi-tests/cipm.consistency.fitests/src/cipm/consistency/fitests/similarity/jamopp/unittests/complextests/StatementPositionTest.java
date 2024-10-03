package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.params.JaMoPPInitialiserParameters;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.statements.IStatementInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementListContainerInitialiser;

/**
 * Tests whether similarity checking works as intended for {@link Statement}
 * instances, which are contained in {@link StatementListContainer} instances.
 * Since testing that requires setup effort that goes beyond simplistic tests,
 * it is done here. <br>
 * <br>
 * Tests are parameterised over all possible combinations of
 * {@link StatementListContainer} and {@link Statement} implementors.
 * 
 * @author atora
 */
public class StatementPositionTest extends AbstractJaMoPPSimilarityTest implements UsesStatements {
	private static List<IStatementListContainerInitialiser> getAllSLCInitInstances() {
		var res = new ArrayList<IStatementListContainerInitialiser>();
		var inits = new JaMoPPInitialiserParameters().getEachInitialiserOnceBySuper(IStatementListContainerInitialiser.class);
		inits.forEach((i) -> res.add(((IStatementListContainerInitialiser) i)));
		return res;
	}

	private static List<IStatementInitialiser> getAllStatementInitInstances() {
		var res = new ArrayList<IStatementInitialiser>();
		var inits = new JaMoPPInitialiserParameters().getEachInitialiserOnceBySuper(IStatementInitialiser.class);
		inits.forEach((i) -> res.add(((IStatementInitialiser) i)));
		return res;
	}

	/**
	 * TODO: Extract to a class later, if needed
	 * 
	 * @return Whether the position of an instance of the given class within its
	 *         container matters.
	 */
	private static Boolean doesStatementPositionMatter(Class<? extends Statement> cls) {
		return ExpressionStatement.class.isAssignableFrom(cls) || LocalVariableStatement.class.isAssignableFrom(cls)
				|| SynchronizedBlock.class.isAssignableFrom(cls);
	}

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
	 * Tests if similar {@link Statement} instances contained by their respective
	 * {@link StatementListContainer} instance are similar, provided they are
	 * contained in the same order.
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("genTestParams")
	public void testSameStatementPosition(String displayName, IStatementListContainerInitialiser containerInit,
			IStatementInitialiser containeeInit) {
		var slc1 = containerInit.instantiate();
		Assertions.assertTrue(containerInit.initialise(slc1));
		var slc2 = containerInit.instantiate();
		Assertions.assertTrue(containerInit.initialise(slc2));

		var lv1name = "lv1";
		var lv2name = "lv2";

		// Use 3 statements to make sure that there is always a predecessor and a
		// successor for the statement generated by containeeInit

		var st11 = this.createMinimalLVS(lv1name);
		var st12 = containeeInit.instantiate();
		var st13 = this.createMinimalLVS(lv2name);

		// Make sure that the surrounding statements are not similar
		this.assertSimilarityResult(st11, st13, false);
		var sts1 = new Statement[] { st11, st12, st13 };

		var st21 = this.cloneEObj(st11);
		var st22 = this.cloneEObj(st12);
		var st23 = this.cloneEObj(st13);

		// Make sure that the surrounding statements are not similar
		this.assertSimilarityResult(st21, st23, false);
		var sts2 = new Statement[] { st21, st22, st23 };

		Assertions.assertTrue(containerInit.addStatements(slc1, sts1));
		Assertions.assertTrue(containerInit.addStatements(slc2, sts2));

		// Since similar statements are added in the same order, they are similar, if
		// their
		// position within the respective arrays is the same
		for (int i = 0; i < sts1.length; i++) {
			for (int j = 0; j < sts2.length; j++) {
				var st1 = sts1[i];
				var st2 = sts2[j];

				this.assertSimilarityResult(st1, st2, i == j);
			}
		}
	}

	/**
	 * Tests if similar {@link Statement} instances contained by their respective
	 * {@link StatementListContainer} instance are not similar, provided their order
	 * within their container differ.
	 */
	@ParameterizedTest
	@MethodSource("genTestParams")
	public void testDifferentStatementPosition(String displayName, IStatementListContainerInitialiser containerInit,
			IStatementInitialiser containeeInit) {
		var containeeCls = containeeInit.instantiate().getClass();

		var slc1 = containerInit.instantiate();
		Assertions.assertTrue(containerInit.initialise(slc1));
		var slc2 = containerInit.instantiate();
		Assertions.assertTrue(containerInit.initialise(slc2));

		var lv1name = "lv1";
		var lv2name = "lv2";

		// Use 3 statements to make sure that there is always a predecessor and a
		// successor for the statement generated by containeeInit

		var st11 = this.createMinimalLVS(lv1name);
		var st12 = containeeInit.instantiate();
		var st13 = this.createMinimalLVS(lv2name);

		// Make sure that the surrounding statements are not similar
		this.assertSimilarityResult(st11, st13, false);
		var sts1 = new Statement[] { st11, st12, st13 };

		var st21 = this.cloneEObj(st11);
		var st22 = this.cloneEObj(st12);
		var st23 = this.cloneEObj(st13);

		// Make sure that the surrounding statements are not similar
		this.assertSimilarityResult(st21, st23, false);
		var sts2 = new Statement[] { st23, st22, st21 };

		Assertions.assertTrue(containerInit.addStatements(slc1, sts1));
		Assertions.assertTrue(containerInit.addStatements(slc2, sts2));

		// Since similar statements are added in the reverse order, only the statements
		// generated by containeeInit are similar, if similarity checking ignores their
		// positioning within their container. The order of the surrounding st variables
		// never match and they are not similar, as asserted above.
		for (int i = 0; i < sts1.length; i++) {
			for (int j = 0; j < sts2.length; j++) {
				var st1 = sts1[i];
				var st2 = sts2[j];

				this.assertSimilarityResult(st1, st2, containeeCls.isAssignableFrom(st1.getClass())
						&& containeeCls.isAssignableFrom(st2.getClass()) && !doesStatementPositionMatter(containeeCls));
			}
		}
	}
}
