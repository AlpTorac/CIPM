package cipm.consistency.fitests.similarity.java.initialiser.initadapters;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

/**
 * An {@link IInitialiserAdapterStrategy} implementation that can be used with
 * {@link IInitialiserBase} implementors that instantiate
 * {@link ConcreteClassifier}. <br>
 * <br>
 * Adds the {@link ConcreteClassifier} instance to a {@link CompilationUnit}.
 * Does not modify the {@link ConcreteClassifier} instance, if it already is
 * contained in a {@link CompilationUnit}. This way, similarity checking 2
 * {@link ConcreteClassifier} instances does not throw exceptions, due to them
 * not having a container.
 * 
 * @author atora
 */
public class ConcreteClassifierInitialiserAdapter implements IInitialiserAdapterStrategy {
	/**
	 * The initialiser responsible for creating {@link CompilationUnit}s to fulfil
	 * this instance's functionality.
	 */
	private ICompilationUnitInitialiser cuInit;

	/**
	 * Constructs an instance with the given {@link ICompilationUnitInitialiser}.
	 */
	public ConcreteClassifierInitialiserAdapter(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
	}

	/**
	 * @return The initialiser responsible for creating {@link CompilationUnit}s.
	 */
	public ICompilationUnitInitialiser getCUInit() {
		return this.cuInit;
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedO = (ConcreteClassifier) obj;

		if (castedO.getContainingCompilationUnit() == null) {
			var cuInit = this.getCUInit();

			CompilationUnit unit = cuInit.instantiate();
			return cuInit.initialise(unit) && cuInit.addClassifier(unit, castedO);
		}

		return true;
	}

	@Override
	public ConcreteClassifierInitialiserAdapter newStrategy() {
		return new ConcreteClassifierInitialiserAdapter(
				(ICompilationUnitInitialiser) this.getCUInit().newInitialiser());
	}
}
