package cipm.consistency.fitests.similarity.java.initialiser.members;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class MembersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new AdditionalFieldInitialiser(),
				new ClassMethodInitialiser(),
				new ConstructorInitialiser(),
				new EmptyMemberInitialiser(),
				new EnumConstantInitialiser(),
				new FieldInitialiser(),
				new InterfaceMethodInitialiser(),
		});
	}
	
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IAdditionalFieldInitialiser.class,
				IClassMethodInitialiser.class,
				IConstructorInitialiser.class,
				IEmptyMemberInitialiser.class,
				IEnumConstantInitialiser.class,
				IExceptionThrowerInitialiser.class,
				IFieldInitialiser.class,
				IInterfaceMethodInitialiser.class,
				IMemberContainerInitialiser.class,
				IMemberInitialiser.class,
				IMethodInitialiser.class,
		});
	}
}
