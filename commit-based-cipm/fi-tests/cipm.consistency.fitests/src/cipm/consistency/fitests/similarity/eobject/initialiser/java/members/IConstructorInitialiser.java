package cipm.consistency.fitests.similarity.eobject.initialiser.java.members;

import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.IStatementListContainerInitialiser;

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
