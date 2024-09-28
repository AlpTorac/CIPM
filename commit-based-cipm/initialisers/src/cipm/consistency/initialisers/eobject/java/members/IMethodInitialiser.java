package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.members.Method;

import cipm.consistency.initialisers.eobject.java.generics.ITypeParametrizableInitialiser;
import cipm.consistency.initialisers.eobject.java.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.eobject.java.parameters.IParametrizableInitialiser;
import cipm.consistency.initialisers.eobject.java.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.eobject.java.statements.IStatementContainerInitialiser;
import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IMethodInitialiser extends IAnnotableAndModifiableInitialiser, IExceptionThrowerInitialiser,
		IMemberInitialiser, IReferenceableElementInitialiser, IParametrizableInitialiser,
		IStatementContainerInitialiser, ITypedElementInitialiser, ITypeParametrizableInitialiser {
	@Override
	public Method instantiate();
}
