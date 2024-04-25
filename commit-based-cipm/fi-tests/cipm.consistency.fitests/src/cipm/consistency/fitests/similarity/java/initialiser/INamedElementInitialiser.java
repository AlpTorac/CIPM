package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.commons.NamedElement;

public interface INamedElementInitialiser extends ICommentableInitialiser {
	public default String getDefaultName() {
		return "";
	}
	
	@Override
	public NamedElement instantiate();
	
	/**
	 * Uses {@link #getDefaultName()} to generate a valid
	 * name.
	 */
	@Override
	public default NamedElement minimalInstantiation() {
		var result = this.instantiate();
		this.initialiseName(result, this.getDefaultName());
		return result;
	}
	
	public default void initialiseName(NamedElement ne, String name) {
		if (name != null) {
			ne.setName(name);
			assert ne.getName().equals(name);
		}
	}
}
