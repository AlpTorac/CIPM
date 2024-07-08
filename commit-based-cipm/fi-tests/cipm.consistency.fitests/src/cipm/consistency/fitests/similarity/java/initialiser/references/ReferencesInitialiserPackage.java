package cipm.consistency.fitests.similarity.java.initialiser.references;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ReferencesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
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
	
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IArgumentableInitialiser.class,
				IElementReferenceInitialiser.class,
				IIdentifierReferenceInitialiser.class,
				IMethodCallInitialiser.class,
				IPackageReferenceInitialiser.class,
				IPrimitiveTypeReferenceInitialiser.class,
				IReferenceableElementInitialiser.class,
				IReferenceInitialiser.class,
				IReflectiveClassReferenceInitialiser.class,
				ISelfReferenceInitialiser.class,
				IStringReferenceInitialiser.class,
				ITextBlockReferenceInitialiser.class,
		});
	}
}
