package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.WhileLoop;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class WhileLoopInitialiser extends AbstractInitialiserBase implements IWhileLoopInitialiser {
	@Override
	public IWhileLoopInitialiser newInitialiser() {
		return new WhileLoopInitialiser();
	}

	@Override
	public WhileLoop instantiate() {
		return StatementsFactory.eINSTANCE.createWhileLoop();
	}
}