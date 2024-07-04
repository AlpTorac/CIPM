package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Method;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IMethodInitialiser extends IAnnotableAndModifiableInitialiser,
	IExceptionThrowerInitialiser,
	IMemberInitialiser,
	IReferenceableElementInitialiser,
	IParametrizableInitialiser,
	IStatementContainerInitialiser,
	ITypedElementInitialiser,
	ITypeParametrizableInitialiser {
	@Override
	public Method instantiate();
}
