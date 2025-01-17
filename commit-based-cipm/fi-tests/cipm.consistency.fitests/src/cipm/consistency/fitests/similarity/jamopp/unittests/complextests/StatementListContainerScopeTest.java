package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.emftext.language.java.statements.BlockContainer;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;
import org.emftext.language.java.statements.StatementListContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.IStatementPositionTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.IJaMoPPEObjectInitialiser;
import cipm.consistency.initialisers.jamopp.statements.BlockInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementListContainerInitialiser;

public class StatementListContainerScopeTest extends AbstractJaMoPPSimilarityTest
		implements UsesStatements, IStatementPositionTest {
	private static Stream<Arguments> genTestParams() {
		var args = new ArrayList<Arguments>();
		var inits = List
				.of(AbstractJaMoPPSimilarityTest.getNonAdaptedInitialisersFor(IStatementListContainerInitialiser.class)
						.stream().filter((i) -> IStatementInitialiser.class.isAssignableFrom(i.getClass()))
						.toArray(IJaMoPPEObjectInitialiser[]::new));
		for (var nestedCon : inits) {
			var displayName = nestedCon.getClass().getSimpleName() + " (nestedCon) in ";
			for (var placeholderCon : inits) {
				args.add(Arguments.of(displayName + placeholderCon.getClass().getSimpleName() + " (placeholderCon)",
						nestedCon, placeholderCon));
			}
		}
		return args.stream();
	}

	private Statement[] createDistinctSts(int count) {
		var sts = new Statement[count];

		for (int i = 0; i < sts.length; i++) {
			sts[i] = this.createMinimalLVS("lvs" + i);
		}

		for (int i = 0; i < sts.length; i++) {
			for (int j = 0; j < sts.length; j++) {
				Assertions.assertEquals(this.isSimilar(sts[i], sts[j]), i == j);
			}
		}

		return sts;
	}

	private void addBlockIfNecessary(StatementListContainer slc, IStatementListContainerInitialiser slcInit) {
		if (!slcInit.canContainStatements(slc)) {
			var block = new BlockInitialiser().instantiate();
			if (slc instanceof StatementContainer) {
				((StatementContainer) slc).setStatement(block);
			} else if (slc instanceof BlockContainer) {
				((BlockContainer) slc).setBlock(block);
			}
		}
	}

	/**
	 * Adds statements in sts within the range [start, end] (both inclusive) to slc.
	 * <br>
	 * <br>
	 * Adds a block to slc first, if statements cannot be added to it in its current
	 * form.
	 */
	private void addStatementsInRange(StatementListContainer slc, IStatementListContainerInitialiser slcInit,
			Statement[] sts, int start, int end) {
		this.addBlockIfNecessary(slc, slcInit);
		for (int i = start; i <= end; i++) {
			slcInit.addStatement(slc, sts[i]);
		}
		Assertions.assertEquals(end - start + 1, slc.getStatements().size());
	}

	private Statement[] setupForTest(IStatementListContainerInitialiser placeholderConInit,
			IStatementListContainerInitialiser containerInit, int conStart, int conEnd, int stsLen) {
		var placeholderCon = placeholderConInit.instantiate();
		this.addBlockIfNecessary(placeholderCon, placeholderConInit);
		var con = containerInit.instantiate();
		var sts = this.createDistinctSts(stsLen);

		/*
		 * Place all statements and the container inside a block, so that similarity
		 * checking can consider statement positions. Make sure to add them in the
		 * correct order to not mess up the intended ordering.
		 */
		for (int i = 0; i < conStart; i++) {
			placeholderConInit.addStatement(placeholderCon, sts[i]);
		}
		placeholderConInit.addStatement(placeholderCon, (Statement) con);
		for (int i = conEnd + 1; i < stsLen; i++) {
			placeholderConInit.addStatement(placeholderCon, sts[i]);
		}

		this.addStatementsInRange(con, containerInit, sts, conStart, conEnd);
		return sts;
	}

	/**
	 * Ensures that similarity checking detects differences caused by changes to the
	 * validity scope of {@link LocalVariableStatement} (LVS). <br>
	 * <br>
	 * For both sides: <br>
	 * <br>
	 * Generates a group of LVS instances, places them in a placeholderCon (so that
	 * they have a position within a container) and then nests a sub-group of the
	 * LVS instances in a further container (nestedCon) instantiated by
	 * containerInit. Then checks the similarity of all possible such sub-group
	 * combinations. <br>
	 * <br>
	 * The size of the said group of LVS instances (stsLen) should be >= 5: <br>
	 * <br>
	 * Similarity checking considers the statement itself, its predecessor and its
	 * successor. With 3 statements, it is possible to cover all basic cases.
	 * However, since the statements are nested within nestedCon, all said cases
	 * also have to be covered within nestedCon, as well as with the nestedCon
	 * itself as a statement within placeholderCon. This makes the following the
	 * most general form of statement placement, where st_i are statements:
	 * 
	 * placeholderCon(st_0 ... st_n nestedCon(st_n+1 ... st_m) st_m+1 ... st_k)
	 * 
	 * with 5 statements: placeholderCon(st_0 nestedCon(st_1 st_2 st_3) st_4)
	 * 
	 * @param displayName        The name of the tests' display
	 * @param nestedConInit      The initialiser responsible for instantiating the
	 *                           nestedCon
	 * @param placeholderConInit The initialiser responsible for instantiating the
	 *                           placeholderCon
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("genTestParams")
	public void testStatementListContainingStatementScope(String displayName,
			IStatementListContainerInitialiser nestedConInit, IStatementListContainerInitialiser placeholderConInit) {

		// FIXME Adapt the test after clarifying the Block situation

		var stsLen = 5;

		for (int nestedCon1Start = 0; nestedCon1Start < stsLen; nestedCon1Start++) {
			for (int nestedCon1End = nestedCon1Start; nestedCon1End < stsLen; nestedCon1End++) {

				var sts1 = this.setupForTest(placeholderConInit, nestedConInit, nestedCon1Start, nestedCon1End, stsLen);

				for (int nestedCon2Start = 0; nestedCon2Start < stsLen; nestedCon2Start++) {
					for (int nestedCon2End = nestedCon2Start; nestedCon2End < stsLen; nestedCon2End++) {

						var sts2 = this.setupForTest(placeholderConInit, nestedConInit, nestedCon2Start, nestedCon2End,
								stsLen);

						for (int i = 0; i < stsLen; i++) {
							var st1 = sts1[i];
							var st2 = sts2[i];

							var blockRelationSame =
									// Whether both st1 and st2 are inside / outside the block
									!(inIndexRange(i, nestedCon1Start, nestedCon1End)
											^ inIndexRange(i, nestedCon2Start, nestedCon2End)) && (
									// Whether both st1 and st2 have a / have no preceding statement in block range
									!(inIndexRange(i - 1, nestedCon1Start, nestedCon1End)
											^ inIndexRange(i - 1, nestedCon2Start, nestedCon2End)) ||
									// Whether both st1 and st2 have a / have no proceeding statement in block
									// range
											!(inIndexRange(i + 1, nestedCon1Start, nestedCon1End)
													^ inIndexRange(i + 1, nestedCon2Start, nestedCon2End)));

							Assertions.assertEquals(blockRelationSame, this.isSimilar(st1, st2));
							Assertions.assertEquals(blockRelationSame, this.isSimilar(st2, st1));
						}
					}
				}
			}
		}
	}

	/**
	 * @return Whether {@code start <= idx <= end}
	 */
	private boolean inIndexRange(int idx, int start, int end) {
		return idx >= start && idx <= end;
	}
}
