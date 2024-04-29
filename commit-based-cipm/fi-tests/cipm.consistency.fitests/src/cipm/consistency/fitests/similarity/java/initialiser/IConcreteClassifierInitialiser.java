package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.types.ClassifierReference;

public interface IConcreteClassifierInitialiser extends
	IAnnotableAndModifiableInitialiser,
	IMemberContainerInitialiser,
	IMemberInitialiser,
	IStatementInitialiser,
	IClassifierInitialiser,
	ITypeParametrizableInitialiser {
	
	public default void addInnerClassifier(ConcreteClassifier cls, ConcreteClassifier toBeAddedToCls) {
		if (toBeAddedToCls != null) {
			cls.getAllInnerClassifiers().add(toBeAddedToCls);
			assert cls.getInnerClassifiers().contains(toBeAddedToCls);
			assert cls.getAllInnerClassifiers().contains(toBeAddedToCls);
			assert cls.getContainedClassifier(toBeAddedToCls.getName()).equals(toBeAddedToCls);
		}
	}
	
	public default void setPackage(ConcreteClassifier cls, Package pac) {
		if (pac != null) {
			cls.setPackage(pac);
			assert cls.getPackage().equals(pac);
		}
	}
	
	public default void addSuperTypeReference(ConcreteClassifier cls, ClassifierReference ref) {
		if (ref != null) {
			cls.getSuperTypeReferences().add(ref);
			assert cls.getSuperTypeReferences().contains(ref);
			assert cls.getContainingContainerName().stream().anyMatch((n) -> ref.getContainingContainerName().contains(n));
		}
	}
}
