package cipm.consistency.fitests.similarity.java;

import java.util.HashMap;
import java.util.Map;

import org.emftext.language.java.containers.Origin;
import org.emftext.language.java.containers.Module;

public interface IPackageTest extends IModuleTest {
	public default Map<ResourceParameters, Object> makePackageParam(String name, Origin origin, String[] namespaces, boolean putPackageInModule) {
		var result = this.makePackageParam(name, origin, namespaces);
		result.put(ResourceParameters.SET_MODULE_AS_PACKAGE_CONTAINER, Boolean.valueOf(putPackageInModule));
		
		return result;
	}
	
	@SuppressWarnings("serial")
	public default Map<ResourceParameters, Object> makePackageParam(String name, Origin origin, String[] namespaces) {
		return new HashMap<ResourceParameters, Object>() {{
			put(ResourceParameters.NAME, name);
			put(ResourceParameters.ORIGIN, origin);
			put(ResourceParameters.NAMESPACE, namespaces);
		}};
	}
	
	public default Map<ResourceParameters, Object> makePackageInModuleParam(Module mod, String name, Origin origin, String[] namespaces) {
		var result = this.makePackageParam(name, origin, namespaces, true);
		result.put(ResourceParameters.MODULE, mod);
		return result;
	}
}
