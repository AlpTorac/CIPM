package cipm.consistency.fitests.similarity.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;

import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;

public class PackageSimilarityTest extends AbstractSimilarityTest implements IPackageTest {
	private IJavaModelConstructor ctor = new IJavaModelConstructor() {
		@Override
		public void fillResource(Resource res, Map<ResourceParameters, Object> params) {
			res.getContents().add(new ModuleInitialiser().build(params));
		}
	};
	
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPackageEquality() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		var params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac", Origin.BINDING, pacNamespaces)
				});
		
		var resOne = this.createResource("pacResOne", ctor, params1);
		var resTwo = this.createResource("pacResTwo", ctor, params1);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
