package cipm.consistency.fitests.similarity.java.eobject.initialiser.commons;

import org.emftext.language.java.commons.NamedElement;

public interface INamedElementInitialiser extends ICommentableInitialiser {
	@Override
	public NamedElement instantiate();

	public default boolean setName(NamedElement ne, String name) {
		if (!this.canSetName(ne)) {
			return false;
		}
		if (name != null) {
			ne.setName(name);
			return ne.getName().equals(name);
		}
		return true;
	}

	/**
	 * Extracted from {@link #setName(NamedElement, String)} because there are
	 * implementors, whose name cannot be modified via
	 * {@link #setName(NamedElement, String)}. This way, such implementors can
	 * override this method to indicate that they were not modified.
	 * 
	 * @return Whether the name of the given {@link NamedElement} was modified.
	 */
	public default boolean canSetName(NamedElement ne) {
		return true;
	}
}
