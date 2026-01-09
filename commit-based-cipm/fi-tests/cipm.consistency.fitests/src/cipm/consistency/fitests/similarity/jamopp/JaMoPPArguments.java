package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.junit.jupiter.params.provider.Arguments;

import cipm.consistency.fitests.similarity.eobject.EObjectCreator;
import cipm.consistency.fitests.similarity.eobject.EObjectInstantiator;
import cipm.consistency.fitests.similarity.params.IInitialiserParameterAdaptationStrategy;
import cipm.consistency.fitests.similarity.params.IInitialiserParameters;

public final class JaMoPPArguments {
	private static final EObjectCreator creator = new FluentJaMoPPEObjectCreator();
	private static final List<EClass> supportedEClasses = creator.getAllSupportedTypes();
	@SuppressWarnings("unchecked")
	private static final List<Class<? extends EObject>> supportedClasses = supportedEClasses.stream()
			.map((eCls) -> (Class<? extends EObject>) eCls.getInstanceClass()).collect(Collectors.toList());

	private static List<EObjectInstantiator> adaptInitialisers(List<EObjectInstantiator> inits) {
		// TODO Apply adaptation
		return inits;
	}

	public static IInitialiserParameterAdaptationStrategy getAdaptationStrategy();

	private static <T extends Commentable> List<EObjectInstantiator> getNonAdaptedInitialisersBySuper(
			Class<T> superCls) {
		return supportedClasses.stream().filter((cls) -> superCls.isAssignableFrom(cls))
				.map((cls) -> new EObjectInstantiator(creator, cls)).collect(Collectors.toList());
	}

	private static <T extends Commentable> List<EObjectInstantiator> getAdaptedInitialisersBySuper(Class<T> superCls) {
		return adaptInitialisers(getNonAdaptedInitialisersBySuper(superCls));
	}

	private static <T extends Commentable> List<EObjectInstantiator> getAllInitialisersBySuper(Class<T> superCls) {
		var result = new ArrayList<EObjectInstantiator>();
		result.addAll(getNonAdaptedInitialisersBySuper(superCls));
		result.addAll(getAdaptedInitialisersBySuper(superCls));
		return result;
	}

	private static <T extends Commentable> List<EObjectInstantiator> getEachInitialiserOnceBySuper(Class<T> superCls) {
		return adaptInitialisers(getNonAdaptedInitialisersBySuper(superCls));
	}

	/**
	 * @see {@link IInitialiserParameters#getEachInitialiserOnceBySuper(Class)}
	 */
	public static <T extends Commentable> List<EObjectInstantiator> getEachInitialiserOnceFor(Class<T> superType) {
		return getEachInitialiserOnceBySuper(superType);
	}

	/**
	 * @return Arguments containing pairs in form of (desired initialiser, display
	 *         name for initialiser)
	 * @see {@link IInitialiserParameters#getEachInitialiserOnceBySuper(Class)}
	 */
	public static Stream<Arguments> getEachInitialiserArgumentsOnceFor(Class<? extends Commentable> superType) {
		return getEachInitialiserOnceFor(superType).stream()
				.map((i) -> Arguments.of(i, generateDisplayNameForInit(i.getTypeToInstantiate())));
	}

	/**
	 * @see {@link IInitialiserParameters#getAllInitialisersBySuper(Class)}
	 */
	public static <T extends Commentable> List<EObjectInstantiator> getAllInitialisersFor(Class<T> superType) {
		return getAllInitialisersBySuper(superType);
	}

	/**
	 * @return Arguments containing pairs in form of (desired initialiser, display
	 *         name for initialiser)
	 * @see {@link IInitialiserParameters#getAllInitialisersBySuper(Class)}
	 */
	public static Stream<Arguments> getAllInitialiserArgumentsFor(Class<? extends Commentable> superType) {
		return getAllInitialisersFor(superType).stream()
				.map((i) -> Arguments.of(i, generateDisplayNameForInit(i.getTypeToInstantiate())));
	}

	/**
	 * @see {@link IInitialiserParameters#getAdaptedInitialisersBySuper(Class)}
	 */
	public static <T extends Commentable> List<EObjectInstantiator> getAdaptedInitialisersFor(Class<T> superType) {
		return getAdaptedInitialisersBySuper(superType);
	}

	/**
	 * @return Arguments containing pairs in form of (desired initialiser, display
	 *         name for initialiser)
	 * @see {@link IInitialiserParameters#getAdaptedInitialisersBySuper(Class)}
	 */
	public static <T extends Commentable> Stream<Arguments> getAdaptedInitialiserArgumentsFor(Class<T> superType) {
		return getAdaptedInitialisersFor(superType).stream()
				.map((i) -> Arguments.of(i, generateDisplayNameForInit(i.getTypeToInstantiate())));
	}

	/**
	 * @see {@link IInitialiserParameters#getAdaptedInitialisersBySuper(Class)}
	 */
	public static <T extends Commentable> List<EObjectInstantiator> getNonAdaptedInitialisersFor(Class<T> superType) {
		return getNonAdaptedInitialisersBySuper(superType);
	}

	/**
	 * @return Arguments containing pairs in form of (desired initialiser, display
	 *         name for initialiser)
	 * @see {@link IInitialiserParameters#getNonAdaptedInitialisersBySuper(Class)}
	 */
	public static Stream<Arguments> getNonAdaptedInitialiserArgumentsFor(Class<? extends Commentable> superType) {
		return getNonAdaptedInitialisersFor(superType).stream()
				.map((i) -> Arguments.of(i, generateDisplayNameForInit(i.getTypeToInstantiate())));
	}

	/**
	 * Used by the static get...InitialiserArgumentsFor methods within this class.
	 * 
	 * @return A display name associated with the given initialiser, which can be
	 *         used by parameterised tests.
	 */
	public static String generateDisplayNameForInit(Class<?> cls) {
		return cls.getSimpleName();
	}
}
