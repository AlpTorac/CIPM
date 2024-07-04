package cipm.consistency.fitests.similarity.java.initialiser.imports;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ImportsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new ClassifierImportInitialiser(),
				new PackageImportInitialiser(),
				new StaticClassifierImportInitialiser(),
				new StaticMemberImportInitialiser(),	
		});
	}
}
