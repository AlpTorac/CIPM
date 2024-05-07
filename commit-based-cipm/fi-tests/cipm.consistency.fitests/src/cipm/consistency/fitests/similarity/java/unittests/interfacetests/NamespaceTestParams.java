package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

public class NamespaceTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// ModuleInitialiser is excluded, as namespaces are NOT used while comparing modules
		
		/*
		 * TODO: Create and add initialisers
		 
			 	AnnotationInstance
				ClassifierImport
				StaticMemberImport
				ModuleReference
				AccessProvidingModuleDirective
				NamespaceClassifierReference
		 */
		
		return new InitialiserParameters()
				.getInitialisersBySuper(INamespaceAwareElementInitialiser.class)
				.stream()
				.filter((i) -> !i.getClass().equals(ModuleInitialiser.class))
				.map((i) -> Arguments.of(i));
	}
}
