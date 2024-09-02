package cipm.consistency.fitests.similarity.java.initialiser.variables;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class VariablesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(
				new EObjectInitialiser[] { new AdditionalLocalVariableInitialiser(), new LocalVariableInitialiser(), });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] { IAdditionalLocalVariableInitialiser.class, ILocalVariableInitialiser.class,
				IResourceInitialiser.class, IVariableInitialiser.class, });
	}
}
