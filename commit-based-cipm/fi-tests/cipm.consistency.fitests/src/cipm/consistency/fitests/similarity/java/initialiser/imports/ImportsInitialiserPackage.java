package cipm.consistency.fitests.similarity.java.initialiser.imports;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ImportsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new ClassifierImportInitialiser(),
				new PackageImportInitialiser(),
				new StaticClassifierImportInitialiser(),
				new StaticMemberImportInitialiser(),	
		});
	}
	
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
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
