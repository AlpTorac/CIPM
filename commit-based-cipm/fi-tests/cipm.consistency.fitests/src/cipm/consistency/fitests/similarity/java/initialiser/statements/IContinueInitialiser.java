package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Continue;

public interface IContinueInitialiser extends IJumpInitialiser {
	@Override
	public Continue instantiate();

}
