package cipm.consistency.fitests.similarity.java.action.declaration;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.java.action.EMFTextUtil;
import cipm.consistency.fitests.similarity.java.action.IDevAction;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;

public class DeclarePackageAction implements IDevAction<Resource> {
	private IPackageInitialiser pacInit;
	
	public DeclarePackageAction withPackageInitialiser(IPackageInitialiser pacInit) {
		this.pacInit = pacInit;
		return this;
	}

	@Override
	public boolean apply(Resource obj) {
		if (this.canBePerformed(obj)) {
			if (this.pacInit == null) {
				this.pacInit = new PackageInitialiser();
			}
			
			var pac = this.pacInit.instantiate();
			
			EMFTextUtil.getInstance().addContentToResource(obj, pac);
			return EMFTextUtil.getInstance().hasDirectContent(obj, pac);
		} else {
			return false;
		}
	}

	@Override
	public DeclarePackageAction newActionInstance() {
		return new DeclarePackageAction();
	}

	@Override
	public DeclarePackageAction cloneAction() {
		var clone = new DeclarePackageAction();
		
		if (this.pacInit != null) {
			clone.withPackageInitialiser((IPackageInitialiser) this.pacInit.newInitialiser());
		}

		return clone;
	}

}
