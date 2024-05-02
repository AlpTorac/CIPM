package cipm.consistency.fitests.similarity.java.params;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.EnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.VariableLengthParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.AdditionalLocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.LocalVariableInitialiser;

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
