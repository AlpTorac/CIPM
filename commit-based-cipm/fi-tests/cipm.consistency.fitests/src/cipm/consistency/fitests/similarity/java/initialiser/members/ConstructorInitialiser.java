package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockInitialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.members.Constructor;

public class ConstructorInitialiser implements IConstructorInitialiser, IBlockContainerHelperInitialiser {
	private IMemberContainerInitialiser mcInit;
	private IBlockInitialiser bInit;
	
	@Override
	public void minimalInitialisation(EObject obj) {
		IConstructorInitialiser.super.minimalInitialisation(obj);
		IBlockContainerHelperInitialiser.super.minimalInitialisation(obj);
	}
	
	@Override
	public Constructor instantiate() {
		return MembersFactory.eINSTANCE.createConstructor();
	}
	
	
	public ConstructorInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}
	
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}

	@Override
	public ConstructorInitialiser newInitialiser() {
		return new ConstructorInitialiser();
	}

	@Override
	public ConstructorInitialiser withBInit(IBlockInitialiser bInit) {
		this.bInit = bInit;
		return this;
	}

	@Override
	public IBlockInitialiser getBInit() {
		return this.bInit;
	}
}
