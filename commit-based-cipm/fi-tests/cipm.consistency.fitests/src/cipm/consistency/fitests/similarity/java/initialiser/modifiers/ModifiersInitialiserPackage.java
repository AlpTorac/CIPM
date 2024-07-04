package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ModifiersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
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
}
