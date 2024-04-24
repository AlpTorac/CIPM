package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.PackageInitialiser;

public class NamespaceTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// ModuleInitialiser is excluded, as namespaces are NOT used while comparing modules
		
		return Stream.of(
					Arguments.of(new PackageInitialiser()),
					Arguments.of(new CompilationUnitInitialiser())
				);
	}
}
