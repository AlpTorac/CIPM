package cipm.consistency.fitests.similarity.java.initialiser.generics;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class GenericsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new ExtendsTypeArgumentInitialiser(),
				new QualifiedTypeArgumentInitialiser(),
				new SuperTypeArgumentInitialiser(),
				new TypeParameterInitialiser(),
				new UnknownTypeArgumentInitialiser(),
		});
	}
	
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				ICallTypeArgumentableInitialiser.class,
				IExtendsTypeArgumentInitialiser.class,
				IQualifiedTypeArgumentInitialiser.class,
				ISuperTypeArgumentInitialiser.class,
				ITypeArgumentableInitialiser.class,
				ITypeArgumentInitialiser.class,
				ITypeParameterInitialiser.class,
				ITypeParametrizableInitialiser.class,
				IUnknownTypeArgumentInitialiser.class,	
		});
	}
}
