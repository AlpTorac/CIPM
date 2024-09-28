package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.initialisers.eobject.java.generics.ITypeParametrizableInitialiser;
import cipm.consistency.initialisers.eobject.java.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.eobject.java.parameters.IParametrizableInitialiser;
import cipm.consistency.initialisers.eobject.java.statements.IBlockContainerInitialiser;
import cipm.consistency.initialisers.eobject.java.statements.IStatementListContainerInitialiser;

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
