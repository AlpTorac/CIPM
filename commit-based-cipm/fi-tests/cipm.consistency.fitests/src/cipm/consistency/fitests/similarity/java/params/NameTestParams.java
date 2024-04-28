package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.INamedElementInitialiser;
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
import cipm.consistency.fitests.similarity.java.initialiser.impl.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.VariableLengthParameterInitialiser;

public class NameTestParams implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
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
		
		return new InitialiserParameters()
				.getInitialisersBySuper(INamedElementInitialiser.class)
				.stream()
				
				// PackageInitialiser is excluded, as its name (.getName()) is NOT supposed to be fiddled with
				// Block is excluded, since its name also is NOT used while comparing
				.filter((i) -> !i.getClass().equals(PackageInitialiser.class)
						&& !i.getClass().equals(BlockInitialiser.class))
				.map((i) -> Arguments.of(i));
	}
}
