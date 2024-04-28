package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;
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
import cipm.consistency.fitests.similarity.java.initialiser.impl.MemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.VariableLengthParameterInitialiser;

public class AnnotableAndModifiableTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// TODO: Clean up
//		var initParams = new InitialiserParameters();
//		var util = new ParameterUtil();
//		
//		var mInits = util.toArgumentCol(initParams
//				.getInitialisersBySuper(IMemberInitialiser.class));
//		
//		var pInits = util.toArgumentCol(initParams
//				.getInitialisersBySuper(IParameterInitialiser.class));
//		
//		var mcInits = util.toArgumentCol(initParams
//				.getInitialisersBySuper(IMemberContainerInitialiser.class));
//		
//		/*
//		 * All possible member (not all implement IAnnotableAndModifiableInitialiser)
//		 * and member container initialiser combinations
//		 */
//		var mXmc = util.toArgumentCol(
//				util.permute(mInits, mcInits)
//				.stream()
//				.filter((args) -> MemberInitialiser.class.isInstance(args.get()[0]))
//				.map((args) -> {
//					var argArr = args.get();
//					var m = (MemberInitialiser) argArr[0];
//					var mc = (IMemberContainerInitialiser) argArr[1];
//					
//					m.setMCInit(mc);
//					return m;
//				}));
//		
//		/*
//		 * All possible parameter (implements IAnnotableAndModifiableInitialiser)
//		 * and mXmc combinations
//		 */
//		var pXmxmc = util.toArgumentCol(
//				util.permute(pInits, mXmc)
//				.stream()
//				.filter((args) -> ParameterInitialiser.class.isInstance(args.get()[0]))
//				.filter((args) -> IParametrizableInitialiser.class.isInstance(args.get()[1]))
//				.map((args) -> {
//					var argArr = args.get();
//					var p = (ParameterInitialiser) argArr[0];
//					var pc = (IParametrizableInitialiser) argArr[1];
//					
//					p.setPInit(pc);
//					return p;
//				}));
//		
//		Stream<Arguments> args = Stream.of(
//				mXmc.stream()
//				.filter((arg) -> IAnnotableAndModifiableInitialiser.class.isInstance(arg.get()[0])),
//				pXmxmc.stream()).flatMap((i) -> i);
//		
//		return args;
		
		var mc = new ClassInitialiser();
		var pc = new ConstructorInitialiser().withMCInit(mc);
		
		return Stream.of(
					// ConcreteClassifier
					Arguments.of(new AnnotationInitialiser()),
					Arguments.of(new ClassInitialiser()),
					Arguments.of(new EnumerationInitialiser()),
					Arguments.of(new InterfaceInitialiser()),
					
					// Constructor
					Arguments.of(new ConstructorInitialiser().withMCInit(mc)),
					
					// Field
					Arguments.of(new FieldInitialiser().withMCInit(mc)),
					
					// LocalVariable
//					Arguments.of(new LocalVariableInitialiser()),
					
					// Method
					Arguments.of(new ClassMethodInitialiser().withMCInit(mc)),
					Arguments.of(new InterfaceMethodInitialiser().withMCInit(mc)),
					
					// Parameter
					Arguments.of(new OrdinaryParameterInitialiser().withPInit(pc)),
					Arguments.of(new CatchParameterInitialiser().withPInit(pc)),
					Arguments.of(new ReceiverParameterInitialiser().withPInit(pc)),
					Arguments.of(new VariableLengthParameterInitialiser().withPInit(pc))
				);
	}
}