package cipm.consistency.fitests.similarity.jamopp;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.api.FluentEObjectAPI;

public final class JaMoPPArguments {
	private static final FluentEObjectAPI api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
	private static final List<Class<?>> supportedClasses = api.getAllSupportedClasses();

	public static List<Class<?>> getAllConcreteClasses() {
		return List.copyOf(supportedClasses);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<Class<? extends T>> getAllConcreteClassesBySuper(Class<T> superType) {
		return supportedClasses.stream().filter((cls) -> superType.isAssignableFrom(cls))
				.map((cls) -> (Class<? extends T>) cls).collect(Collectors.toList());
	}

	public static Stream<Arguments> getAllConcreteClassesAsArgs() {
		return toArgStream(getAllConcreteClasses());
	}

	public static Stream<Arguments> getAllConcreteClassesBySuperAsArgs(Class<?> superType) {
		return toArgStream(getAllConcreteClassesBySuper(superType));
	}

	private static <T, Cls extends Class<? extends T>, C extends Collection<Cls>> Stream<Arguments> toArgStream(C col) {
		return col.stream().map((c) -> Arguments.of(c, generateDisplayNameForInit(c)));
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
