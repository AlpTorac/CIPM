package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

public class PackageInitialiser extends AbstractInitialiser implements IPackageInitialiser {
	private boolean setDefaultName = true;
	
	@Override
	public Package getCurrentObject() {
		return (Package) super.getCurrentObject();
	}
	
	@Override
	public boolean isSetDefaultName() {
		return this.setDefaultName;
	}

	@Override
	public void instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		var pac = fac.createPackage();
		this.setCurrentObject(pac);
	}

	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {
		this.setDefaultName = setDefaultName;
	}
}
