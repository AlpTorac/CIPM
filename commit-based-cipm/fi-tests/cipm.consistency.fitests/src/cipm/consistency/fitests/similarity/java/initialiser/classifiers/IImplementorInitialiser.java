package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IImplementorInitialiser extends ICommentableInitialiser {
	@Override
	public Implementor instantiate();

	public default boolean addImplements(Implementor implementor, TypeReference impls) {
		if (impls != null) {
			implementor.getImplements().add(impls);
			return implementor.getImplements().contains(impls);
		}
		return true;
	}

	public default boolean addImplements(Implementor implementor, TypeReference[] implsArr) {
		return this.doMultipleModifications(implementor, implsArr, this::addImplements);
	}
}
