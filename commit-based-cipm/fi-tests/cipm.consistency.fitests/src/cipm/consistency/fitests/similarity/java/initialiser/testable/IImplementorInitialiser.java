package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IImplementorInitialiser extends ICommentableInitialiser {
	public default void addImplements(Implementor implementor, TypeReference tref) {
		if (tref != null) {
			implementor.getImplements().add(tref);
			assert implementor.getImplements().contains(tref);
		}
	}
}
