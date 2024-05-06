package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public class ConcreteClassifierTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new InitialiserParameters()
				.getInitialisersBySuper(IConcreteClassifierInitialiser.class)
				.stream()
				.map((i) -> Arguments.of(i));
	}

}