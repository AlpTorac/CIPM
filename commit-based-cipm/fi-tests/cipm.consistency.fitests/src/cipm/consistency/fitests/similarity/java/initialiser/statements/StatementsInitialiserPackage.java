package cipm.consistency.fitests.similarity.java.initialiser.statements;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class StatementsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
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
}
