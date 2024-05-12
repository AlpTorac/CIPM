package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;
import cipm.consistency.fitests.similarity.java.params.ParameterUtil;

public class AnnotableAndModifiableTestParams implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
		// TODO: Extract the long version below for structure testing later
		
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
//					// Clone to not set IMemberContainerInitialiser again
//					m = m.newInitialiser();
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
//					var pc = (MemberInitialiser) argArr[1];
//					
//					// Clone to not set IMemberContainerInitialiser again
////					var newPc = pc.newInitialiser();
////					newPc.setMCInit(pc.getMCInit());
//					
//					// Clone to not set IParametrizableInitialiser again
//					p = p.newInitialiser();
//					p.setPInit((IParametrizableInitialiser) pc);
//					return p;
//				}));
//		
//		var rest = util.toArgumentCol(initParams
//					.getInitialisersBySuper(IAnnotableAndModifiableInitialiser.class)
//					.stream()
////					.filter((i) -> !ParameterInitialiser.class.isInstance(i)
////							&& !MemberInitialiser.class.isInstance(i))
//				);
//		
//		Stream<Arguments> args = Stream.of(
//				mXmc.stream()
//				.filter((arg) -> IAnnotableAndModifiableInitialiser.class.isInstance(arg.get()[0]))
//				.map((arg) -> Arguments.of(arg.get()[0],
//						arg.get()[0].getClass().getSimpleName() + " with "
//						+ ((MemberInitialiser) arg.get()[0]).getMCInit().getClass().getSimpleName()))
//				,
//				pXmxmc.stream()
//				.map((arg) -> Arguments.of(arg.get()[0],
//						arg.get()[0].getClass().getSimpleName() + " with "
//						+ ((ParameterInitialiser) arg.get()[0]).getPInit().getClass().getSimpleName() + " with "
//						+ ((MemberInitialiser) ((ParameterInitialiser) arg.get()[0]).getPInit()).getMCInit().getClass().getSimpleName()))
//				,
//				rest.stream()
//				.map((arg) -> Arguments.of(arg.get()[0], arg.get()[0].getClass().getSimpleName()))
//				)
//				.flatMap((i) -> i);
//		
//		return args;

		// End of the long version, start of the short version
		
		var initParams = new InitialiserParameters();
		var util = new ParameterUtil();
		
		return initParams.getInitialisersBySuper(IAnnotableAndModifiableInitialiser.class)
				.stream()
				.map((i) -> Arguments.of(i, i.getClass().getSimpleName()));
		
		// The version without InitialiserParameters
		
//		var mc = new ClassInitialiser();
//		var pc = new ConstructorInitialiser().withMCInit(mc);
		
//		return Stream.of(
//					// ConcreteClassifier
//					Arguments.of(new AnnotationInitialiser()),
//					Arguments.of(new ClassInitialiser()),
//					Arguments.of(new EnumerationInitialiser()),
//					Arguments.of(new InterfaceInitialiser()),
//					
//					// Constructor
//					Arguments.of(new ConstructorInitialiser()),
////					Arguments.of(new ConstructorInitialiser().withMCInit(mc)),
//					
//					// Field
//					Arguments.of(new FieldInitialiser()),
////					Arguments.of(new FieldInitialiser().withMCInit(mc)),
//					
//					// LocalVariable
////					Arguments.of(new LocalVariableInitialiser()),
//					
//					// Method
//					Arguments.of(new ClassMethodInitialiser()),
//					Arguments.of(new InterfaceMethodInitialiser()),
////					Arguments.of(new ClassMethodInitialiser().withMCInit(mc)),
////					Arguments.of(new InterfaceMethodInitialiser().withMCInit(mc)),
//					
//					// Parameter
//					Arguments.of(new OrdinaryParameterInitialiser()),
//					Arguments.of(new CatchParameterInitialiser()),
//					Arguments.of(new ReceiverParameterInitialiser()),
//					Arguments.of(new VariableLengthParameterInitialiser())
////					Arguments.of(new OrdinaryParameterInitialiser().withPInit(pc)),
////					Arguments.of(new CatchParameterInitialiser().withPInit(pc)),
////					Arguments.of(new ReceiverParameterInitialiser().withPInit(pc)),
////					Arguments.of(new VariableLengthParameterInitialiser().withPInit(pc))
//				);
	}
}