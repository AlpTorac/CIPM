package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IConcreteClassifierInitialiser extends
	IAnnotableAndModifiableInitialiser,
	IMemberContainerInitialiser,
	IMemberInitialiser,
	IStatementInitialiser,
	IClassifierInitialiser,
	ITypeParametrizableInitialiser {
	
	public default void setPackage(ConcreteClassifier cls, Package pac) {
		if (pac != null) {
			cls.setPackage(pac);
			assert cls.getPackage().equals(pac);
		}
	}
}
