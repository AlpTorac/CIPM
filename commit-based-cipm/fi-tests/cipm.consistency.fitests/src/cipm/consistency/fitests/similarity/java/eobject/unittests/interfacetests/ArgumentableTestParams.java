package cipm.consistency.fitests.similarity.java.eobject.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.references.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.params.EObjectInitialiserParameters;

public class ArgumentableTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new EObjectInitialiserParameters().getAllInitialisersBySuper(IArgumentableInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}

}
