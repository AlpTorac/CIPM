package cipm.consistency.fitests.similarity.java.action.declaration;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.action.EMFTextUtil;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;

/**
 * Encapsulates the action of declaring a new package. <br>
 * <br>
 * The declared package has none of its attributes set.
 * 
 * @author atora
 */
public class DeclarePackageAction extends DeclareJavaRootAction {
	private IPackageInitialiser pacInit;
	private Module containingMod;

	private Package createdPac;

	public DeclarePackageAction(Resource targetRes) {
		super(targetRes);
	}

	public DeclarePackageAction withInitialiser(IPackageInitialiser pacInit) {
		this.pacInit = pacInit;
		return this;
	}

	public DeclarePackageAction withContainingModule(Module containingMod) {
		this.containingMod = containingMod;
		return this;
	}

	/**
	 * @return The {@link IPackageInitialiser} that will be used during the
	 *         construction of the {@link Package} instance.
	 */
	public IPackageInitialiser getInitialiser() {
		if (this.pacInit == null) {
			this.pacInit = new PackageInitialiser();
		}
		return this.pacInit;
	}

	public Package getCreatedPackage() {
		return this.createdPac;
	}

	public Module getContainingModule() {
		return this.containingMod;
	}

	@Override
	public boolean apply() {
		var target = this.getTargetResource();
		if (target != null) {
			var init = this.getInitialiser();

			var pac = init.instantiate();

			var result = init.addNamespaces(pac, this.getNamespaces());
			result = result && init.setOrigin(pac, this.getOrigin());
			result = result && init.setModule(pac, this.getContainingModule());

			result = result && EMFTextUtil.getInstance().addContentToResource(target, pac);

			if (result) {
				this.createdPac = pac;
				return true;
			}
		}

		return false;
	}
}
