package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public class CompilationUnitContaineeInitialiserAdapter implements
	IInitialiserAdapterStrategy {
	private ICompilationUnitInitialiser cuInit;
	
	public CompilationUnitContaineeInitialiserAdapter(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
	}
	
	public ICompilationUnitInitialiser getCUInit() {
		return this.cuInit;
	}
	
	public CompilationUnitContaineeInitialiserAdapter withCUInit(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
		return this;
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedO = (ConcreteClassifier) obj;
		
		var cuInit = this.getCUInit();
		
		CompilationUnit unit = cuInit.instantiate();
		return cuInit.addClassifier(unit, castedO);
	}

	@Override
	public CompilationUnitContaineeInitialiserAdapter newStrategy() {
		return new CompilationUnitContaineeInitialiserAdapter(
				(ICompilationUnitInitialiser) this.getCUInit().newInitialiser());
	}
}
