package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.containers.Package;

import org.eclipse.emf.ecore.EObject;

public interface IPackageContaineeGenerator {
	public default void setDefaultPacGen() {
		this.setPacGen(new PackageGenerator());
	}
	
	public void setPacGen(PackageGenerator pacGen);
	public PackageGenerator getPacGen();
	
	public default <T extends EObject> void setPackage(T obj) {
		var gen = this.getPacGen();
		if (gen != null)
			this.initialiserSetPackage(this.getPacGen().generateElement(), obj);
	}
	
	<T extends EObject> void initialiserSetPackage(Package pac, T obj);
}
