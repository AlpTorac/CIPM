package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;

/**
 * An interface meant to be implemented by {@link IInitialiser} implementors
 * that are supposed to create {@link Package} instances. <br>
 * <br>
 * <b>Note: Package names {@code package.getName()} is supposed to be left
 * empty. Changing it HAS NO EFFECT on the name of the package while similarity
 * checking. Package's name consists of the concatenation of its namespaces with
 * dots ".". Therefore, use
 * {@link #addNamespace(org.emftext.language.java.commons.NamespaceAwareElement, String)}
 * to modify its name.</b>
 * 
 * @author atora
 */
public interface IPackageInitialiser extends IJavaRootInitialiser, IReferenceableElementInitialiser {
	@Override
	public Package instantiate();

	public default boolean setModule(Package pac, Module mod) {
		if (mod != null) {
			pac.setModule(mod);
			return pac.getModule().equals(mod);
		}
		return true;
	}

	public default boolean addClassifier(Package pac, ConcreteClassifier cc) {
		if (cc != null) {
			pac.getClassifiers().add(cc);
			return pac.getClassifiers().contains(cc);
		}
		return true;
	}

	public default boolean addClassifiers(Package pac, ConcreteClassifier[] ccs) {
		return this.doMultipleModifications(pac, ccs, this::addClassifier);
	}
}