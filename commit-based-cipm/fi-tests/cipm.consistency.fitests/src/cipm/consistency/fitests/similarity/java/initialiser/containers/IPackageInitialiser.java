package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;

/*
 * Package names (package.getName()) are supposed to always be empty. The names are saved in namespace.
 */
public interface IPackageInitialiser extends IJavaRootInitialiser, IReferenceableElementInitialiser {
	public default boolean initialiseModuleField(Package pac, Module mod) {
		if (mod != null) {
			pac.setModule(mod);
			return pac.getModule().equals(mod);
		}
		return false;
	}
	
	public default boolean addClassifier(Package pac, ConcreteClassifier cc) {
		if (cc != null) {
			pac.getClassifiers().add(cc);
			return pac.getClassifiers().contains(cc);
		}
		return false;
	}
	
	public default boolean addClassifiers(Package pac, ConcreteClassifier[] ccs) {
		return this.addXs(pac, ccs, this::addClassifier);
	}
}