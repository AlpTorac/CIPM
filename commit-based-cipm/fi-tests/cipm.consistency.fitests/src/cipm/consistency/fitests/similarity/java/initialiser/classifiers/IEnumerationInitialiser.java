package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.members.EnumConstant;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IEnumerationInitialiser extends IConcreteClassifierInitialiser, IImplementorInitialiser {
    @Override
    public Enumeration instantiate();
    
    @ModificationMethod
	public default boolean addConstant(Enumeration enm, EnumConstant cst) {
		if (cst != null) {
			enm.getConstants().add(cst);
			return enm.getConstants().contains(cst) &&
					enm.getContainedConstant(cst.getName()).equals(cst);
		}
		return true;
	}
	
	public default boolean addConstants(Enumeration enm, EnumConstant[] csts) {
		return this.addXs(enm, csts, this::addConstant);
	}
}
