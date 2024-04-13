package cipm.consistency.fitests.similarity.java;

import java.util.HashMap;
import java.util.Map;

public interface IModuleTest {
	@SuppressWarnings("serial")
	public default Map<ResourceParameters, Object> makeMinimalModuleParam(String name) {
		return new HashMap<ResourceParameters, Object>() {{
			put(ResourceParameters.NAME, name);
		}};
	}
	
	public default Map<ResourceParameters, Object> makeMinimalModuleWithPackagesParam(String name,
			Map<ResourceParameters, Object>[] packages) {
		var result = this.makeMinimalModuleParam(name);
		result.put(ResourceParameters.PACKAGES, packages);
		return result;
	}
}
