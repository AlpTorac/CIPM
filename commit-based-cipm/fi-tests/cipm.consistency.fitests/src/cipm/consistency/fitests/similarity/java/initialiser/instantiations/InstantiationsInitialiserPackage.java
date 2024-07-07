package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class InstantiationsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new ExplicitConstructorCallInitialiser(),
				new NewConstructorCallInitialiser(),
				new NewConstructorCallWithInferredTypeArgumentsInitialiser(),
		});
	}
	
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IExplicitConstructorCallInitialiser.class,
				IInitializableInitialiser.class,
				IInstantiationInitialiser.class,
				INewConstructorCallInitialiser.class,
				INewConstructorCallWithInferredTypeArgumentsInitialiser.class,
		});
	}
}
