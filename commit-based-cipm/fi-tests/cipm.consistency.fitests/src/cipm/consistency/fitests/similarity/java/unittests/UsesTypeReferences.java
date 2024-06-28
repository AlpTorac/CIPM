package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.types.ClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.INamespaceClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypeReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.NamespaceClassifierReferenceInitialiser;

public interface UsesTypeReferences extends UsesConcreteClassifiers {
	public default ClassifierReference createMinimalClsRef(String clsName) {
		return (ClassifierReference) this.createMinimalTypeReference(new ClassifierReferenceInitialiser(),
				this.createMinimalClassWithCU(clsName));
	}
	
	public default TypeReference createMinimalClsRef(ITypeReferenceInitialiser init, String clsName) {
		return this.createMinimalTypeReference(init,
				this.createMinimalClassWithCU(clsName));
	}
	
	public default TypeReference createMinimalTypeReference(ITypeReferenceInitialiser init, Classifier cls) {
		TypeReference result = init.instantiate();
		init.minimalInitialisation(result);
		init.setTarget(result, cls);
		return result;
	}
	
	public default NamespaceClassifierReference createMinimalCNR(String clsName) {
		var init = new NamespaceClassifierReferenceInitialiser();
		NamespaceClassifierReference result = init.instantiate();
		init.minimalInitialisation(result);
		init.addClassifierReference(result, this.createMinimalClsRef(clsName));
		return result;
	}
}
