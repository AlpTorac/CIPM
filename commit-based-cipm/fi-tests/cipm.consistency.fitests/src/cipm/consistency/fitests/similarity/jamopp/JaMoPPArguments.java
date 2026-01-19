package cipm.consistency.fitests.similarity.jamopp;

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

	public static List<Class<?>> getAllConcreteClassesBySuper(Class<?> superType) {
		return supportedClasses.stream().filter((cls) -> superType.isAssignableFrom(cls)).collect(Collectors.toList());
	}

	public static Stream<Arguments> getAllConcreteClassesAsArgs() {
		return getAllConcreteClasses().stream().map(Arguments::of);
	}

	public static Stream<Arguments> getAllConcreteClassesBySuperAsArgs(Class<?> superType) {
		return getAllConcreteClassesBySuper(superType).stream().map(Arguments::of);
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
