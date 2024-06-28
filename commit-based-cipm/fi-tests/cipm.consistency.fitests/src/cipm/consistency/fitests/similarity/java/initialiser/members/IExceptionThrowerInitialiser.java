package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.types.NamespaceClassifierReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IExceptionThrowerInitialiser extends ICommentableInitialiser {
	public default void addException(ExceptionThrower extt, NamespaceClassifierReference ncref) {
		if (ncref != null) {
			extt.getExceptions().add(ncref);
			assert extt.getExceptions().contains(ncref);
		}
	}
	public default void addExceptions(ExceptionThrower extt, NamespaceClassifierReference[] ncrefs) {
		this.addXs(extt, ncrefs, this::addException);
	}
}
