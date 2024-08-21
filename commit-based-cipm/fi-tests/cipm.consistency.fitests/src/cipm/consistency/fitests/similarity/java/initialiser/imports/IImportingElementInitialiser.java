package cipm.consistency.fitests.similarity.java.initialiser.imports;

import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IImportingElementInitialiser extends ICommentableInitialiser {
	@Override
	public ImportingElement instantiate();

	public default boolean addImport(ImportingElement ie, Import imp) {
		if (imp != null) {
			ie.getImports().add(imp);
			return ie.getImports().contains(imp);
		}
		return true;
	}

	public default boolean addImports(ImportingElement ie, Import[] imps) {
		return this.doMultipleModifications(ie, imps, this::addImport);
	}
}