package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnonymousClassInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link AnonymousClass} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link AnonymousClass} instances.
 */
public interface UsesAnonymousClasses extends UsesMethods {
	/**
	 * @return A minimal {@link AnonymousClass} instance.
	 */
	public default AnonymousClass createMinimalAnonymousClass() {
		var acInit = new AnonymousClassInitialiser();
		var ac = acInit.instantiate();
		return ac;
	}

	/**
	 * @param methodName The name of the {@link ClassMethod} that will be added to
	 *                   the constructed instance
	 * @return An {@link AnonymousClass} instance with a {@link ClassMethod} with
	 *         the given name.
	 * 
	 * @see {@link #createMinimalClsMethodWithNullReturn(String)}
	 */
	public default AnonymousClass createMinimalAnonymousClassWithMethod(String methodName) {
		var acInit = new AnonymousClassInitialiser();
		var ac = this.createMinimalAnonymousClass();
		acInit.addMember(ac, this.createMinimalClsMethodWithNullReturn(methodName));
		return ac;
	}
}
