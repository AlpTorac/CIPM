package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Division;

public interface IDivisionInitialiser extends IMultiplicativeOperatorInitialiser {
	@Override
	public Division instantiate();

}
