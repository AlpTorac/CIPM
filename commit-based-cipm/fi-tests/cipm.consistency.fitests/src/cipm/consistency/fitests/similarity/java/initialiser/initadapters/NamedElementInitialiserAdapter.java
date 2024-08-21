package cipm.consistency.fitests.similarity.java.initialiser.initadapters;

import org.emftext.language.java.commons.NamedElement;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;

/**
 * An {@link IInitialiserAdapterStrategy} implementation that can be used with
 * {@link IInitialiserBase} implementors that instantiate {@link NamedElement}.
 * <br>
 * <br>
 * Sets the name of the created {@link NamedElement} to a default name, if it
 * has no name. This way, similarity checking 2 {@link NamedElement} instances
 * does not throw exceptions, due to them not having a name.
 * 
 * @author atora
 *
 */
public class NamedElementInitialiserAdapter implements IInitialiserAdapterStrategy {
	/**
	 * @return The default name, to which the name of the created
	 *         {@link NamedElement} instances will be set.
	 */
	public String getDefaultName() {
		return "";
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedInit = (INamedElementInitialiser) init;
		var castedO = (NamedElement) obj;

		if (castedInit.canSetName(castedO) && castedO.getName() == null) {
			return castedInit.setName(castedO, this.getDefaultName());
		}

		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new NamedElementInitialiserAdapter();
	}
}
