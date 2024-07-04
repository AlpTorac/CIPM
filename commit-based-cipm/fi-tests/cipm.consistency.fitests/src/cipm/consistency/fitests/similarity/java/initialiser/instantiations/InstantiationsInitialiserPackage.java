package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class InstantiationsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new ExplicitConstructorCallInitialiser(),
				new NewConstructorCallInitialiser(),
				new NewConstructorCallWithInferredTypeArgumentsInitialiser(),
		});
	}
}
