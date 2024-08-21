package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.references.IElementReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

public class ElementReferenceTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new InitialiserParameters().getAllInitialisersBySuper(IElementReferenceInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}

}