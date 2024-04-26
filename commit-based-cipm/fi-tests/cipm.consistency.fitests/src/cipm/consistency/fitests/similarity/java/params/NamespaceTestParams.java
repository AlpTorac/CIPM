package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.impl.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.PackageInitialiser;

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
		
		return Stream.of(
					Arguments.of(new PackageInitialiser()),
					Arguments.of(new CompilationUnitInitialiser())
				);
	}
}