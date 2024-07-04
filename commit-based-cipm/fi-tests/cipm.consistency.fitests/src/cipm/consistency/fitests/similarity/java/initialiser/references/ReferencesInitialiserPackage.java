package cipm.consistency.fitests.similarity.java.initialiser.references;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ReferencesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new IdentifierReferenceInitialiser(),
				new MethodCallInitialiser(),
				new PackageReferenceInitialiser(),
				new PrimitiveTypeReferenceInitialiser(),
				new ReflectiveClassReferenceInitialiser(),
				new SelfReferenceInitialiser(),
				new StringReferenceInitialiser(),
				new TextBlockReferenceInitialiser(),
		});
	}
}
