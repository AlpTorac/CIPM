package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;

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
