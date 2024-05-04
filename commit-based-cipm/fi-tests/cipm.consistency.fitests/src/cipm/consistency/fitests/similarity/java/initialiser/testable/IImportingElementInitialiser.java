package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IImportingElementInitialiser extends ICommentableInitialiser {
	public default void addImport(ImportingElement ie, Import imp) {
		if (imp != null) {
			ie.getImports().add(imp);
			assert ie.getImports().contains(imp);
		}
	}
}
