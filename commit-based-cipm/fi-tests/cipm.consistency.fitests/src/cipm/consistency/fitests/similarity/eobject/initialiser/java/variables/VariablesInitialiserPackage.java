package cipm.consistency.fitests.similarity.eobject.initialiser.java.variables;

import java.util.Collection;

import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.initialiser.IInitialiserPackage;

public class VariablesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(
				new IInitialiser[] { new AdditionalLocalVariableInitialiser(), new LocalVariableInitialiser(), });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol(new Class[] { IAdditionalLocalVariableInitialiser.class, ILocalVariableInitialiser.class,
				IResourceInitialiser.class, IVariableInitialiser.class, });
	}
}
