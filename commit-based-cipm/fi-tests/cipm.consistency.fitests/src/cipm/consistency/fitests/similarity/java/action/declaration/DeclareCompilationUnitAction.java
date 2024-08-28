package cipm.consistency.fitests.similarity.java.action.declaration;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.action.EMFTextUtil;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public class DeclareCompilationUnitAction extends DeclareJavaRootAction {
	private ICompilationUnitInitialiser cuInit;
	private CompilationUnit createdCU;

	public DeclareCompilationUnitAction(Resource targetRes) {
		super(targetRes);
	}

	public DeclareCompilationUnitAction withInitialiser(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
		return this;
	}

	public ICompilationUnitInitialiser getInitialiser() {
		if (this.cuInit == null) {
			this.cuInit = new CompilationUnitInitialiser();
		}
		return this.cuInit;
	}

	public CompilationUnit getCreatedCU() {
		return this.createdCU;
	}

	@Override
	public boolean apply() {
		var target = this.getTargetResource();
		if (target != null) {
			var init = this.getInitialiser();

			var cu = init.instantiate();

			var result = init.addNamespaces(cu, this.getNamespaces()) && init.setOrigin(cu, this.getOrigin())
					&& init.setName(cu, this.getName());

			result = result && EMFTextUtil.getInstance().addContentToResource(target, cu);

			if (result) {
				this.createdCU = cu;
				return true;
			}
		}

		return false;
	}
}
