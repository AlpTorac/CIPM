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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;

import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;

public class ModuleSimilarityTest extends AbstractSimilarityTest implements IModuleTest, IPackageTest {
	private IJavaModelConstructor ctor = new IJavaModelConstructor() {
		@Override
		public void fillResource(Resource res, Map<ResourceParameters, Object> params) {
			var initialiser = new ModuleInitialiser();
			
			initialiser.setResource(res);
			initialiser.build(params);
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
	
	@Test
	public void testModuleNameEquality() throws IOException {
		var param = this.makeMinimalModuleParam("mod1");
		
		var resOne = this.createResource("modRes1", ctor, param);
		var resTwo = this.createResource("modRes2", ctor, param);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModuleNameUnequality() throws IOException {
		var params1 = this.makeMinimalModuleParam("mod1");
		var params2 = this.makeMinimalModuleParam("mod2");
		
		var resOne = this.createResource("modRes1", ctor, params1);
		var resTwo = this.createResource("modRes2", ctor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModuleStructureEquality() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
		});
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
		});
		
		var resOne = this.createResource("modRes11", ctor, params1);
		var resTwo = this.createResource("modRes12", ctor, params2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	/**
	 * Package names are currently not compared, disabled until further clarification
	 */
	@Disabled
	@Test
	public void testModulePackagesUnequality() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces)
		});
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
		});
		
		var resOne = this.createResource("pacRes21", ctor, params1);
		var resTwo = this.createResource("pacRes22", ctor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModulePackagesSecondSubset() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
		});
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
		});
		
		var resOne = this.createResource("pacRes21", ctor, params1);
		var resTwo = this.createResource("pacRes22", ctor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModulePackagesFirstSubset() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces)
		});
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
		});
		
		var resOne = this.createResource("pacRes21", ctor, params1);
		var resTwo = this.createResource("pacRes22", ctor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
