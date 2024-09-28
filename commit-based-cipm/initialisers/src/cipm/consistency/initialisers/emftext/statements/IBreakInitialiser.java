package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.Break;

public interface IBreakInitialiser extends IJumpInitialiser {
	@Override
	public Break instantiate();

}
