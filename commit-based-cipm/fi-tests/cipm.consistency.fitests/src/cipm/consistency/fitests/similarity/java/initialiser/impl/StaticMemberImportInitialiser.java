package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.StaticMemberImport;

import cipm.consistency.fitests.similarity.java.initialiser.IStaticMemberImportInitialiser;

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