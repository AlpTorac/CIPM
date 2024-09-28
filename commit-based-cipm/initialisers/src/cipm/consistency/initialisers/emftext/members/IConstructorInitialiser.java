package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.initialisers.emftext.generics.ITypeParametrizableInitialiser;
import cipm.consistency.initialisers.emftext.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.emftext.parameters.IParametrizableInitialiser;
import cipm.consistency.initialisers.emftext.statements.IBlockContainerInitialiser;
import cipm.consistency.initialisers.emftext.statements.IStatementListContainerInitialiser;

public interface IConstructorInitialiser extends IAnnotableAndModifiableInitialiser, IBlockContainerInitialiser,
		IExceptionThrowerInitialiser, IMemberInitialiser, IParametrizableInitialiser,
		IStatementListContainerInitialiser, ITypeParametrizableInitialiser {

	@Override
	public Constructor instantiate();

	@Override
	public default boolean canContainStatements(StatementListContainer slc) {
		return ((Constructor) slc).getBlock() != null;
	}
}
