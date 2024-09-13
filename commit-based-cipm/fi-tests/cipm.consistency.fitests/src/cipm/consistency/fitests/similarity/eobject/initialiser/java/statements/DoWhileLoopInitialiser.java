package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.DoWhileLoop;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class DoWhileLoopInitialiser extends AbstractInitialiserBase implements IDoWhileLoopInitialiser {
	@Override
	public IDoWhileLoopInitialiser newInitialiser() {
		return new DoWhileLoopInitialiser();
	}

	@Override
	public DoWhileLoop instantiate() {
		return StatementsFactory.eINSTANCE.createDoWhileLoop();
	}
}