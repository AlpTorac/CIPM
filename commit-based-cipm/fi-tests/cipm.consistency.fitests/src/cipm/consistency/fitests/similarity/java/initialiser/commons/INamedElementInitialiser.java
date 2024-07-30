package cipm.consistency.fitests.similarity.java.initialiser.commons;

import org.emftext.language.java.commons.NamedElement;

public interface INamedElementInitialiser extends ICommentableInitialiser {
	@Override
	public NamedElement instantiate();

	public default boolean setName(NamedElement ne, String name) {
		if (name != null) {
			ne.setName(name);
			return ne.getName().equals(name);
		}
		return true;
	}
}
