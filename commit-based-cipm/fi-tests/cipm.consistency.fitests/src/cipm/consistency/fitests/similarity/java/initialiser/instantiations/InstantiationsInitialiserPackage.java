package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class InstantiationsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new ExplicitConstructorCallInitialiser(),
				new NewConstructorCallInitialiser(),
				new NewConstructorCallWithInferredTypeArgumentsInitialiser(),
		});
	}
}
