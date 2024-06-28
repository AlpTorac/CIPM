package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Origin;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;

/*
 * Package names (package.getName()) are supposed to always be empty. The names are saved in namespace.
 */
public interface IPackageInitialiser extends IJavaRootInitialiser, IReferenceableElementInitialiser {
	public default void initialiseModuleField(Package pac, Module mod) {
		if (mod != null) {
			pac.setModule(mod);
			assert pac.getModule().equals(mod);
		}
	}
	
	public default void addClassifier(Package pac, ConcreteClassifier cc) {
		if (cc != null) {
			pac.getClassifiers().add(cc);
			assert pac.getClassifiers().contains(cc);
		}
	}
	
	public default void addClassifiers(Package pac, ConcreteClassifier[] ccs) {
		this.addXs(pac, ccs, this::addClassifier);
	}
}