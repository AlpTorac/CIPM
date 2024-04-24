package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;

public class CompilationUnitInitialiser implements ICompilationUnitInitialiser {
	private boolean setDefaultName = true;
	
	@Override
	public boolean isSetDefaultName() {
		return this.setDefaultName;
	}

	@Override
	public CompilationUnit minimalInstantiation() {
		return (CompilationUnit) ICompilationUnitInitialiser.super.minimalInstantiation();
	}
	
	@Override
	public CompilationUnit instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createCompilationUnit();
	}

	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {
		this.setDefaultName = setDefaultName;
	}
}
