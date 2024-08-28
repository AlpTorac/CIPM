package cipm.consistency.fitests.similarity.java.action.declaration;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modifiers.Open;

import cipm.consistency.fitests.similarity.java.action.EMFTextUtil;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;

public class DeclareModuleAction extends DeclareJavaRootAction {
	private IModuleInitialiser modInit;

	private Module createdMod;

	public DeclareModuleAction(Resource targetRes) {
		super(targetRes);
	}

	public DeclareModuleAction withInitialiser(IModuleInitialiser modInit) {
		this.modInit = modInit;
		return this;
	}

	public IModuleInitialiser getInitialiser() {
		if (this.modInit == null) {
			this.modInit = new ModuleInitialiser();
		}
		return this.modInit;
	}

	public Module getCreatedModule() {
		return this.createdMod;
	}

	@Override
	public boolean apply() {
		var target = this.getTargetResource();
		if (target != null) {
			var init = this.getInitialiser();

			var mod = init.instantiate();

			var result = init.setName(mod, this.getName()) && init.setOrigin(mod, this.getOrigin());

			result = result && EMFTextUtil.getInstance().addContentToResource(target, mod);

			if (result) {
				this.createdMod = mod;
				return true;
			}
		}

		return false;
	}
}
