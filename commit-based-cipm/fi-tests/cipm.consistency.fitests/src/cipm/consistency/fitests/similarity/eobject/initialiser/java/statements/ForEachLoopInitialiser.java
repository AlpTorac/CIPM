package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

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