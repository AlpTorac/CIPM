package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.impl.AdditionalLocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.EnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.LocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.VariableLengthParameterInitialiser;

public class NameTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// PackageInitialiser is excluded, as its name (.getName()) is NOT supposed to be fiddled with
		// Block is excluded, since its name also is NOT used while comparing
		
		/*
		 * ConcreteClassifier +
		 * TypeParameter +
		 * Method +
		 * Constructor +
		 * EnumConstant +
		 * Member +
		 * Parameter +
		 * TODO: Add JumpLabel
		 * Variable +
		 * AdditionalLocalVariable +
		 */
		
		return Stream.of(
				
				// Containers (except Package)
				Arguments.of(new ModuleInitialiser()),
				Arguments.of(new CompilationUnitInitialiser()),
				
				// ConcreteClassifier
				Arguments.of(new AnnotationInitialiser()),
				Arguments.of(new ClassInitialiser()),
				Arguments.of(new EnumerationInitialiser()),
				Arguments.of(new InterfaceInitialiser()),
				
				// TypeParameter
				Arguments.of(new TypeParameterInitialiser()),
				
				// Member (except Block)
				Arguments.of(new ConstructorInitialiser()),
				Arguments.of(new FieldInitialiser()),
				Arguments.of(new ClassMethodInitialiser()),
				Arguments.of(new InterfaceMethodInitialiser()),
				
				// Parameter
				Arguments.of(new OrdinaryParameterInitialiser()),
				Arguments.of(new CatchParameterInitialiser()),
				Arguments.of(new ReceiverParameterInitialiser()),
				Arguments.of(new VariableLengthParameterInitialiser()),
				
				// EnumConstant
				Arguments.of(new EnumConstantInitialiser()),
				
				// Variable
				Arguments.of(new LocalVariableInitialiser()),
				
				// AdditionalLocalVariable
				Arguments.of(new AdditionalLocalVariableInitialiser())
			);
	}
}
