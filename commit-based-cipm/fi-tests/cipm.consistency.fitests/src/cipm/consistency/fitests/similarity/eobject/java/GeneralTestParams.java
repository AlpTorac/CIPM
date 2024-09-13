package cipm.consistency.fitests.similarity.eobject.java;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.eobject.java.params.EObjectJavaInitialiserParameters;

public class GeneralTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// No need for adapted versions of initialisers, since init.initialise(...) is
		// not called in these tests
		return new EObjectJavaInitialiserParameters().getAllNonAdaptedInitialisers().stream().map((i) -> Arguments.of(i));
	}
}
