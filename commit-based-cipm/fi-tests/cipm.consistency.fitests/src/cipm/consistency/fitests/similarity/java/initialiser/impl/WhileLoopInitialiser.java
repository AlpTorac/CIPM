package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.WhileLoop;

import cipm.consistency.fitests.similarity.java.initialiser.IWhileLoopInitialiser;

public class WhileLoopInitialiser implements IWhileLoopInitialiser {
	@Override
	public IWhileLoopInitialiser newInitialiser() {
		return new WhileLoopInitialiser();
	}

	@Override
	public WhileLoop instantiate() {
		return StatementsFactory.eINSTANCE.createWhileLoop();
	}
}