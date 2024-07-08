package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public class ConcreteClassifierInitialiserAdapter implements
	IInitialiserAdapterStrategy {
	private ICompilationUnitInitialiser cuInit;
	
	public ConcreteClassifierInitialiserAdapter(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
	}
	
	public ICompilationUnitInitialiser getCUInit() {
		return this.cuInit;
	}
	
	public ConcreteClassifierInitialiserAdapter withCUInit(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
		return this;
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedO = (ConcreteClassifier) obj;
		
		if (castedO.getContainingCompilationUnit() == null) {
			var cuInit = this.getCUInit();
			
			CompilationUnit unit = cuInit.instantiate();
			return cuInit.initialise(unit) &&
					cuInit.addClassifier(unit, castedO);
		}
		
		return true;
	}

	@Override
	public ConcreteClassifierInitialiserAdapter newStrategy() {
		return new ConcreteClassifierInitialiserAdapter(
				(ICompilationUnitInitialiser) this.getCUInit().newInitialiser());
	}
}
