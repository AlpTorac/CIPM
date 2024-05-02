package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Constructor;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IExceptionThrowerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IStatementListContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ITypeParametrizableInitialiser;

public interface IConstructorInitialiser extends IAnnotableAndModifiableInitialiser,
	IBlockContainerInitialiser,
	IExceptionThrowerInitialiser,
	IMemberInitialiser,
	IParametrizableInitialiser,
	IStatementListContainerInitialiser,
	ITypeParametrizableInitialiser {
}
