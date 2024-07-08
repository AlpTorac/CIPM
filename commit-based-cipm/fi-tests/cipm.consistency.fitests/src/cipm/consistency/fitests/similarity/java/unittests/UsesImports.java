package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportInitialiser;

public interface UsesImports extends UsesConcreteClassifiers {
	public default ClassifierImport createMinimalClsImport(ConcreteClassifier cls) {
		return (ClassifierImport) this.createMinimalImport(new ClassifierImportInitialiser(), cls);
	}
	public default ClassifierImport createMinimalClsImport(String clsName) {
		return (ClassifierImport) this.createMinimalImport(new ClassifierImportInitialiser(),
				this.createMinimalClassWithCU(clsName));
	}
	
	public default Import createMinimalImport(IImportInitialiser initialiser, ConcreteClassifier cls) {
		Import result = initialiser.instantiate();
		initialiser.setClassifier(result, cls);
		
		return result;
	}
}
