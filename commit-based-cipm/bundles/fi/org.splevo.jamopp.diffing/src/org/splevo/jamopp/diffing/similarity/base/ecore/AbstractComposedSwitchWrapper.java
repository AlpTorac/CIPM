package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;

//TODO: Consider renaming to "AbstractComposedSwitchAdapter"
//TODO: Re-use ComposedSwitch#ComposedSwitch(Collection)

/**
 * An abstract class that complements {@link IComposedSwitchWrapper} with an
 * attribute to contain the compare element mentioned there. It also provides
 * implementations for the methods from {@link IComposedSwitchWrapper}.
 * 
 * @author atora
 */
public abstract class AbstractComposedSwitchWrapper extends ComposedSwitch<Boolean> implements IComposedSwitchWrapper {
	/** The object to compare the switched element with. */
	private EObject compareElement = null;

	/**
	 * Constructs an instance using {@code super()}
	 * 
	 * @see {@link ComposedSwitch#ComposedSwitch()}
	 */
	public AbstractComposedSwitchWrapper() {

	}

	/**
	 * Constructs an instance with the given switches. <br>
	 * <br>
	 * Meant to be used while testing.
	 */
	protected AbstractComposedSwitchWrapper(Collection<Switch<Boolean>> switches) {
		if (switches != null) {
			switches.forEach((s) -> this.addSwitch(s));
		}
	}

	/**
	 * Constructs an instance with the given switches. <br>
	 * <br>
	 * Meant to be used while testing.
	 */
	protected AbstractComposedSwitchWrapper(Switch<Boolean>[] switches) {
		if (switches != null) {
			for (var s : switches) {
				this.addSwitch(s);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@link #compareElement}
	 */
	@Override
	public EObject getCompareElement() {
		return this.compareElement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean compare(EObject eo1, EObject eo2) {
		this.compareElement = eo2;
		return this.doSwitch(eo1);
	}

	/**
	 * The default case for not explicitly handled elements always returns null to
	 * identify the open decision.
	 * 
	 * @param object The object to compare with the compare element.
	 * @return null
	 */
	@Override
	public Boolean defaultCase(EObject object) {
		return null;
	}
}
