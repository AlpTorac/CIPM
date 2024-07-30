package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ParametersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this
				.initCol(new EObjectInitialiser[] { new CatchParameterInitialiser(), new OrdinaryParameterInitialiser(),
						new ReceiverParameterInitialiser(), new VariableLengthParameterInitialiser(), });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] { ICatchParameterInitialiser.class, IOrdinaryParameterInitialiser.class,
				IParameterInitialiser.class, IParametrizableInitialiser.class, IReceiverParameterInitialiser.class,
				IVariableLengthParameterInitialiser.class, });
	}
}
