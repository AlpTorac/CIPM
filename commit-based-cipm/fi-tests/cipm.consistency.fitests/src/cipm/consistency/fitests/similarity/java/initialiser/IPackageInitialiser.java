package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;

/*
 * Package names (package.getName()) are supposed to always be empty. The names are saved in namespace.
 */
public interface IPackageInitialiser extends IJavaRootInitialiser, IReferenceableElementInitialiser {
	@Override
	public Package instantiate();
	
	@Override
	public default Package clone(EObject obj) {
		return (Package) IJavaRootInitialiser.super.clone(obj);
	}
	
	public default void initialiseModuleField(Package pac, Module mod) {
		if (mod != null) {
			pac.setModule(mod);
			assert pac.getModule().equals(mod);
		}
	}
	
	/**
	 * Package names are NOT to be compared. Packages' names consist
	 * of namespaces and those are used instead of the actual name field
	 * {@code package.getName()}.
	 */
	@Override
	public default void initialiseName(NamedElement ne, String name) {
		
	}
	
	public default void addClassifier(Package pac, ConcreteClassifier cc) {
		if (cc != null) {
			pac.getClassifiers().add(cc);
			assert pac.getClassifiers().contains(cc);
		}
	}
}