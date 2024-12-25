package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.stream.Stream;

import org.emftext.language.java.statements.BlockContainer;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;
import org.emftext.language.java.statements.StatementListContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.IStatementPositionTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.statements.BlockInitialiser;
import cipm.consistency.initialisers.jamopp.statements.IStatementListContainerInitialiser;

@Disabled("To be fixed")
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

	private void addStatementsInRange(StatementListContainer slc, IStatementListContainerInitialiser slcInit,
			Statement[] sts, int start, int end) {
		if (!slcInit.canContainStatements(slc)) {
			var block = new BlockInitialiser().instantiate();
			if (slc instanceof StatementContainer) {
				((StatementContainer) slc).setStatement(block);
			} else if (slc instanceof BlockContainer) {
				((BlockContainer) slc).setBlock(block);
			}
		}
		for (int i = start; i <= end; i++) {
			slcInit.addStatement(slc, sts[i]);
		}
		Assertions.assertEquals(end - start + 1, slc.getStatements().size());
	}

	@ParameterizedTest
	@MethodSource("genTestParams")
	public void test(IStatementListContainerInitialiser containerInit) {
		var stsLen = 5;

		for (int con1Start = 0; con1Start < stsLen; con1Start++) {
			for (int con1End = con1Start; con1End < stsLen; con1End++) {
				var con1 = containerInit.instantiate();

				var sts1 = this.createDistinctSts(stsLen);

				this.addStatementsInRange(con1, containerInit, sts1, con1Start, con1End);

				for (int con2Start = 0; con2Start < stsLen; con2Start++) {
					for (int con2End = con2Start; con2End < stsLen; con2End++) {
						var con2 = containerInit.instantiate();
						var sts2 = this.createDistinctSts(stsLen);

						this.addStatementsInRange(con2, containerInit, sts2, con2Start, con2End);

						for (int i = 0; i < stsLen; i++) {
							var st1 = sts1[i];
							var st2 = sts2[i];
							
							Statement st1pred = null;
							Statement st2pred = null;
							Statement st1succ = null;
							Statement st2succ = null;
							
							if (inIndexRange(i-1, con1Start, con1End)) {
								st1pred = sts1[i-1];
							}
							if (inIndexRange(i-1, con2Start, con2End)) {
								st2pred = sts2[i-1];
							}
							if (inIndexRange(i+1, con1Start, con1End)) {
								st1succ = sts1[i+1];
							}
							if (inIndexRange(i+1, con2Start, con2End)) {
								st2succ = sts2[i+1];
							}
							
							var con1BlockPos = i - con1Start;
							var con2BlockPos = i - con2Start;
							// Whether i is out of the block
							var outerBlockStatements = !hasContainer(st1) && !hasContainer(st2);
							// Whether i is inside the block
							var innerBlockStatements = hasContainer(st1) && hasContainer(st2);
							var adjacentStatementsDifferent = 
									(st1pred == null ^ st2pred == null) &&
									(st1succ == null ^ st2succ == null);
							var expectedResult = outerBlockStatements
									|| (innerBlockStatements && !adjacentStatementsDifferent);
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
	private boolean inIndexRange(int idx, int start, int end) {
		return idx >= start && idx < end;
	}
}
