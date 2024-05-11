package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;

public class ModuleGenerator extends EObjectGenerator<Module>
	implements INamedElementGenerator {
	private NameGenerator nGen;
	
	public ModuleGenerator() {
		super();
		this.setDefaultNameGen();
	}
	
	@Override
	public void reset() {
		super.reset();
		this.nGen.reset();
	}
	
	@Override
	public IModuleInitialiser getInitialiser() {
		return (IModuleInitialiser) super.getInitialiser();
	}
	
	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new ModuleInitialiser();
	}

	@Override
	public Module generateElement() {
		Module result = super.generateElement();
		this.setName(result);
		return result;
	}
	
	@Override
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}

	@Override
	public void setNameGenerator(NameGenerator nGen) {
		this.nGen = nGen;
	}
}
