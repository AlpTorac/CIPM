package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

public class NameTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new InitialiserParameters()
				.getInitialisersBySuper(INamedElementInitialiser.class)
				.stream()
				
				// PackageInitialiser is excluded, as its name (.getName()) is NOT supposed to be fiddled with
				// Block is excluded, since its name also is NOT used while comparing
				.filter((i) -> !i.getClass().equals(PackageInitialiser.class)
						&& !i.getClass().equals(BlockInitialiser.class))
				.map((i) -> Arguments.of(i));
	}
}