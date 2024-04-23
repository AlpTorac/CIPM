package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.types.TypeReference;

public interface IImplementorInitialiser extends ICommentableInitialiser {
	@Override
	public Implementor instantiate();
	
	public default void addImplements(Implementor implementor, TypeReference tref) {
		if (tref != null) {
			implementor.getImplements().add(tref);
			assert implementor.getImplements().contains(tref);
		}
	}
}
