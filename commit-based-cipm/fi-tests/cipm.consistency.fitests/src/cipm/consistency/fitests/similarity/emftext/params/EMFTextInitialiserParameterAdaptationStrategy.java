package cipm.consistency.fitests.similarity.emftext.params;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserBase;
import cipm.consistency.initialisers.emftext.classifiers.ClassInitialiser;
import cipm.consistency.initialisers.emftext.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.initialisers.emftext.commons.INamedElementInitialiser;
import cipm.consistency.initialisers.emftext.containers.CompilationUnitInitialiser;
import cipm.consistency.initialisers.emftext.initadapters.BlockContainerInitialiserAdapter;
import cipm.consistency.initialisers.emftext.initadapters.ClassMethodInitialiserAdapter;
import cipm.consistency.initialisers.emftext.initadapters.ConcreteClassifierInitialiserAdapter;
import cipm.consistency.initialisers.emftext.initadapters.MemberInitialiserAdapter;
import cipm.consistency.initialisers.emftext.initadapters.NamedElementInitialiserAdapter;
import cipm.consistency.initialisers.emftext.initadapters.NewConstructorCallInitialiserAdapter;
import cipm.consistency.initialisers.emftext.instantiations.INewConstructorCallInitialiser;
import cipm.consistency.initialisers.emftext.members.IClassMethodInitialiser;
import cipm.consistency.initialisers.emftext.members.IMemberInitialiser;
import cipm.consistency.initialisers.emftext.statements.BlockInitialiser;
import cipm.consistency.initialisers.emftext.statements.IBlockContainerInitialiser;
import cipm.consistency.initialisers.emftext.types.ClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.params.IInitialiserParameterAdaptationStrategy;

/**
 * A concrete implementation of {@link IInitialiserParameterAdaptationStrategy}.
 * <br>
 * <br>
 * Adapts the given {@link IInitialiser} instances in a way that interface tests
 * can run without exceptions being thrown, due to objects they instantiate
 * missing certain components.
 */
public class EMFTextInitialiserParameterAdaptationStrategy implements IInitialiserParameterAdaptationStrategy {

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
					if (IClassMethodInitialiser.class.isAssignableFrom(i.getClass())) {
						i.addAdaptingInitialiser(new ClassMethodInitialiserAdapter(new BlockInitialiser()));
					}
				});
	}

}