package cipm.consistency.fitests.similarity.java.action.declaration;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.action.IDevAction;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;

public abstract class DeclareConcreteClassifierAction implements IDevAction {
	private final CompilationUnit targetCU;
	
	private Package targetPac;
	private String name;

	public DeclareConcreteClassifierAction(CompilationUnit targetCU) {
		this.targetCU = targetCU;
	}
	
	public DeclareConcreteClassifierAction withName(String name) {
		this.name = name;
		return this;
	}
	
	public DeclareConcreteClassifierAction withTargetPackage(Package targetPac) {
		this.targetPac = targetPac;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Package getTargetPackage() {
		return this.targetPac;
	}
	
	public CompilationUnit getTargetCU() {
		return this.targetCU;
	}
	
	protected boolean addToTargetCU(ConcreteClassifier cls, CompilationUnit cu) {
		return cu != null && new CompilationUnitInitialiser().addClassifier(cu, cls);
	}
}
