package cipm.consistency.fitests.similarity.java.initialiser.variables;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class VariablesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new AdditionalLocalVariableInitialiser(),
				new LocalVariableInitialiser(),
		});
	}
}
