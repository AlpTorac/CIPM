package cipm.consistency.fitests.similarity.java.initialiser.operators;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class OperatorsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new AdditionInitialiser(),
				new AssignmentAndInitialiser(),
				new AssignmentDivisionInitialiser(),
				new AssignmentExclusiveOrInitialiser(),
				new AssignmentInitialiser(),
				new AssignmentLeftShiftInitialiser(),
				new AssignmentMinusInitialiser(),
				new AssignmentModuloInitialiser(),
				new AssignmentMultiplicationInitialiser(),
				new AssignmentOrInitialiser(),
				new AssignmentPlusInitialiser(),
				new AssignmentRightShiftInitialiser(),
				new AssignmentUnsignedRightShiftInitialiser(),
				new ComplementInitialiser(),
				new DivisionInitialiser(),
				new EqualInitialiser(),
				new GreaterThanInitialiser(),
				new GreaterThanOrEqualInitialiser(),
				new LeftShiftInitialiser(),
				new LessThanInitialiser(),
				new LessThanOrEqualInitialiser(),
				new MinusMinusInitialiser(),
				new MultiplicationInitialiser(),
				new NegateInitialiser(),
				new NotEqualInitialiser(),
				new PlusPlusInitialiser(),
				new RemainderInitialiser(),
				new RightShiftInitialiser(),
				new SubtractionInitialiser(),
				new UnsignedRightShiftInitialiser(),
		});
	}
}
