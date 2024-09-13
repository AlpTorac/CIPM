package cipm.consistency.fitests.similarity.java.eobject.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

public class NameTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new InitialiserParameters().getAllInitialisersBySuper(INamedElementInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}
}
