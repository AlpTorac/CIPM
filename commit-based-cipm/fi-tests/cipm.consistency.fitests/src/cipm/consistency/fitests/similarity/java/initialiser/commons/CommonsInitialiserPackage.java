package cipm.consistency.fitests.similarity.java.initialiser.commons;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class CommonsInitialiserPackage implements IInitialiserPackage {
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				ICommentableInitialiser.class,
				INamedElementInitialiser.class,
				INamespaceAwareElementInitialiser.class
		});
	}
}
