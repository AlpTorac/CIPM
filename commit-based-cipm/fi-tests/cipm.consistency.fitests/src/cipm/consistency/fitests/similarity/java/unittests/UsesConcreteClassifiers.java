package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Class;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public interface UsesConcreteClassifiers {
	public default Class createMinimalClass(String name) {
		return (Class) this.createMinimalClassifier(new ClassInitialiser(), name);
	}
	
	public default Class createMinimalClassWithCU(String name) {
		return (Class) this.createMinimalClassifierWithCU(new ClassInitialiser(), name);
	}
	
	public default ConcreteClassifier createMinimalClassifier(IConcreteClassifierInitialiser init, String name) {
		ConcreteClassifier result = init.instantiate();
		init.minimalInitialisation(result);
		init.initialiseName(result, name);
		return result;
	}
	
	public default ConcreteClassifier createMinimalClassifierWithCU(IConcreteClassifierInitialiser init, String name) {
		ConcreteClassifier result = init.instantiate();
		init.minimalInitialisationWithContainer(result);
		init.initialiseName(result, name);
		return result;
	}
}