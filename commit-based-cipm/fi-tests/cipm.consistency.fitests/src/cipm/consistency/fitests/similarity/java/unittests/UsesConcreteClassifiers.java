package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Class;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link ConcreteClassifier} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link ConcreteClassifier}
 * instances.
 */
public interface UsesConcreteClassifiers extends UsesPackages, UsesNames {
	/**
	 * A variant of
	 * {@link #createMinimalClassifier(IConcreteClassifierInitialiser, String)},
	 * where a {@link Class} instance with the given parameter is created.
	 * 
	 * @param name The name of the instance to be constructed
	 */
	public default Class createMinimalClass(String name) {
		return (Class) this.createMinimalClassifier(new ClassInitialiser(), name);
	}

	/**
	 * A variant of
	 * {@link #createMinimalClassifierWithCU(IConcreteClassifierInitialiser, String)},
	 * where the constructed instance is of type {@link Class} with the given
	 * parameter.
	 * 
	 * @param name The name of the instance to be constructed
	 */
	public default Class createMinimalClassWithCU(String name) {
		return (Class) this.createMinimalClassifierWithCU(new ClassInitialiser(), name);
	}

	/**
	 * A variant of
	 * {@link #createMinimalClassifierWithPac(IConcreteClassifierInitialiser, String, String[])},
	 * where the constructed instance is of type {@link Class}.
	 * 
	 * @param name The name of the instance to be constructed
	 * @param nss  The namespaces of the {@link Package} that will contain the
	 *             constructed instance
	 */
	public default Class createMinimalClassWithPac(String name, String[] nss) {
		return (Class) this.createMinimalClassifierWithPac(new ClassInitialiser(), name, nss);
	}

	/**
	 * @param init The initialiser that will be used to create the instance
	 * @param name The name of the instance to be constructed
	 * @return A {@link ConcreteClassifier} instance with the given name. The
	 *         concrete type of the instance depends on init.
	 */
	public default ConcreteClassifier createMinimalClassifier(IConcreteClassifierInitialiser init, String name) {
		ConcreteClassifier result = init.instantiate();
		init.setName(result, name);
		return result;
	}

	/**
	 * A variant of
	 * {@link #createMinimalClassifierWithCU(IConcreteClassifierInitialiser, String, String)},
	 * where the name of the constructed {@link CompilationUnit} is
	 * {@link #getDefaultName()}.
	 * 
	 * @param init The initialiser that will be used to create the instance
	 * @param name The name of the instance to be constructed
	 */
	public default ConcreteClassifier createMinimalClassifierWithCU(IConcreteClassifierInitialiser init, String name) {
		return this.createMinimalClassifierWithCU(init, name, this.getDefaultName());
	}

	/**
	 * @param init    The initialiser that will be used to create the instance
	 * @param clsName The name of the instance to be constructed
	 * @param cuName  The name of the {@link CompilationUnit} that will contain the
	 *                created instance
	 * @return A {@link ConcreteClassifier} instance that is contained by a
	 *         {@link CompilationUnit}. The given parameters are used during both of
	 *         their construction.
	 */
	public default ConcreteClassifier createMinimalClassifierWithCU(IConcreteClassifierInitialiser init, String clsName,
			String cuName) {
		ConcreteClassifier result = init.instantiate();
		init.setName(result, clsName);

		var cuInit = new CompilationUnitInitialiser();
		var cu = cuInit.instantiate();
		cuInit.setName(cu, cuName);
		cuInit.addClassifier(cu, result);

		return result;
	}

	/**
	 * @param init    The initialiser that will be used to create the instance
	 * @param clsName The name of the instance to be constructed
	 * @param pacNss  The namespaces of the {@link Package} that will contain the
	 *                constructed instance
	 * @return A {@link ConcreteClassifier} instance that will be contained by a
	 *         {@link Package}. The given parameters are used in both of their
	 *         construction.
	 * 
	 * @see {@link #createMinimalPackage(String[])}
	 */
	public default ConcreteClassifier createMinimalClassifierWithPac(IConcreteClassifierInitialiser init,
			String clsName, String[] pacNss) {
		ConcreteClassifier result = init.instantiate();
		init.setName(result, clsName);
		init.setPackage(result, this.createMinimalPackage(pacNss));
		return result;
	}
}