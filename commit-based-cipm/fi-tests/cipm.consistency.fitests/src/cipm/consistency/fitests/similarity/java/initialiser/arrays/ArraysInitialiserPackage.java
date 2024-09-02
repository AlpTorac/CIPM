package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ArraysInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this
				.initCol(new EObjectInitialiser[] { new ArrayDimensionInitialiser(), new ArrayInitializerInitialiser(),
						new ArrayInstantiationBySizeInitialiser(), new ArrayInstantiationByValuesTypedInitialiser(),
						new ArrayInstantiationByValuesUntypedInitialiser(), new ArraySelectorInitialiser() });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] { IArrayDimensionInitialiser.class, IArrayInitializationValueInitialiser.class,
				IArrayInitializerInitialiser.class, IArrayInstantiationBySizeInitialiser.class,
				IArrayInstantiationByValuesInitialiser.class, IArrayInstantiationByValuesTypedInitialiser.class,
				IArrayInstantiationByValuesUntypedInitialiser.class, IArrayInstantiationInitialiser.class,
				IArraySelectorInitialiser.class, IArrayTypeableInitialiser.class });
	}
}
