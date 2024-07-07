package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ModifiersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new AbstractInitialiser(),
				new DefaultInitialiser(),
				new FinalInitialiser(),
				new NativeInitialiser(),
				new OpenInitialiser(),
				new PrivateInitialiser(),
				new ProtectedInitialiser(),
				new PublicInitialiser(),
				new StaticInitialiser(),
				new StrictfpInitialiser(),
				new SynchronizedInitialiser(),
				new TransientInitialiser(),
				new TransitiveInitialiser(),
				new VolatileInitialiser(),
		});
	}
	
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IAbstractInitialiser.class,
				IAnnotableAndModifiableInitialiser.class,
				IAnnotationInstanceOrModifierInitialiser.class,
				IDefaultInitialiser.class,
				IFinalInitialiser.class,
				IModifiableInitialiser.class,
				IModifierInitialiser.class,
				IModuleRequiresModifierInitialiser.class,
				INativeInitialiser.class,
				IOpenInitialiser.class,
				IPrivateInitialiser.class,
				IProtectedInitialiser.class,
				IPublicInitialiser.class,
				IStaticInitialiser.class,
				IStrictfpInitialiser.class,
				ISynchronizedInitialiser.class,
				ITransientInitialiser.class,
				ITransitiveInitialiser.class,
				IVolatileInitialiser.class,
		});
	}
}
