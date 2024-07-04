package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ShiftExpressionChild;

public interface IShiftExpressionChildInitialiser extends IRelationExpressionChildInitialiser {
    @Override
    public ShiftExpressionChild instantiate();
	
}