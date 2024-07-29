package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportInitialiser;

/**
 * An interface that can be implemented by tests, which work with {@link Import}
 * instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Import} instances.
 */
public interface UsesImports extends UsesConcreteClassifiers {
	/**
	 * A variant of
	 * {@link #createMinimalImport(IImportInitialiser, ConcreteClassifier)}, where
	 * the constructed instance is of type {@link ClassifierImport}.
	 * 
	 * @param cls The classifier of the instance to be constructed (what it will
	 *            point at)
	 */
	public default ClassifierImport createMinimalClsImport(ConcreteClassifier cls) {
		return (ClassifierImport) this.createMinimalImport(new ClassifierImportInitialiser(), cls);
	}

	/**
	 * A variant of
	 * {@link #createMinimalImport(IImportInitialiser, ConcreteClassifier)}, where
	 * the constructed instance is of type {@link ClassifierImport}.
	 * 
	 * @param clsName The name of the {@link Class}, to which the constructed
	 *                instance will point at
	 * 
	 * @see {@link #createMinimalClassWithCU(String)}
	 */
	public default ClassifierImport createMinimalClsImport(String clsName) {
		return (ClassifierImport) this.createMinimalImport(new ClassifierImportInitialiser(),
				this.createMinimalClassWithCU(clsName));
	}

	/**
	 * @param initialiser The initialiser that will be used to construct the
	 *                    instance
	 * @param cls         The classifier of the instance to be constructed (what it
	 *                    will point at)
	 * @return An {@link Import} instance with the given parameters
	 */
	public default Import createMinimalImport(IImportInitialiser initialiser, ConcreteClassifier cls) {
		Import result = initialiser.instantiate();
		initialiser.setClassifier(result, cls);

		return result;
	}
}
