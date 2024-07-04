package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ArraysInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new ArrayDimensionInitialiser(),
				new ArrayInitializerInitialiser(),
				new ArrayInstantiationBySizeInitialiser(),
				new ArrayInstantiationByValuesTypedInitialiser(),
				new ArrayInstantiationByValuesUntypedInitialiser(),
				new ArraySelectorInitialiser()
		});
	}
}
