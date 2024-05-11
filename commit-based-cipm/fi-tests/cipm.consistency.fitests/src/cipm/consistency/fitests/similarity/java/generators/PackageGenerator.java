package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;

public class PackageGenerator extends EObjectGenerator<Package>
	implements INamespaceAwareElementGenerator {
	
	private NamespaceGenerator nsGen;
	
	public PackageGenerator() {
		super();
		this.setDefaultNamespaceGenerator();
	}
	
	@Override
	public void reset() {
		super.reset();
		this.nsGen.reset();
	}
	
	@Override
	public IPackageInitialiser getInitialiser() {
		return (IPackageInitialiser) super.getInitialiser();
	}
	
	@Override
	protected IPackageInitialiser getDefaultInitialiser() {
		return new PackageInitialiser();
	}

	@Override
	public NamespaceGenerator getNamespaceGenerator() {
		return this.nsGen;
	}

	@Override
	public void setNamespaceGenerator(NamespaceGenerator nsGen) {
		this.nsGen = nsGen;
	}
	
	@Override
	public Package generateElement() {
		Package result = super.generateElement();
		this.setNamespace(result);
		return result;
	}
}
