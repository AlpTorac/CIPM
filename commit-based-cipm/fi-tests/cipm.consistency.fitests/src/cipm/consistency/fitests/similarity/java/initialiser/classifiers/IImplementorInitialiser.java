package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IImplementorInitialiser extends ICommentableInitialiser {
	public default void addImplements(Implementor implementor, TypeReference tref) {
		if (tref != null) {
			implementor.getImplements().add(tref);
			assert implementor.getImplements().contains(tref);
		}
	}
	
	public default void addImplements(Implementor implementor, TypeReference[] trefs) {
		this.addXs(implementor, trefs, this::addImplements);
	}
}
