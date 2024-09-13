package cipm.consistency.fitests.similarity.eobject.initialiser.java.containers;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class CompilationUnitInitialiser extends AbstractInitialiserBase implements ICompilationUnitInitialiser {
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
