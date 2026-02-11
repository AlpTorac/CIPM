package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EOperation;

public interface FluentAPIMethodOverload {
	/*
	 * TODO Replace this hierarchy with metamodel-specific post-processing for the
	 * generated Fluent API model (use FluentAPIGenerationContext to locate
	 * elements):
	 * 
	 * 1) Enables adding metamodel specific helper methods systematically
	 * 
	 * 2) Makes overloading method origins clearer
	 * 
	 * 3) Keeps the core Fluent API generation code cleaner
	 * 
	 * TODO Change FluentAPIGlobalMethodParameterOverload into a
	 * metamodel-independent post-processor
	 * 
	 * TODO Add api.newTImport(ReferenceableElement), since namespaces and other
	 * attributes should be derived from the referenced element and the referenced
	 * element must always be there
	 */

	public List<EOperation> createOverloadingMethodsFor(EOperation opToOverload);
}
