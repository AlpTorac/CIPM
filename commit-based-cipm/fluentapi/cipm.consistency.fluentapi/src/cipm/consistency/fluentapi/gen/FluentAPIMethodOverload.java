package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EOperation;

public interface FluentAPIMethodOverload {
	public List<EOperation> createOverloadingMethodsFor(EOperation opToOverload);
}
