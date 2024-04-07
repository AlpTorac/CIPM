package cipm.consistency.fitests.similarity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModuleSimilarityTest extends AbstractSimilarityTest {
	private final String moduleName = "mName";
	
	@SuppressWarnings("serial")
	@Test
	public void test() throws IOException {
		var ctor = new IJavaModelConstructor() {
			@Override
			public void fillResource(Resource res, Map<String, Object> params) {
				var fac = ContainersFactory.eINSTANCE;
				var mod = fac.createModule();
				mod.setName((String) params.get(moduleName));
				
				res.getContents().add(mod);
			}
		};
		
		var params1 = new HashMap<String, Object>() {{
			put(moduleName, "mod1");
		}};
		
		var params2 = new HashMap<String, Object>() {{
			put(moduleName, "mod2");
		}};
		
		var resOne = this.createResource("resOne", ctor, params1);
		var resTwo = this.createResource("resTwo", ctor, params2);
		var resThree = this.createResource("resThree", ctor, params2);
		
		Assertions.assertTrue(this.areSimilar(resTwo.getContents(), resThree.getContents()));
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
