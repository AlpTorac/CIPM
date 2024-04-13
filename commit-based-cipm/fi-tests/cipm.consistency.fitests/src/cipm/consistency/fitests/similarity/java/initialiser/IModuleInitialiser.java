package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modifiers.Open;

import cipm.consistency.fitests.similarity.java.ResourceParameters;

public interface IModuleInitialiser extends IJavaRootInitialiser {
	/**
	 * pacParams could help determine which initialiser is the correct one
	 * 
	 * ToDo: Edit commentary when the interface and implementing classes are done
	 */
	public IPackageInitialiser getPackageInitialiserFor(Map<ResourceParameters, Object> pacParam);
	
	public Module instantiateModule(Map<ResourceParameters, Object> modParam);
	
	public default Module initialiseInnerPackages(Module mod, Map<ResourceParameters, Object>[] pacParams) {
		if (pacParams != null) {
			for (var param : pacParams) {
				var setModuleAsPackageContainer = this.getSetModuleAsPackageContainer(param);
				
				if (setModuleAsPackageContainer != null && setModuleAsPackageContainer) {
					param.put(ResourceParameters.MODULE, mod);
					assert param.get(ResourceParameters.MODULE).equals(mod);
				}
				
				var pacInitialiser = this.getPackageInitialiserFor(param);
				var pac = pacInitialiser.instantiatePackage(param);
				
				pacInitialiser.initialisePackage(pac, param);
			}
		}
		
		return mod;
	}
	
	public default Module initialiseOpen(Module mod, Open open) {
		if (open != null) {
			mod.setOpen(open);
			assert mod.getOpen().equals(open);
		}
		return mod;
	}
	
	public default Module initialiseModule(Module mod, Map<ResourceParameters, Object> params) {
		this.initialiseJavaRoot(mod, params);
		this.initialiseOpen(mod, this.getOpenParam(params));
		this.initialiseInnerPackages(mod, this.getInnerPackageParams(params));
		
		return mod;
	}
	
	public default Open getOpenParam(Map<ResourceParameters, Object> params) {
		return (Open) params.get(ResourceParameters.OPEN);
	}
	
	@SuppressWarnings("unchecked")
	public default Map<ResourceParameters, Object>[] getInnerPackageParams(Map<ResourceParameters, Object> params) {
		return (Map<ResourceParameters, Object>[]) params.get(ResourceParameters.PACKAGES);
	}
	
	public default Boolean getSetModuleAsPackageContainer(Map<ResourceParameters, Object> params) {
		return (Boolean) params.get(ResourceParameters.SET_MODULE_AS_PACKAGE_CONTAINER);
	}
	
	@Override
	public default Module instantiate(Map<ResourceParameters, Object> params) {
		return this.instantiateModule(params);
	}
	
	@Override
	public default Module initialise(EObject obj, Map<ResourceParameters, Object> params) {
		Module mod = (Module) obj;
		return this.initialiseModule(mod, params);
	}
	
	@Override
	public default Module build(Map<ResourceParameters, Object> params) {
		var instance = this.instantiate(params);
		return this.initialise(instance, params);
	}
	
	@Override
	public default Module build() {
		return this.build(null);
	}
}
