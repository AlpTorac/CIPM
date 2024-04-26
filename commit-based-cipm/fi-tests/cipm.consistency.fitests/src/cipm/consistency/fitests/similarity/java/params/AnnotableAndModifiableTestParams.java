package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.LocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.VariableLengthParameterInitialiser;

public class AnnotableAndModifiableTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		return Stream.of(
					// ConcreteClassifier
					Arguments.of(new AnnotationInitialiser()),
					Arguments.of(new ClassInitialiser()),
					Arguments.of(new EnumerationInitialiser()),
					Arguments.of(new InterfaceInitialiser()),
					
					// Constructor
					Arguments.of(new ConstructorInitialiser()),
					
					// Field
					Arguments.of(new FieldInitialiser()),
					
					// LocalVariable
//					Arguments.of(new LocalVariableInitialiser()),
					
					// Method
					Arguments.of(new ClassMethodInitialiser()),
					Arguments.of(new InterfaceMethodInitialiser()),
					
					// Parameter
					Arguments.of(new OrdinaryParameterInitialiser()),
					Arguments.of(new CatchParameterInitialiser()),
					Arguments.of(new ReceiverParameterInitialiser()),
					Arguments.of(new VariableLengthParameterInitialiser())
				);
	}
}