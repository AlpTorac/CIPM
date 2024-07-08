package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportingElementInitialiser;

public interface UsesImportingElements extends UsesImports {
	public default ImportingElement createMinimalImportingElement(IImportingElementInitialiser init, String clsName) {
		return this.createMinimalImportingElement(init, this.createMinimalClsImport(clsName));
	}
	
	public default ImportingElement createMinimalImportingElement(IImportingElementInitialiser init, Import imp) {
		ImportingElement result = init.instantiate();
		init.addImport(result, imp);
		
		return result;
	}
}
