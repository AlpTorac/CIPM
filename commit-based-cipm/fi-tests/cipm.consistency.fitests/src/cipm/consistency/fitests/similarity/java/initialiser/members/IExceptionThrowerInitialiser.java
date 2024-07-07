package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.types.NamespaceClassifierReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IExceptionThrowerInitialiser extends ICommentableInitialiser {
    @Override
    public ExceptionThrower instantiate();
    @ModificationMethod
	public default boolean addException(ExceptionThrower extt, NamespaceClassifierReference ncref) {
		if (ncref != null) {
			extt.getExceptions().add(ncref);
			return extt.getExceptions().contains(ncref);
		}
		return true;
	}
	public default boolean addExceptions(ExceptionThrower extt, NamespaceClassifierReference[] ncrefs) {
		return this.addXs(extt, ncrefs, this::addException);
	}
}
