package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

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
import cipm.consistency.initialisers.jamopp.statements.BlockInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementListContainerInitialiser;

public class StatementListContainerScopeTest extends AbstractJaMoPPSimilarityTest
		implements UsesStatements, IStatementPositionTest {
	private static Stream<Arguments> genTestParams() {
		return AbstractJaMoPPSimilarityTest
				.getNonAdaptedInitialiserArgumentsFor(IStatementListContainerInitialiser.class);
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

	@ParameterizedTest
	@MethodSource("genTestParams")
	public void test(IStatementListContainerInitialiser containerInit) {
		/*
		 * 5 statements are needed to cover all possible cases:
		 * 
		 * Similarity checking considers the statement itself, its predecessor and its
		 * successor. With 3 statements, it is possible to cover all basic cases.
		 * However, since there is a block involved, all said cases also have to be
		 * covered within the block, as well as with the block. This makes the following
		 * the most general form of statement placement, where st_i are statements and
		 * block(...) represents a statement list container:
		 * 
		 * st_0 ... st_n block(st_n+1 ... st_m) st_m+1 ... st_k
		 * 
		 * with 5 statements: st_0 block(st_1 st_2 st_3) st_4
		 * 
		 * The entirety of the variations of above are enough to cover all cases.
		 */
		var stsLen = 5;
		var placeholderConInit = new BlockInitialiser();

		for (int con1Start = 0; con1Start < stsLen; con1Start++) {
			for (int con1End = con1Start; con1End < stsLen; con1End++) {
				var placeholderCon1 = placeholderConInit.instantiate();
				var con1 = containerInit.instantiate();
				var sts1 = this.createDistinctSts(stsLen);

				/*
				 * Place all statements and the container inside a block, so that similarity
				 * checking can consider statement positions.
				 */
				placeholderConInit.addStatements(placeholderCon1, sts1);
				if (con1 instanceof Statement) {
					placeholderConInit.addStatement(placeholderCon1, (Statement) con1);
				}

				this.addStatementsInRange(con1, containerInit, sts1, con1Start, con1End);

//				for (int i = 0; i < con1Start; i++) {
//					placeholderConInit.addStatement(placeholderCon1, sts1[i]);
//				}
				
				for (int con2Start = 0; con2Start < stsLen; con2Start++) {
					for (int con2End = con2Start; con2End < stsLen; con2End++) {
						var placeholderCon2 = placeholderConInit.instantiate();
						var con2 = containerInit.instantiate();
						var sts2 = this.createDistinctSts(stsLen);

						/*
						 * Place all statements and the container inside a block, so that similarity
						 * checking can consider statement positions.
						 */
						placeholderConInit.addStatements(placeholderCon2, sts2);
						if (con2 instanceof Statement) {
							placeholderConInit.addStatement(placeholderCon2, (Statement) con2);
						}

						this.addStatementsInRange(con2, containerInit, sts2, con2Start, con2End);

						for (int i = 0; i < stsLen; i++) {
							var st1 = sts1[i];
							var st2 = sts2[i];

							var blockRelationSame =
									// Whether both are in/out a block
									!(inIndexRange(i, con1Start, con1End) ^ inIndexRange(i, con2Start, con2End)) &&
									// Whether both have a / have no preceding block
									// !(blockPreceedes(i, con1Start, con1End) ^ blockPreceedes(i, con2Start,
									// con2End)) &&
									// Whether both have a / have no proceeding block
									// !(blockProceedes(i, con1Start, con1End) ^ blockProceedes(i, con2Start,
									// con2End)) &&

											(
											// Whether both have a / have no preceding statement in block range
											!(inIndexRange(i - 1, con1Start, con1End)
													^ inIndexRange(i - 1, con2Start, con2End)) ||
											// Whether both have a / have no proceeding statement in block range
													!(inIndexRange(i + 1, con1Start, con1End)
															^ inIndexRange(i + 1, con2Start, con2End)));

							var expectedResult = blockRelationSame;
							
							Assertions.assertEquals(expectedResult, this.isSimilar(st1, st2));
							Assertions.assertEquals(expectedResult, this.isSimilar(st2, st1));
						}
					}
				}
			}
		}
	}

	private boolean hasContainer(Statement st) {
		return st != null && st.eContainer() != null;
	}

	/**
	 * @return Whether {@code start <= idx <= end}
	 */
	private boolean inIndexRange(int idx, int start, int end) {
		return idx >= start && idx <= end;
	}

	private boolean blockPreceedes(int idx, int start, int end) {
		return !inIndexRange(idx, start, end) && inIndexRange(idx - 1, start, end);
	}

	private boolean blockProceedes(int idx, int start, int end) {
		return !inIndexRange(idx, start, end) && inIndexRange(idx + 1, start, end);
	}
}
