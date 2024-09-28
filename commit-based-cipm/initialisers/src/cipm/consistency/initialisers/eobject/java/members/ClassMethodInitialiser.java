package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

import org.emftext.language.java.members.ClassMethod;

public class ClassMethodInitialiser extends AbstractInitialiserBase implements IClassMethodInitialiser {
	private IMemberContainerInitialiser mcInit;

	@Override
	public ClassMethod instantiate() {
		return MembersFactory.eINSTANCE.createClassMethod();
	}

	public ClassMethodInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}

	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}

	@Override
	public ClassMethodInitialiser newInitialiser() {
		return new ClassMethodInitialiser();
	}
}
