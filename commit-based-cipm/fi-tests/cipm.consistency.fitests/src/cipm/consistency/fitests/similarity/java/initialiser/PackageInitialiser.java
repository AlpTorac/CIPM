package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Package;

public class PackageInitialiser implements IPackageInitialiser {
	private boolean setDefaultName = true;
	
	@Override
	public boolean isSetDefaultName() {
		return this.setDefaultName;
	}

	@Override
	public Package instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createPackage();
	}

	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {
		this.setDefaultName = setDefaultName;
	}
}
