package cipm.consistency.fitests.similarity.java.action.declaration;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IClassInitialiser;

public class DeclareClassAction extends DeclareConcreteClassifierAction {
	private IClassInitialiser init;
	private Class createdClass;

	public DeclareClassAction(CompilationUnit targetCU) {
		super(targetCU);
	}

	public DeclareClassAction withInitialiser(IClassInitialiser clsInit) {
		this.init = clsInit;
		return this;
	}

	public IClassInitialiser getInitialiser() {
		if (this.init == null) {
			this.init = new ClassInitialiser();
		}
		return this.init;
	}

	public Class getCreatedClass() {
		return this.createdClass;
	}

	@Override
	public boolean apply() {
		var target = this.getTargetCU();

		if (target != null) {
			var init = this.getInitialiser();

			var cls = init.instantiate();

			var result = init.setPackage(cls, this.getTargetPackage()) && init.setName(cls, this.getName());

			result = result && this.addToTargetCU(cls, target);

			if (result) {
				this.createdClass = cls;
				return true;
			}
		}

		return false;
	}
}
