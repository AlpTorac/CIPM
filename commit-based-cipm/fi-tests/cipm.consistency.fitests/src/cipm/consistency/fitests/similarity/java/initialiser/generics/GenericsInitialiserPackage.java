package cipm.consistency.fitests.similarity.java.initialiser.generics;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class GenericsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new ExtendsTypeArgumentInitialiser(),
				new QualifiedTypeArgumentInitialiser(),
				new SuperTypeArgumentInitialiser(),
				new TypeParameterInitialiser(),
				new UnknownTypeArgumentInitialiser(),
		});
	}
}
