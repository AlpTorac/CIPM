package cipm.consistency.fitests.similarity.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;

import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;

public class ModuleSimilarityTest extends AbstractSimilarityTest implements IModuleTest {
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
	
	@Test
	public void test() throws IOException {
		var ctor = new IJavaModelConstructor() {
			@Override
			public void fillResource(Resource res, Map<ResourceParameters, Object> params) {
				res.getContents().add(new ModuleInitialiser().build(params));
			}
		};
		
		var params1 = this.makeMinimalModuleParam("mod1");
		var params2 = this.makeMinimalModuleParam("mod2");
		
		var resOne = this.createResource("modResOne", ctor, params1);
		var resTwo = this.createResource("modResTwo", ctor, params2);
		var resThree = this.createResource("modResThree", ctor, params2);
		
		Assertions.assertTrue(this.areSimilar(resTwo.getContents(), resThree.getContents()));
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
