package cipm.consistency.fitests.similarity.java.initialiser.generics;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class GenericsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new ExtendsTypeArgumentInitialiser(),
				new QualifiedTypeArgumentInitialiser(),
				new SuperTypeArgumentInitialiser(),
				new TypeParameterInitialiser(),
				new UnknownTypeArgumentInitialiser(),
		});
	}
}
