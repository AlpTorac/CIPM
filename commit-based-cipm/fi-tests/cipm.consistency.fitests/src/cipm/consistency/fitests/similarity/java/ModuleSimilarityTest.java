package cipm.consistency.fitests.similarity.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;

public class ModuleSimilarityTest extends AbstractSimilarityTest {
	private final boolean defaultCheckStatementPosition = true;
	private final String moduleName = "mName";
	
	public boolean getDefaultCheckStatementPosition() {
		return this.defaultCheckStatementPosition;
	}
	
	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();
		
		this.setSwitchFactory(new InnerSwitchFactory() {
			@Override
			public List<Switch<Boolean>> createSwitchesFor(DummySimilaritySwitch dss) {
				var list = new ArrayList<Switch<Boolean>>();
				list.add(new ContainersSimilaritySwitch(dss, getDefaultCheckStatementPosition()));
				return list;
			}
		});
	}
	
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
