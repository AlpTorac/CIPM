package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IJavaRootInitialiser;

public class JavaRootTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		var util = new ParameterUtil();
		
//		var initialiserArgs = util.toArgumentCol(new InitialiserParameters()
//				.getInitialisersBySuper(IJavaRootInitialiser.class));
//		
//		var originArgs = util.toArgumentCol(Stream.of(Origin.values()));
//		
//		var initXOrigin = util.permute(initialiserArgs, originArgs);
		
		return util.toArgumentStream(new InitialiserParameters()
				.getInitialisersBySuper(IJavaRootInitialiser.class)
				.stream());
	}
}
