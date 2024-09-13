package cipm.consistency.fitests.similarity.java.eobject.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.params.EObjectInitialiserParameters;

public class TypeParametrizableTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return new EObjectInitialiserParameters().getAllInitialisersBySuper(ITypeParametrizableInitialiser.class).stream()
				.map((i) -> Arguments.of(i));
	}

}
