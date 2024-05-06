package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Constructor;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IExceptionThrowerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IStatementListContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeParametrizableInitialiser;

public interface IConstructorInitialiser extends IAnnotableAndModifiableInitialiser,
	IBlockContainerInitialiser,
	IExceptionThrowerInitialiser,
	IMemberInitialiser,
	IParametrizableInitialiser,
	IStatementListContainerInitialiser,
	ITypeParametrizableInitialiser {
	
	@Override
	public default void minimalInitialisation(EObject obj) {
		IMemberInitialiser.super.minimalInitialisation(obj);
	}
}
