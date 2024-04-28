package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ICompilationUnitInitialiser;

public class CompilationUnitInitialiser implements ICompilationUnitInitialiser, IInitialiser<CompilationUnit> {
	@Override
	public CompilationUnit instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createCompilationUnit();
	}
}
