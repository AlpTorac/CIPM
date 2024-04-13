package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface IPackageInitialiser extends IJavaRootInitialiser {
	public Package instantiatePackage(Map<ResourceParameters, Object> pacParam);
	
	public default Package initialiseModuleField(Package pac, Module mod) {
		pac.setModule(mod);
		
		assert pac.getModule().equals(mod);
		return pac;
	}
	
	public default Package initialisePackage(Package pac, Map<ResourceParameters, Object> params) {
		this.initialiseJavaRoot(pac, params);
		this.initialiseModuleField(pac, this.getModuleFieldParam(params));
		
		return pac;
	}
	
	public default Module getModuleFieldParam(Map<ResourceParameters, Object> params) {
		return (Module) params.get(ResourceParameters.MODULE);
	}
	
	@Override
	public default Package initialise(EObject obj, Map<ResourceParameters, Object> params) {
		Package pac = (Package) obj;
		return this.initialisePackage(pac, params);
	}
	
	@Override
	public default Package instantiate(Map<ResourceParameters, Object> params) {
		return this.instantiatePackage(params);
	}
	
	@Override
	public default Package build(Map<ResourceParameters, Object> params) {
		var instance = this.instantiate();
		return this.initialise(instance, params);
	}
	
	@Override
	public default Package build() {
		return this.build(null);
	}
}
