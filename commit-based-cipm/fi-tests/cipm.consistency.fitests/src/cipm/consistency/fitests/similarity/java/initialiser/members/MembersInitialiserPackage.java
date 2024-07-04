package cipm.consistency.fitests.similarity.java.initialiser.members;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class MembersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
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
}
