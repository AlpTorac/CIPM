package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ExpressionsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new AdditiveExpressionInitialiser(),
				new AndExpressionInitialiser(),
				new ArrayConstructorReferenceExpressionInitialiser(),
				new AssignmentExpressionInitialiser(),
				new CastExpressionInitialiser(),
				new ClassTypeConstructorReferenceExpressionInitialiser(),
				new ConditionalAndExpressionInitialiser(),
				new ConditionalExpressionInitialiser(),
				new ConditionalOrExpressionInitialiser(),
				new EqualityExpressionInitialiser(),
				new ExclusiveOrExpressionInitialiser(),
				new ExplicitlyTypedLambdaParametersInitialiser(),
				new ExpressionListInitialiser(),
				new ImplicitlyTypedLambdaParametersInitialiser(),
				new InclusiveOrExpressionInitialiser(),
				new InstanceOfExpressionInitialiser(),
				new LambdaExpressionInitialiser(),
				new MultiplicativeExpressionInitialiser(),
				new NestedExpressionInitialiser(),
				new PrefixUnaryModificationExpressionInitialiser(),
				new PrimaryExpressionReferenceExpressionInitialiser(),
				new RelationExpressionInitialiser(),
				new ShiftExpressionInitialiser(),
				new SingleImplicitLambdaParameterInitialiser(),
				new SuffixUnaryModificationExpressionInitialiser(),
				new UnaryExpressionInitialiser(),
		});
	}
}
