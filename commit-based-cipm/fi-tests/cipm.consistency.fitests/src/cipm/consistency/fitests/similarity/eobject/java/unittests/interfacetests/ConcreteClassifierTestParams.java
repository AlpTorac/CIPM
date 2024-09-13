package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.params.EObjectInitialiserParameters;

public class ConcreteClassifierTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new EObjectInitialiserParameters().getAllInitialisersBySuper(IConcreteClassifierInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}

}
