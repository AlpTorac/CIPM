package cipm.consistency.fitests.similarity.java.initialiser.members;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class MembersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
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
