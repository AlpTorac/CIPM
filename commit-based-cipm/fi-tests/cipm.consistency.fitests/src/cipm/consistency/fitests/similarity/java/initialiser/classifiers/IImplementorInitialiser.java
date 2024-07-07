package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IImplementorInitialiser extends ICommentableInitialiser {
    @Override
    public Implementor instantiate();
    
    @ModificationMethod
	public default boolean addImplements(Implementor implementor, TypeReference tref) {
		if (tref != null) {
			implementor.getImplements().add(tref);
			return implementor.getImplements().contains(tref);
		}
		return true;
	}
	
	public default boolean addImplements(Implementor implementor, TypeReference[] trefs) {
		return this.addXs(implementor, trefs, this::addImplements);
	}
}
