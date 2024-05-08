package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public class CompilationUnitGenerator extends EObjectGenerator<CompilationUnit>
	implements INamedElementGenerator {
	
	private NameGenerator nGen = new NameGenerator();
	
	@Override
	public ICompilationUnitInitialiser getInitialiser() {
		return (ICompilationUnitInitialiser) super.getInitialiser();
	}
	
	@Override
	public void reset() {
		super.reset();
		this.nGen.reset();
	}
	
	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new CompilationUnitInitialiser();
	}

	@Override
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}
}
