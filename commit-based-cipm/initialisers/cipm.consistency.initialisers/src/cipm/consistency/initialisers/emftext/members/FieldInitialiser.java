package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class FieldInitialiser extends AbstractInitialiserBase implements IFieldInitialiser {
	private IMemberContainerInitialiser mcInit;

	public FieldInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}

	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}

	@Override
	public Field instantiate() {
		return MembersFactory.eINSTANCE.createField();
	}

	@Override
	public FieldInitialiser newInitialiser() {
		return new FieldInitialiser();
	}
}