package cipm.consistency.fitests.similarity.eobject.initialiser.java.members;

import org.emftext.language.java.members.Method;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IMethodInitialiser extends IAnnotableAndModifiableInitialiser, IExceptionThrowerInitialiser,
		IMemberInitialiser, IReferenceableElementInitialiser, IParametrizableInitialiser,
		IStatementContainerInitialiser, ITypedElementInitialiser, ITypeParametrizableInitialiser {
	@Override
	public Method instantiate();
}
