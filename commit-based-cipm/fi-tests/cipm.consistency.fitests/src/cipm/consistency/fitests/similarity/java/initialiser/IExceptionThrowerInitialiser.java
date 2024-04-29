package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.types.NamespaceClassifierReference;

public interface IExceptionThrowerInitialiser extends ICommentableInitialiser {
	public default void addException(ExceptionThrower extt, NamespaceClassifierReference ncref) {
		if (ncref != null) {
			extt.getExceptions().add(ncref);
			assert extt.getExceptions().contains(ncref);
		}
	}
}
