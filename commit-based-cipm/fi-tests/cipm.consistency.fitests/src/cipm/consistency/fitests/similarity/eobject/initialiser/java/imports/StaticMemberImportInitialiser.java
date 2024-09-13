package cipm.consistency.fitests.similarity.eobject.initialiser.java.imports;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.StaticMemberImport;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class StaticMemberImportInitialiser extends AbstractInitialiserBase implements IStaticMemberImportInitialiser {
	@Override
	public IStaticMemberImportInitialiser newInitialiser() {
		return new StaticMemberImportInitialiser();
	}

	@Override
	public StaticMemberImport instantiate() {
		return ImportsFactory.eINSTANCE.createStaticMemberImport();
	}
}