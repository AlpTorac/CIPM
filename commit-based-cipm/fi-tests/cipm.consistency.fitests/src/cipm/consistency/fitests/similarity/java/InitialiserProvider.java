package cipm.consistency.fitests.similarity.java;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.PackageInitialiser;

public class InitialiserProvider implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return Stream.of(
				Arguments.of(new ModuleInitialiser()),
				Arguments.of(new PackageInitialiser()),
				Arguments.of(new CompilationUnitInitialiser())
			);
	}
}
