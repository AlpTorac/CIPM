package cipm.consistency.fitests.similarity.java.initialiser.variables;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class VariablesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new AdditionalLocalVariableInitialiser(),
				new LocalVariableInitialiser(),
		});
	}
}
