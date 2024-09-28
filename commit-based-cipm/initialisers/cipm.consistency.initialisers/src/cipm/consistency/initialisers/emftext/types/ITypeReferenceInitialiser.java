package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.emftext.arrays.IArrayTypeableInitialiser;

public interface ITypeReferenceInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeReference instantiate();

	/**
	 * Since {@link TypeReference} itself does not have the "target" attribute and
	 * its implementors' relations to that attribute vary, this method is
	 * implemented as a template. <br>
	 * <br>
	 * Therefore, <b>what this method does can change immensely between different
	 * implementors</b>. Check the concrete implementations of the methods below
	 * within implementors for further details:
	 * 
	 * @see {@link #canSetTarget(TypeReference)}
	 * @see {@link #canSetTargetTo(TypeReference, Classifier)}
	 * @see {@link #setTargetAssertion(TypeReference, Classifier)}
	 */
	public default boolean setTarget(TypeReference tref, Classifier target) {
		if (!this.canSetTargetTo(tref, target)) {
			return false;
		}

		tref.setTarget(target);
		return this.setTargetAssertion(tref, target);
	}

	/**
	 * @return Whether calling {@link #setTarget(TypeReference, Classifier)} with
	 *         the given parameters results in the expected behaviour.
	 */
	public default boolean setTargetAssertion(TypeReference tref, Classifier target) {
		return tref.getTarget().equals(target);
	}

	/**
	 * @return Whether {@code tref.setTarget(...)} method can be used to modify
	 *         tref.
	 */
	public boolean canSetTarget(TypeReference tref);

	/**
	 * Expects {@link #canSetTarget(TypeReference)} to return true for tref, since
	 * otherwise its "target" attribute cannot be changed in the first place.
	 * 
	 * @return Whether {@code tref.setTarget(target)} method can be used in the
	 *         context of the given target parameter.
	 */
	public default boolean canSetTargetTo(TypeReference tref, Classifier target) {
		return this.canSetTarget(tref) && target != null;
	}
}