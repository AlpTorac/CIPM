package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.emftext.params.EMFTextInitialiserParameters;
import cipm.consistency.initialisers.emftext.imports.IStaticImportInitialiser;

public class StaticImportTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new EMFTextInitialiserParameters().getAllInitialisersBySuper(IStaticImportInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}
}
