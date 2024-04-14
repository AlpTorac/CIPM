package cipm.consistency.fitests.similarity.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;

import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.utils.DummySimilaritySwitch;
import cipm.consistency.fitests.similarity.java.utils.IJavaModelConstructor;
import cipm.consistency.fitests.similarity.java.utils.InnerSwitchFactory;

public class PackageSimilarityTest extends AbstractSimilarityTest implements IPackageTest {
	@Override
	public InnerSwitchFactory initSwitchFactory() {
		return new InnerSwitchFactory() {
			@Override
			public List<Switch<Boolean>> createSwitchesFor(DummySimilaritySwitch dss) {
				var list = new ArrayList<Switch<Boolean>>();
				list.add(new ContainersSimilaritySwitch(dss, getDefaultCheckStatementPosition()));
				return list;
			}
		};
	}
	
	@Test
	public void testNamespaceEquality() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		Map<ResourceParameters, Object> params1 = this.makePackageParam("pac", Origin.BINDING, pacNamespaces);
		Map<ResourceParameters, Object> params2 = this.makePackageParam("pac", Origin.BINDING, pacNamespaces);
		
		var resOne = this.createResource("pacRes11", pacCTor, params1);
		var resTwo = this.createResource("pacRes12", pacCTor, params2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testNamespaceUnequality() throws IOException {
		var pacNamespaces1 = new String[] {"ns1", "ns2", "ns3"};
		var pacNamespaces2 = new String[] {"ns11", "n", "ns"};
		
		Map<ResourceParameters, Object> params1 = this.makePackageParam("pac", Origin.BINDING, pacNamespaces1);
		Map<ResourceParameters, Object> params2 = this.makePackageParam("pac", Origin.BINDING, pacNamespaces2);
		
		var resOne = this.createResource("pacRes11", pacCTor, params1);
		var resTwo = this.createResource("pacRes12", pacCTor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModuleEquality() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac", Origin.BINDING, pacNamespaces)
		});
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac", Origin.BINDING, pacNamespaces)
		});
		
		var resOne = this.createResource("pacRes11", modCTor, params1);
		var resTwo = this.createResource("pacRes12", modCTor, params2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModuleUnequality() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod1",
				new Map[] {
						this.makePackageParam("pac", Origin.BINDING, pacNamespaces)
		});
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod2",
				new Map[] {
						this.makePackageParam("pac", Origin.BINDING, pacNamespaces)
		});
		
		var resOne = this.createResource("pacRes11", modCTor, params1);
		var resTwo = this.createResource("pacRes12", modCTor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@Test
	public void testModuleAbsence() throws IOException {
		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
		
		@SuppressWarnings("unchecked")
		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
				new Map[] {
						this.makePackageParam("pac", Origin.BINDING, pacNamespaces)
		});
		
		Map<ResourceParameters, Object> params2 = this.makePackageParam("pac", Origin.BINDING, pacNamespaces);
		
		var resOne = this.createResource("pacRes11", modCTor, params1);
		var resTwo = this.createResource("pacRes12", pacCTor, params2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
