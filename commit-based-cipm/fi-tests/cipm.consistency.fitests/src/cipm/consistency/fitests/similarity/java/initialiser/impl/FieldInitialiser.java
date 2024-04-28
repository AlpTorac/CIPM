package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IFieldInitialiser;

public class FieldInitialiser implements IFieldInitialiser, IInitialiser<Field> {
	@Override
	public Field instantiate() {
		return MembersFactory.eINSTANCE.createField();
	}
}
