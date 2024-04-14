package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

public interface IImportingElementInitialiser extends EObjectInitialiser {
	@Override
	public ImportingElement instantiate();
	
	@Override
	public default ImportingElement clone(EObject obj) {
		return (ImportingElement) EObjectInitialiser.super.clone(obj);
	}
	
	public default void addImport(ImportingElement ie, Import imp) {
		if (imp != null) {
			ie.getImports().add(imp);
			assert ie.getImports().contains(imp);
		}
	}
}
