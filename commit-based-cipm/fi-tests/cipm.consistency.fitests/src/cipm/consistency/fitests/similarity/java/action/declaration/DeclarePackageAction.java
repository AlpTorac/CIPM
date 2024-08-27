package cipm.consistency.fitests.similarity.java.action.declaration;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.action.EMFTextUtil;
import cipm.consistency.fitests.similarity.java.action.IDevAction;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;

/**
 * Encapsulates the action of declaring a new package. <br>
 * <br>
 * The declared package has none of its attributes set.
 * 
 * @author atora
 */
public class DeclarePackageAction implements IDevAction {
	private final Resource targetRes;

	private IPackageInitialiser pacInit;
	
	private Package createdPac;

	public DeclarePackageAction(Resource targetRes) {
		this.targetRes = targetRes;
	}

	public DeclarePackageAction withPackageInitialiser(IPackageInitialiser pacInit) {
		this.pacInit = pacInit;
		return this;
	}

	/**
	 * @return The {@link Resource} instance, to which a {@link Package} instance
	 *         will be added.
	 */
	public Resource getTargetResource() {
		return this.targetRes;
	}

	/**
	 * @return The {@link IPackageInitialiser} that will be used during the
	 *         construction of the {@link Package} instance.
	 */
	public IPackageInitialiser getPackageInitialiser() {
		if (this.pacInit == null) {
			this.pacInit = new PackageInitialiser();
		}
		return this.pacInit;
	}

	public Package getCreatedPackage() {
		return this.createdPac;
	}
	
	@Override
	public boolean apply() {
		var target = this.getTargetResource();
		if (target != null) {
			var init = this.getPackageInitialiser();
			
			var pac = init.instantiate();

			var isAdded = EMFTextUtil.getInstance().addContentToResource(target, pac);
			
			if (isAdded) {
				this.createdPac = pac;
				return true;
			}
		}
		
		return false;
	}
}
