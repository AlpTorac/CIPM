package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.AnnotationClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;

public class NameTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// PackageInitialiser is excluded, as its name (.getName()) is NOT supposed to be fiddled with
		
		return Stream.of(
				Arguments.of(new ModuleInitialiser()),
				Arguments.of(new CompilationUnitInitialiser()),
				Arguments.of(new ClassInitialiser()),
				Arguments.of(new EnumerationInitialiser()),
				Arguments.of(new InterfaceInitialiser()),
				Arguments.of(new AnnotationClassifierInitialiser())
			);
	}
}
