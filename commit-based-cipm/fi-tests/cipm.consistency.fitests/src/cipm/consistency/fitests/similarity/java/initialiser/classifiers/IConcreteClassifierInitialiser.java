package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementInitialiser;

public interface IConcreteClassifierInitialiser extends
	IAnnotableAndModifiableInitialiser,
	IMemberContainerInitialiser,
	IMemberInitialiser,
	IStatementInitialiser,
	IClassifierInitialiser,
	ITypeParametrizableInitialiser {
	
	public default boolean setPackage(ConcreteClassifier cls, Package pac) {
		if (pac != null) {
			cls.setPackage(pac);
			return cls.getPackage().equals(pac) &&
					pac.getClassifiers().contains(cls) &&
					pac.getClassifiersInSamePackage().contains(cls);
		}
		return true;
	}
}
