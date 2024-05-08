package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;

public class ModuleGenerator extends EObjectGenerator<Module>
	implements INamedElementGenerator {
	private NameGenerator nGen = new NameGenerator() {{
		setNamePrefix("mod");
	}};
	
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
	public NameGenerator getNameGenerator() {
		return this.nGen;
	}
}
