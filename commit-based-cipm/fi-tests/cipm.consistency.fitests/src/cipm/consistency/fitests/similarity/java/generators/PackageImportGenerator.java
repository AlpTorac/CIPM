package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IPackageImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;

public class PackageImportGenerator extends EObjectGenerator<PackageImport>
	implements INamespaceAwareElementGenerator {
	private NamespaceGenerator nsGen;
	
	public PackageImportGenerator() {
		super();
		this.setDefaultNamespaceGenerator();
	}
	
	@Override
	public IPackageImportInitialiser getInitialiser() {
		return (IPackageImportInitialiser) super.getInitialiser();
	}
	
	@Override
	protected EObjectInitialiser getDefaultInitialiser() {
		return new PackageImportInitialiser();
	}

	@Override
	public PackageImport createElement() {
		PackageImport imp = super.createElement();
		this.setNamespace(imp);
		return imp;
	}

	@Override
	public NamespaceGenerator getNamespaceGenerator() {
		return this.nsGen;
	}

	@Override
	public void setNamespaceGenerator(NamespaceGenerator nsGen) {
		this.nsGen = nsGen;
	}
}
