package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.types.TypeReference;

public interface IInterfaceInitialiser extends IConcreteClassifierInitialiser {
    @Override
    public Interface instantiate();
	public default boolean addDefaultExtends(Interface intfc, TypeReference tref) {
		if (tref != null) {
			intfc.getDefaultExtends().add(tref);
			return intfc.getDefaultExtends().contains(tref);
		}
		return true;
	}
	
	public default boolean addDefaultExtends(Interface intfc, TypeReference[] trefs) {
		return this.addXs(intfc, trefs, this::addDefaultExtends);
	}
	public default boolean addExtends(Interface intfc, TypeReference tref) {
		if (tref != null) {
			intfc.getExtends().add(tref);
			return intfc.getExtends().contains(tref);
		}
		return true;
	}
	
	public default boolean addExtends(Interface intfc, TypeReference[] trefs) {
		return this.addXs(intfc, trefs, this::addExtends);
	}
}
