package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.PlusPlus;

public interface IPlusPlusInitialiser extends IUnaryModificationOperatorInitialiser {
	@Override
	public PlusPlus instantiate();

}
