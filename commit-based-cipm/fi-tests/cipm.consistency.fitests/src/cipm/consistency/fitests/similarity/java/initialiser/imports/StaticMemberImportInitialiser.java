package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.StaticMemberImport;

public class StaticMemberImportInitialiser implements IStaticMemberImportInitialiser {
	@Override
	public IStaticMemberImportInitialiser newInitialiser() {
		return new StaticMemberImportInitialiser();
	}

	@Override
	public StaticMemberImport instantiate() {
		return ImportsFactory.eINSTANCE.createStaticMemberImport();
	}
}