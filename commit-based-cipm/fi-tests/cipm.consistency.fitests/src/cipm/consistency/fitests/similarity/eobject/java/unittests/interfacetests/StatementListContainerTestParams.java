package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.initialisers.eobject.java.statements.IStatementListContainerInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.params.EObjectJavaInitialiserParameters;

public class StatementListContainerTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new EObjectJavaInitialiserParameters().getAllInitialisersBySuper(IStatementListContainerInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}
}
