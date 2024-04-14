package cipm.consistency.fitests.similarity.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.emftext.language.java.containers.Origin;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.splevo.jamopp.diffing.similarity.switches.ContainersSimilaritySwitch;

import cipm.consistency.fitests.similarity.java.initialiser.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.utils.DummySimilaritySwitch;
import cipm.consistency.fitests.similarity.java.utils.IJavaModelConstructor;
import cipm.consistency.fitests.similarity.java.utils.InnerSwitchFactory;

public class ModuleSimilarityTest extends AbstractSimilarityTest {
//	private static final IModuleInitialiser moduleInitialiser = new ModuleInitialiser();
//	private static final IPackageInitialiser packageInitialiser = new PackageInitialiser();
//	
//	private static final Module minimalModule = moduleInitialiser.instantiate();
//	private static final Package minimalPackage = packageInitialiser.instantiate();
//	
//	@Override
//	public void setUp() {
//		this.setResourceFileTestPrefix(ModuleSimilarityTest.class.getSimpleName());
//		super.setUp();
//	}
//	
//	@Override
//	public InnerSwitchFactory initSwitchFactory() {
//		return new InnerSwitchFactory() {
//			@Override
//			public List<Switch<Boolean>> createSwitchesFor(DummySimilaritySwitch dss) {
//				var list = new ArrayList<Switch<Boolean>>();
//				list.add(new ContainersSimilaritySwitch(dss, getDefaultCheckStatementPosition()));
//				return list;
//			}
//		};
//	}
//	
//	private static Stream<Arguments> generateNameSimilarityArguments() {
//		String name1 = "mod1";
//		String name2 = "mod2";
//		
//		var nameChanger1 = new IModelChange() {
//			@Override
//			public void change(EObject obj) {
//				moduleInitialiser.initialiseName((NamedElement) obj, name1);
//			}
//		};
//		
//		var nameChanger2 = new IModelChange() {
//			@Override
//			public void change(EObject obj) {
//				moduleInitialiser.initialiseName((NamedElement) obj, name2);
//			}
//		};
//		
//		return Stream.of(
//					Arguments.of(AbstractSimilarityTest.copyAndChange(minimalModule, nameChanger1), AbstractSimilarityTest.copyAndChange(minimalModule, nameChanger1), Boolean.TRUE),
//					Arguments.of(AbstractSimilarityTest.copyAndChange(minimalModule, nameChanger1), AbstractSimilarityTest.copyAndChange(minimalModule, nameChanger2), Boolean.FALSE),
//					Arguments.of(AbstractSimilarityTest.copyAndChange(minimalModule, nameChanger2), AbstractSimilarityTest.copyAndChange(minimalModule, nameChanger1), Boolean.FALSE)
//				);
//	}
//	
//	@ParameterizedTest
//	@MethodSource("generateNameSimilarityArguments")
//	public void testModuleNameSimilarity(IModelTestParamGenerator param1,
//			IModelTestParamGenerator param2,
//			Boolean result) {
//		
//		this.setResourceFileTestIdentifier("moduleNameSimilarity");
//		
//		var resOne = this.createResource(param1.generateParam());
//		var resTwo = this.createResource(param2.generateParam());
//		
//		Assertions.assertEquals(result, this.areSimilar(resOne.getContents(), resTwo.getContents()));
//	}
//	
//	private static Stream<Arguments> generatePackageSimilarityArguments() {
//		Package pac1 = AbstractSimilarityTest.copyAndChange(minimalPackage, namespaceChanger1);
//		Package pac2 = AbstractSimilarityTest.copyAndChange(minimalPackage, namespaceChanger2);
//	}
//	
//	@Test
//	public void testModuleStructureEquality() throws IOException {
//		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
//						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
//		});
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
//						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
//		});
//		
//		var resOne = this.createResource("modRes11", ctor, params1);
//		var resTwo = this.createResource("modRes12", ctor, params2);
//		
//		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
//	}
//	
//	/**
//	 * Package names are currently not compared, disabled until further clarification
//	 */
//	@Disabled
//	@Test
//	public void testModulePackagesUnequality() throws IOException {
//		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces)
//		});
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
//		});
//		
//		var resOne = this.createResource("pacRes21", ctor, params1);
//		var resTwo = this.createResource("pacRes22", ctor, params2);
//		
//		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
//	}
//	
//	@Test
//	public void testModulePackagesSecondSubset() throws IOException {
//		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
//						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
//		});
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
//		});
//		
//		var resOne = this.createResource("pacRes21", ctor, params1);
//		var resTwo = this.createResource("pacRes22", ctor, params2);
//		
//		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
//	}
//	
//	@Test
//	public void testModulePackagesFirstSubset() throws IOException {
//		var pacNamespaces = new String[] {"ns1", "ns2", "ns3"};
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params1 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces)
//		});
//		
//		@SuppressWarnings("unchecked")
//		Map<ResourceParameters, Object> params2 = this.makeMinimalModuleWithPackagesParam("mod",
//				new Map[] {
//						this.makePackageParam("pac1", Origin.BINDING, pacNamespaces),
//						this.makePackageParam("pac2", Origin.BINDING, pacNamespaces)
//		});
//		
//		var resOne = this.createResource("pacRes21", ctor, params1);
//		var resTwo = this.createResource("pacRes22", ctor, params2);
//		
//		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
//	}
}
