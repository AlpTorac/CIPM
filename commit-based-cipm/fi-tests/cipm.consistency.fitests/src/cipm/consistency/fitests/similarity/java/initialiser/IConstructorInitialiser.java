package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.Constructor;

public interface IConstructorInitialiser extends IAnnotableAndModifiableInitialiser,
	IBlockContainerInitialiser,
	IExceptionThrowerInitialiser,
	IMemberInitialiser,
	IParametrizableInitialiser,
	IStatementListContainerInitialiser,
	ITypeParametrizableInitialiser {

	@Override
	public Constructor instantiate();
	
	@Override
	public default Constructor minimalInstantiation() {
		return (Constructor) IMemberInitialiser.super.minimalInstantiation();
	}
}
