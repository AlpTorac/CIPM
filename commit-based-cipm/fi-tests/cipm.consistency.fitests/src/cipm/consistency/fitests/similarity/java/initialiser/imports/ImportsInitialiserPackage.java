package cipm.consistency.fitests.similarity.java.initialiser.imports;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ImportsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new ClassifierImportInitialiser(),
				new PackageImportInitialiser(),
				new StaticClassifierImportInitialiser(),
				new StaticMemberImportInitialiser(),	
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IClassifierImportInitialiser.class,
				IImportingElementInitialiser.class,
				IImportInitialiser.class,
				IPackageImportInitialiser.class,
				IStaticClassifierImportInitialiser.class,
				IStaticImportInitialiser.class,
				IStaticMemberImportInitialiser.class,
		});
	}
}
