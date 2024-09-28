package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.members.Method;

import cipm.consistency.initialisers.emftext.generics.ITypeParametrizableInitialiser;
import cipm.consistency.initialisers.emftext.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.emftext.parameters.IParametrizableInitialiser;
import cipm.consistency.initialisers.emftext.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.emftext.statements.IStatementContainerInitialiser;
import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IMethodInitialiser extends IAnnotableAndModifiableInitialiser, IExceptionThrowerInitialiser,
		IMemberInitialiser, IReferenceableElementInitialiser, IParametrizableInitialiser,
		IStatementContainerInitialiser, ITypedElementInitialiser, ITypeParametrizableInitialiser {
	@Override
	public Method instantiate();
}
