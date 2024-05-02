package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberInitialiser;

import org.emftext.language.java.members.ClassMethod;

public class ClassMethodInitialiser extends MemberInitialiser implements IClassMethodInitialiser {
	@Override
	public ClassMethod instantiate() {
		return MembersFactory.eINSTANCE.createClassMethod();
	}
	
	@Override
	public ClassMethodInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (ClassMethodInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new ClassMethodInitialiser();
	}
}
