package cipm.consistency.fitests.similarity.jamopp.gens;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class GeneratorOutput {
	private EObject lhs;
	private EObject rhs;
	private EStructuralFeature[] changedAttrs;

	public GeneratorOutput(EObject lhs, EObject rhs, EStructuralFeature...changedAttrs) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.changedAttrs = changedAttrs;
	}

	public EObject getLhs() {
		return lhs;
	}
	public EObject getRhs() {
		return rhs;
	}
	public EStructuralFeature[] getChangedAttrs() {
		return changedAttrs;
	}
}
