package cipm.consistency.fitests.similarity.java.initialiser.statements;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class StatementsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new AssertInitialiser(),
				new BlockInitialiser(),
				new BreakInitialiser(),
				new CatchBlockInitialiser(),
				new ConditionInitialiser(),
				new ContinueInitialiser(),
				new DefaultSwitchCaseInitialiser(),
				new DefaultSwitchRuleInitialiser(),
				new DoWhileLoopInitialiser(),
				new EmptyStatementInitialiser(),
				new ExpressionStatementInitialiser(),
				new ForEachLoopInitialiser(),
				new ForLoopInitialiser(),
				new JumpLabelInitialiser(),
				new LocalVariableStatementInitialiser(),
				new NormalSwitchCaseInitialiser(),
				new NormalSwitchRuleInitialiser(),
				new ReturnInitialiser(),
				new SwitchInitialiser(),
				new SynchronizedBlockInitialiser(),
				new ThrowInitialiser(),
				new TryBlockInitialiser(),
				new WhileLoopInitialiser(),
				new YieldStatementInitialiser(),
		});
	}
	
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IAssertInitialiser.class,
				IBlockContainerInitialiser.class,
				IBlockInitialiser.class,
				IBreakInitialiser.class,
				ICatchBlockInitialiser.class,
				IConditionalInitialiser.class,
				IConditionInitialiser.class,
				IContinueInitialiser.class,
				IDefaultSwitchCaseInitialiser.class,
				IDefaultSwitchRuleInitialiser.class,
				IDoWhileLoopInitialiser.class,
				IEmptyStatementInitialiser.class,
				IExpressionStatementInitialiser.class,
				IForEachLoopInitialiser.class,
				IForLoopInitialiser.class,
				IForLoopInitializerInitialiser.class,
				IJumpInitialiser.class,
				IJumpLabelInitialiser.class,
				ILocalVariableStatementInitialiser.class,
				INormalSwitchCaseInitialiser.class,
				INormalSwitchRuleInitialiser.class,
				IReturnInitialiser.class,
				IStatementContainerInitialiser.class,
				IStatementInitialiser.class,
				IStatementListContainerInitialiser.class,
				ISwitchCaseInitialiser.class,
				ISwitchInitialiser.class,
				ISwitchRuleInitialiser.class,
				ISynchronizedBlockInitialiser.class,
				IThrowInitialiser.class,
				ITryBlockInitialiser.class,
				IWhileLoopInitialiser.class,
				IYieldStatementInitialiser.class,
		});
	}
}
