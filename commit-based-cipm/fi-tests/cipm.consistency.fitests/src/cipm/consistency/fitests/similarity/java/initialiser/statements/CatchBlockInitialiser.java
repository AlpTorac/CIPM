package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerHelperInitialiser;

public class CatchBlockInitialiser implements ICatchBlockInitialiser, IBlockContainerHelperInitialiser {
	private IBlockInitialiser bInit;
	
	@Override
	public CatchBlock instantiate() {
		return StatementsFactory.eINSTANCE.createCatchBlock();
	}
	
	public CatchBlockInitialiser withBInit(IBlockInitialiser bInit) {
		this.bInit = bInit;
		return this;
	}

	@Override
	public CatchBlockInitialiser newInitialiser() {
		return new CatchBlockInitialiser();
	}

	@Override
	public IBlockInitialiser getBInit() {
		return this.bInit;
	}
}