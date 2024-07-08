package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Constructor;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementListContainerInitialiser;

public interface IConstructorInitialiser extends IAnnotableAndModifiableInitialiser,
	IBlockContainerInitialiser,
	IExceptionThrowerInitialiser,
	IMemberInitialiser,
	IParametrizableInitialiser,
	IStatementListContainerInitialiser,
	ITypeParametrizableInitialiser {
	
	@Override
	public Constructor instantiate();
}
