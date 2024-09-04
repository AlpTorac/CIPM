package cipm.consistency.fitests.similarity.java.initialiser.commons;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class CommonsInitialiserPackage implements IInitialiserPackage {
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol(new Class[] { ICommentableInitialiser.class, INamedElementInitialiser.class,
				INamespaceAwareElementInitialiser.class });
	}
}
