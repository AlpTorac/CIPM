package cipm.consistency.fitests.similarity.java.generators;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public class CompilationUnitGenerator extends EObjectGenerator<CompilationUnit>
	implements INamedElementGenerator {
	
	private NameGenerator nGen;
	
	public CompilationUnitGenerator() {
		super();
		this.setDefaultNameGen();
	}
	
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

	public NameGenerator getNameGenerator() {
		return this.nGen;
	}
	
	@Override
	public void setNameGenerator(NameGenerator nGen) {
		this.nGen = nGen;
	}
	
	@Override
	public CompilationUnit generateElement() {
		CompilationUnit result = super.generateElement();
		this.setName(result);
		return result;
	}
}
