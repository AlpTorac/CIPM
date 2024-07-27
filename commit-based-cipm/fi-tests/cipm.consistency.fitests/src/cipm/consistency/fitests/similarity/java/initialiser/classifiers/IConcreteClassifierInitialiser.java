package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementInitialiser;

public interface IConcreteClassifierInitialiser extends
	IAnnotableAndModifiableInitialiser,
	IMemberContainerInitialiser,
	IMemberInitialiser,
	IStatementInitialiser,
	IClassifierInitialiser,
	ITypeParametrizableInitialiser {
	
	@Override
	public ConcreteClassifier instantiate();
	
	/**
	 * Sets the package of cls as pac.
	 * <br><br>
	 * <b>Note: DOES NOT modify the classifiers contained by pac.</b>
	 */
	public default boolean setPackage(ConcreteClassifier cls, Package pac) {
		if (pac != null) {
			cls.setPackage(pac);
			return cls.getPackage().equals(pac);
		}
		return true;
	}
}
