package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

public interface IImportingElementInitialiser extends EObjectInitialiser {
	@Override
	public ImportingElement getCurrentObject();
	
	public default void addImport(Import imp) {
		var cObj = this.getCurrentObject();
		
		if (imp != null) {
			cObj.getImports().add(imp);
			assert cObj.getImports().contains(imp);
		}
	}
	
	@Override
	public default ImportingElement build() {
		return (ImportingElement) EObjectInitialiser.super.build();
	}
}
