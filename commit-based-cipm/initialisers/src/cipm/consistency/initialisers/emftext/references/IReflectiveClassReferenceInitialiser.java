package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.ReflectiveClassReference;

public interface IReflectiveClassReferenceInitialiser extends IReferenceInitialiser {
	@Override
	public ReflectiveClassReference instantiate();

}
