package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.ImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

public class ImportTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new InitialiserParameters()
				.getInitialisersBySuper(IImportInitialiser.class)
				.stream()
				.map((i) -> ((ImportInitialiser) i).withClsInit(new ClassInitialiser()))
				.map((i) -> Arguments.of(i));
	}

}
