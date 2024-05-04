package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface INamedElementInitialiser extends ICommentableInitialiser {
	public default String getDefaultName() {
		return "";
	}
	
	/**
	 * Uses {@link #getDefaultName()} to generate a valid
	 * name.
	 */
	@Override
	public default void minimalInitialisation(EObject obj) {
		NamedElement castedO = (NamedElement) obj;
		
		if (castedO.getName() == null) {
			this.initialiseName(castedO, this.getDefaultName());
		}
	}
	
	public default void initialiseName(NamedElement ne, String name) {
		if (name != null) {
			ne.setName(name);
			assert ne.getName().equals(name);
		}
	}
}
