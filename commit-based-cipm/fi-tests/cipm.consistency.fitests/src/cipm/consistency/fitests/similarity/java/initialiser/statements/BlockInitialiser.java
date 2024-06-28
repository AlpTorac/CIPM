package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.helper.IMemberHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Block;

public class BlockInitialiser implements IBlockInitialiser,
	IMemberHelperInitialiser,
	IBlockContainerContaineeInitialiser {
	private IMemberContainerInitialiser mcInit;
	private IBlockContainerInitialiser bcInit;
	
	@Override
	public EObject minimalInitialisationWithContainer(EObject obj) {
		IBlockInitialiser.super.minimalInitialisation(obj);
		return IBlockContainerContaineeInitialiser.super.minimalInitialisationWithContainer(obj);
	}
	
	@Override
	public Block instantiate() {
		return StatementsFactory.eINSTANCE.createBlock();
	}
	
	public BlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}

	@Override
	public BlockInitialiser newInitialiser() {
		return new BlockInitialiser();
	}

	@Override
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}

	@Override
	public BlockInitialiser withBCInit(IBlockContainerInitialiser bInit) {
		this.bcInit = bInit;
		return this;
	}

	@Override
	public IBlockContainerInitialiser getBCInit() {
		return this.bcInit;
	}
}