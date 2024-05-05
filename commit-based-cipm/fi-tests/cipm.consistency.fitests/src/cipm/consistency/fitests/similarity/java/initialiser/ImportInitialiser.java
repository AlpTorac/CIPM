package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public abstract class ImportInitialiser implements EObjectInitialiser {
	private IConcreteClassifierInitialiser clsInit;
	
	public ImportInitialiser withClsInit(IConcreteClassifierInitialiser clsInit) {
		this.setClsInit(clsInit);
		return this;
	}
	
	public void setClsInit(IConcreteClassifierInitialiser clsInit) {
		this.clsInit = clsInit;
	}
	
	public IConcreteClassifierInitialiser getClsInit() {
		return this.clsInit;
	}
	
	@Override
	public EObject minimalInitialisationWithContainer(EObject obj) {
		var castedO = (Import) obj;
		this.minimalInitialisation(castedO);
		
		var clsInit = this.getClsInit();
		ConcreteClassifier cls = clsInit.instantiate();
		var unit = clsInit.minimalInitialisationWithContainer(cls);
		cls.addImport(castedO.getNamespacesAsString());
		
		return unit;
	}
}
