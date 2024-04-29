package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

public interface IImportingElementInitialiser extends ICommentableInitialiser {
	public default void addImport(ImportingElement ie, Import imp) {
		if (imp != null) {
			ie.getImports().add(imp);
			assert ie.getImports().contains(imp);
		}
	}
}
