package cipm.consistency.fitests.similarity.java.unittests.complextests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.emftext.language.java.statements.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementListContainerInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class StatementPositionTest extends EObjectSimilarityTest implements UsesStatements {
	private static List<IStatementListContainerInitialiser> getAllSLCInitInstances() {
		var res = new ArrayList<IStatementListContainerInitialiser>();
		var inits = new InitialiserParameters().getAdaptedInitialisersBySuper(IStatementListContainerInitialiser.class);
		inits.forEach((i) -> res.add(((IStatementListContainerInitialiser) i)));
		return res;
	}

//	private static List<IStatementInitialiser> getAllStatementInitInstances() {
//		var res = new ArrayList<IStatementInitialiser>();
//		var inits = new InitialiserParameters().getAdaptedInitialisersBySuper(IStatementInitialiser.class);
//		inits.forEach((i) -> res.add(((IStatementInitialiser) i)));
//		return res;
//	}

	private static Stream<Arguments> genTestParams() {
		return getAllSLCInitInstances().stream().map(Arguments::of);
	}

	@ParameterizedTest
	@MethodSource("genTestParams")
	public void testSameStatementPosition(IStatementListContainerInitialiser init) {
		this.setResourceFileTestIdentifier("testSameStatementPosition");

		var slc1 = init.instantiate();
		Assertions.assertTrue(init.initialise(slc1));
		var slc2 = init.instantiate();
		Assertions.assertTrue(init.initialise(slc2));

		var lv1name = "lv1";
		var lv2name = "lv2";
		var lv3name = "lv3";

		var st11 = this.createMinimalLVS(lv1name);
		var st12 = this.createMinimalLVS(lv2name);
		var st13 = this.createMinimalLVS(lv3name);
		var sts1 = new Statement[] { st11, st12, st13 };

		var st21 = this.createMinimalLVS(lv1name);
		var st22 = this.createMinimalLVS(lv2name);
		var st23 = this.createMinimalLVS(lv3name);
		var sts2 = new Statement[] { st21, st22, st23 };

		Assertions.assertTrue(init.addStatements(slc1, sts1));
		Assertions.assertTrue(init.addStatements(slc2, sts2));

		// Since statements are placed in a way that similar ones are at the same
		// positions, they are supposed to be similar, if i == j
		for (int i = 0; i < sts1.length; i++) {
			for (int j = 0; j < sts2.length; j++) {
				if (i == j) {
					this.assertSimilarityResult(sts1[i], sts2[j], true);
				} else {
					this.assertSimilarityResult(sts1[i], sts2[j], false);
				}
			}
		}
	}

	@ParameterizedTest
	@MethodSource("genTestParams")
	public void testDifferentStatementPosition(IStatementListContainerInitialiser init) {
		this.setResourceFileTestIdentifier("testDifferentStatementPosition");

		var slc1 = init.instantiate();
		Assertions.assertTrue(init.initialise(slc1));
		var slc2 = init.instantiate();
		Assertions.assertTrue(init.initialise(slc2));

		var lv1name = "lv1";
		var lv2name = "lv2";
		var lv3name = "lv3";

		var st11 = this.createMinimalLVS(lv1name);
		var st12 = this.createMinimalLVS(lv2name);
		var st13 = this.createMinimalLVS(lv3name);
		var sts1 = new Statement[] { st11, st12, st13 };

		var st21 = this.createMinimalLVS(lv1name);
		var st22 = this.createMinimalLVS(lv2name);
		var st23 = this.createMinimalLVS(lv3name);
		var sts2 = new Statement[] { st23, st22, st21 };

		Assertions.assertTrue(init.addStatements(slc1, sts1));
		Assertions.assertTrue(init.addStatements(slc2, sts2));

		// Even though statements on their own are similar, their positions are always
		// dissimilar here
		for (int i = 0; i < sts1.length; i++) {
			for (int j = 0; j < sts2.length; j++) {
				this.assertSimilarityResult(sts1[i], sts2[j], false);
			}
		}
	}
}
