package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberInitialiser;

public class FieldInitialiser extends MemberInitialiser implements IFieldInitialiser {
	@Override
	public Field instantiate() {
		return MembersFactory.eINSTANCE.createField();
	}
	
	@Override
	public FieldInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (FieldInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new FieldInitialiser();
	}
}
