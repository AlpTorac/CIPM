package cipm.consistency.fitests.similarity.java.params;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserBase;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.initadapters.BlockContainerInitialiserAdapter;
import cipm.consistency.fitests.similarity.java.initialiser.initadapters.ConcreteClassifierInitialiserAdapter;
import cipm.consistency.fitests.similarity.java.initialiser.initadapters.MemberInitialiserAdapter;
import cipm.consistency.fitests.similarity.java.initialiser.initadapters.NamedElementInitialiserAdapter;
import cipm.consistency.fitests.similarity.java.initialiser.initadapters.NewConstructorCallInitialiserAdapter;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.INewConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ClassifierReferenceInitialiser;

/**
 * A concrete implementation of {@link IInitialiserParameterAdaptationStrategy}.
 * <br>
 * <br>
 * Adapts the given {@link IInitialiser} instances in a way that interface tests
 * can run without exceptions being thrown, due to objects they instantiate
 * missing certain components.
 */
public class InitialiserParameterAdaptationStrategy implements IInitialiserParameterAdaptationStrategy {

	@Override
	public void adaptInitialisers(Collection<IInitialiser> inits) {
		inits.stream().filter((i) -> IInitialiserBase.class.isAssignableFrom(i.getClass()))
				.map((i) -> (IInitialiserBase) i).forEach((i) -> {
					if (INamedElementInitialiser.class.isAssignableFrom(i.getClass())) {
						i.addAdaptingInitialiser(new NamedElementInitialiserAdapter());
					}
					if (IBlockContainerInitialiser.class.isAssignableFrom(i.getClass())) {
						i.addAdaptingInitialiser(new BlockContainerInitialiserAdapter(new BlockInitialiser()));
					}
					if (IMemberInitialiser.class.isAssignableFrom(i.getClass())) {
						i.addAdaptingInitialiser(new MemberInitialiserAdapter(new ClassInitialiser()));
					}
					if (IConcreteClassifierInitialiser.class.isAssignableFrom(i.getClass())) {
						i.addAdaptingInitialiser(
								new ConcreteClassifierInitialiserAdapter(new CompilationUnitInitialiser()));
					}
					if (INewConstructorCallInitialiser.class.isAssignableFrom(i.getClass())) {
						i.addAdaptingInitialiser(new NewConstructorCallInitialiserAdapter(
								new ClassifierReferenceInitialiser(), new ClassInitialiser()));
					}
				});
	}

}
