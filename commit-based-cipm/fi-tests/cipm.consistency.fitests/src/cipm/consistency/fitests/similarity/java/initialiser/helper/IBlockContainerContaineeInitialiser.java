package cipm.consistency.fitests.similarity.java.initialiser.helper;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IBlockContainerInitialiser;

public interface IBlockContainerContaineeInitialiser extends EObjectInitialiser {
	public IBlockContainerContaineeInitialiser withBCInit(IBlockContainerInitialiser bInit);
	public IBlockContainerInitialiser getBCInit();
	
	public default IBlockContainerInitialiser getDefaultBCInit() {
		return new ConstructorInitialiser();
	}
	
	@Override
	public default EObject minimalInitialisationWithContainer(EObject obj) {
		var castedO = (Block) obj;
				
		var bcInit = this.getBCInit();
		if (bcInit == null) {
			bcInit = this.getDefaultBCInit();
		}
		
		BlockContainer bc = bcInit.instantiate();
		bcInit.minimalInitialisation(bc);
		bcInit.setBlock(bc, castedO);
		
		return bc;
	}
}
