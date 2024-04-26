package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.members.EnumConstant;

public interface IEnumerationInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
	public default void addConstant(Enumeration enm, EnumConstant cst) {
		if (cst != null) {
			enm.getConstants().add(cst);
			assert enm.getConstants().contains(cst);
			assert enm.getContainedConstant(cst.getName()).equals(cst);
		}
	}
}
