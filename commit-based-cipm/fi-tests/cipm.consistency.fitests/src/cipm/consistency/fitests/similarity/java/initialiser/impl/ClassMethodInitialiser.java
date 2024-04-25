package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.ClassMethod;

import cipm.consistency.fitests.similarity.java.initialiser.IClassMethodInitialiser;

public class ClassMethodInitialiser implements IClassMethodInitialiser {
	@Override
	public ClassMethod instantiate() {
		return MembersFactory.eINSTANCE.createClassMethod();
	}
}
