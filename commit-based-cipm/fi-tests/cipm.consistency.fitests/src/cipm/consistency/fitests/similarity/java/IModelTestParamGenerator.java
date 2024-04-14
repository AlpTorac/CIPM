package cipm.consistency.fitests.similarity.java;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

public interface IModelTestParamGenerator {
	public Collection<? extends EObject> generateParam();
	public Collection<? extends EObject> generateDefaultParam();
}