package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Class;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;

public interface UsesConcreteClassifiers extends UsesPackages {
	public default Class createMinimalClass(String name) {
		return (Class) this.createMinimalClassifier(new ClassInitialiser(), name);
	}
	
	public default Class createMinimalClassWithCU(String name) {
		return (Class) this.createMinimalClassifierWithCU(new ClassInitialiser(), name);
	}
	
	public default Class createMinimalClassWithPac(String name, String[] nss) {
		return (Class) this.createMinimalClassifierWithPac(new ClassInitialiser(), name, nss);
	}
	
	public default ConcreteClassifier createMinimalClassifier(IConcreteClassifierInitialiser init, String name) {
		ConcreteClassifier result = init.instantiate();
		init.setName(result, name);
		return result;
	}
	
	public default ConcreteClassifier createMinimalClassifierWithCU(IConcreteClassifierInitialiser init, String name) {
		ConcreteClassifier result = init.instantiate();
		init.setName(result, name);
		
		var cuInit = new CompilationUnitInitialiser();
		var cu = cuInit.instantiate();
		cuInit.initialise(cu);
		cuInit.addClassifier(cu, result);
		
		return result;
	}
	
	public default ConcreteClassifier createMinimalClassifierWithPac(IConcreteClassifierInitialiser init, String clsName, String[] pacNss) {
		ConcreteClassifier result = init.instantiate();
		init.setName(result, clsName);
		init.setPackage(result, this.createMinimalPackage(pacNss));
		return result;
	}
}