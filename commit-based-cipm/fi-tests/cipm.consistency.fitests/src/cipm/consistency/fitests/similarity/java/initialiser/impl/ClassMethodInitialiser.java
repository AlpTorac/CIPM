package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.ClassMethod;

import cipm.consistency.fitests.similarity.java.initialiser.IClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;

public class ClassMethodInitialiser extends MemberInitialiser implements IClassMethodInitialiser {
	@Override
	public ClassMethod instantiate() {
		return MembersFactory.eINSTANCE.createClassMethod();
	}
	
	@Override
	public ClassMethodInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (ClassMethodInitialiser) super.withMCInit(mcInit);
	}
}
