package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;

public class CompilationUnitInitialiser implements ICompilationUnitInitialiser {
	@Override
	public CompilationUnit instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createCompilationUnit();
	}

	@Override
	public ICompilationUnitInitialiser newInitialiser() {
		return new CompilationUnitInitialiser();
	}
}
