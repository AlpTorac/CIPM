package cipm.consistency.initialisers.emftext.classifiers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.Package;

import cipm.consistency.initialisers.emftext.generics.ITypeParametrizableInitialiser;
import cipm.consistency.initialisers.emftext.members.IMemberContainerInitialiser;
import cipm.consistency.initialisers.emftext.members.IMemberInitialiser;
import cipm.consistency.initialisers.emftext.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.emftext.statements.IStatementInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link ConcreteClassifier} instances. <br>
 * <br>
 * <b>Note: {@link #setPackage(ConcreteClassifier, Package)} does not add the
 * given {@link ConcreteClassifier} to the given {@link Package}.</b>
 * 
 * @author atora
 *
 */
public interface IConcreteClassifierInitialiser extends IAnnotableAndModifiableInitialiser, IMemberContainerInitialiser,
		IMemberInitialiser, IStatementInitialiser, IClassifierInitialiser, ITypeParametrizableInitialiser {

	@Override
	public ConcreteClassifier instantiate();

	/**
	 * Sets the package of cls as pac. <br>
	 * <br>
	 * <b>Note: DOES NOT modify the classifiers contained by pac.</b>
	 * 
	 * @see {@link IConcreteClassifierInitialiser}
	 */
	public default boolean setPackage(ConcreteClassifier cls, Package pac) {
		if (pac != null) {
			cls.setPackage(pac);
			return cls.getPackage().equals(pac);
		}
		return true;
	}
}
