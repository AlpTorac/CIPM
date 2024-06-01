package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImplementorInitialiser;

public interface IEnumerationInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
	public default void addConstant(Enumeration enm, EnumConstant cst) {
		if (cst != null) {
			enm.getConstants().add(cst);
			assert enm.getConstants().contains(cst);
			assert enm.getContainedConstant(cst.getName()).equals(cst);
		}
	}
	
	public default void addConstants(Enumeration enm, EnumConstant[] csts) {
		this.addXs(enm, csts, this::addConstant);
	}
}
