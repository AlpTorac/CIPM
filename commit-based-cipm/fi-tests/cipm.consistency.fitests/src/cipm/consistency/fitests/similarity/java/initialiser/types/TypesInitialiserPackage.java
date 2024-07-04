package cipm.consistency.fitests.similarity.java.initialiser.types;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class TypesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new BooleanInitialiser(),
				new ByteInitialiser(),
				new CharInitialiser(),
				new ClassifierReferenceInitialiser(),
				new DoubleInitialiser(),
				new FloatInitialiser(),
				new InferableTypeInitialiser(),
				new IntInitialiser(),
				new LongInitialiser(),
				new NamespaceClassifierReferenceInitialiser(),
				new ShortInitialiser(),
				new VoidInitialiser(),
		});
	}
}
