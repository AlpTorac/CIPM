package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

public class CompilationUnitInitialiser implements ICompilationUnitInitialiser {
	@Override
	public CompilationUnit instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createCompilationUnit();
	}
}
