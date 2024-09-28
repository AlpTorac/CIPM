package cipm.consistency.fitests.similarity.eobject.java.params;

import java.util.Collection;

import cipm.consistency.initialisers.eobject.java.classifiers.ClassInitialiser;
import cipm.consistency.initialisers.eobject.java.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.initialisers.eobject.java.commons.INamedElementInitialiser;
import cipm.consistency.initialisers.eobject.java.containers.CompilationUnitInitialiser;
import cipm.consistency.initialisers.eobject.java.initadapters.BlockContainerInitialiserAdapter;
import cipm.consistency.initialisers.eobject.java.initadapters.ClassMethodInitialiserAdapter;
import cipm.consistency.initialisers.eobject.java.initadapters.ConcreteClassifierInitialiserAdapter;
import cipm.consistency.initialisers.eobject.java.initadapters.MemberInitialiserAdapter;
import cipm.consistency.initialisers.eobject.java.initadapters.NamedElementInitialiserAdapter;
import cipm.consistency.initialisers.eobject.java.initadapters.NewConstructorCallInitialiserAdapter;
import cipm.consistency.initialisers.eobject.java.instantiations.INewConstructorCallInitialiser;
import cipm.consistency.initialisers.eobject.java.members.IClassMethodInitialiser;
import cipm.consistency.initialisers.eobject.java.members.IMemberInitialiser;
import cipm.consistency.initialisers.eobject.java.statements.BlockInitialiser;
import cipm.consistency.initialisers.eobject.java.statements.IBlockContainerInitialiser;
import cipm.consistency.initialisers.eobject.java.types.ClassifierReferenceInitialiser;
import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserBase;
import cipm.consistency.fitests.similarity.params.IInitialiserParameterAdaptationStrategy;

/**
 * A concrete implementation of {@link IInitialiserParameterAdaptationStrategy}.
 * <br>
 * <br>
 * Adapts the given {@link IInitialiser} instances in a way that interface tests
 * can run without exceptions being thrown, due to objects they instantiate
 * missing certain components.
 */
public class EObjectJavaInitialiserParameterAdaptationStrategy implements IInitialiserParameterAdaptationStrategy {

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
