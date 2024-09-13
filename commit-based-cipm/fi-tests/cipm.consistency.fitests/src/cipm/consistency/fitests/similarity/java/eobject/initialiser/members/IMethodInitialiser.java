package cipm.consistency.fitests.similarity.java.eobject.initialiser.members;

import org.emftext.language.java.members.Method;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.parameters.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.statements.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypedElementInitialiser;

public interface IMethodInitialiser extends IAnnotableAndModifiableInitialiser, IExceptionThrowerInitialiser,
		IMemberInitialiser, IReferenceableElementInitialiser, IParametrizableInitialiser,
		IStatementContainerInitialiser, ITypedElementInitialiser, ITypeParametrizableInitialiser {
	@Override
	public Method instantiate();
}
