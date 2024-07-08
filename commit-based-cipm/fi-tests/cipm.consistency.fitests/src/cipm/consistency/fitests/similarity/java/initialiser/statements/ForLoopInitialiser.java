package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ForLoopInitialiser extends AbstractInitialiserBase implements IForLoopInitialiser {
	@Override
	public IForLoopInitialiser newInitialiser() {
		return new ForLoopInitialiser();
	}

	@Override
	public ForLoop instantiate() {
		return StatementsFactory.eINSTANCE.createForLoop();
	}
}