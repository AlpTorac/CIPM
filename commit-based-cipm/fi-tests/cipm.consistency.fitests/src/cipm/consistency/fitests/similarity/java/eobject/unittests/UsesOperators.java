package cipm.consistency.fitests.similarity.java.eobject.unittests;

import org.emftext.language.java.operators.Addition;
import org.emftext.language.java.operators.AssignmentOperator;
import org.emftext.language.java.operators.AssignmentOr;
import org.emftext.language.java.operators.Division;
import org.emftext.language.java.operators.EqualityOperator;
import org.emftext.language.java.operators.GreaterThan;
import org.emftext.language.java.operators.LeftShift;
import org.emftext.language.java.operators.LessThan;
import org.emftext.language.java.operators.MinusMinus;
import org.emftext.language.java.operators.Multiplication;
import org.emftext.language.java.operators.NotEqual;
import org.emftext.language.java.operators.PlusPlus;
import org.emftext.language.java.operators.RightShift;
import org.emftext.language.java.operators.Subtraction;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.AdditionInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.AssignmentInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.AssignmentOrInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.DivisionInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.EqualInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.GreaterThanInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.LeftShiftInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.LessThanInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.MinusMinusInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.MultiplicationInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.NotEqualInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.PlusPlusInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.RightShiftInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.operators.SubtractionInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link Operator} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Operator} instances.
 */
public interface UsesOperators {
	public default AssignmentOperator createAssignmentOperator() {
		return new AssignmentInitialiser().instantiate();
	}

	public default AssignmentOr createAssignmentOrOperator() {
		return new AssignmentOrInitialiser().instantiate();
	}

	public default EqualityOperator createEqualityOperator() {
		return new EqualInitialiser().instantiate();
	}

	public default NotEqual createNotEqualOperator() {
		return new NotEqualInitialiser().instantiate();
	}

	public default GreaterThan createGreaterThanOperator() {
		return new GreaterThanInitialiser().instantiate();
	}

	public default LessThan createLessThanOperator() {
		return new LessThanInitialiser().instantiate();
	}

	public default LeftShift createLeftShiftOperator() {
		return new LeftShiftInitialiser().instantiate();
	}

	public default RightShift createRightShiftOperator() {
		return new RightShiftInitialiser().instantiate();
	}

	public default Addition createAdditionOperator() {
		return new AdditionInitialiser().instantiate();
	}

	public default Subtraction createSubtractionOperator() {
		return new SubtractionInitialiser().instantiate();
	}

	public default Division createDivisionOperator() {
		return new DivisionInitialiser().instantiate();
	}

	public default Multiplication createMultiplicationOperator() {
		return new MultiplicationInitialiser().instantiate();
	}

	public default PlusPlus createPlusPlusOperator() {
		return new PlusPlusInitialiser().instantiate();
	}

	public default MinusMinus createMinusMinusOperator() {
		return new MinusMinusInitialiser().instantiate();
	}
}
