package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IExceptionThrowerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypedElementInitialiser;

public interface IMethodInitialiser extends IAnnotableAndModifiableInitialiser,
	IExceptionThrowerInitialiser,
	IMemberInitialiser,
	IReferenceableElementInitialiser,
	IParametrizableInitialiser,
	IStatementContainerInitialiser,
	ITypedElementInitialiser,
	ITypeParametrizableInitialiser {
}
