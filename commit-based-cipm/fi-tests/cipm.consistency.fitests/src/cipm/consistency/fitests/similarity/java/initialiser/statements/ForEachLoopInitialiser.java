package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ForEachLoopInitialiser extends AbstractInitialiserBase implements IForEachLoopInitialiser {
	@Override
	public IForEachLoopInitialiser newInitialiser() {
		return new ForEachLoopInitialiser();
	}

	@Override
	public ForEachLoop instantiate() {
		return StatementsFactory.eINSTANCE.createForEachLoop();
	}
}