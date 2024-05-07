package cipm.consistency.fitests.similarity.java.unittests.impltests;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.ExportsModuleDirective;
import org.emftext.language.java.modules.ModuleDirective;
import org.emftext.language.java.modules.OpensModuleDirective;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.IPackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.OpenInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ExportsModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.IExportsModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.IOpensModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.OpensModuleDirectiveInitialiser;

public class ModuleSimilarityTest extends EObjectSimilarityTest {
	private IModuleInitialiser moduleInitialiser;
	private IPackageInitialiser pacInit;
	
	private List<Package> pacList;
	
	private IExportsModuleDirectiveInitialiser md1Init;
	private ExportsModuleDirective md1;
	
	private IOpensModuleDirectiveInitialiser md2Init;
	private OpensModuleDirective md2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ModuleSimilarityTest.class.getSimpleName());
		
		this.moduleInitialiser = new ModuleInitialiser();
		this.pacInit = new PackageInitialiser();
		
		this.pacList = new ArrayList<Package>();
		
		var pacNss = new String[][] {
			new String[] {"ns1", "ns2", "ns3"},
			new String[] {"ns1", "ns2"},
			new String[] {"ns4", "ns5"},
			new String[] {"ns1"}
		};
		
		for (var pacNs : pacNss) {
			Package pac = pacInit.instantiate();
			pacInit.minimalInitialisation(pac);
			pacInit.initialiseNamespaces(pac, pacNs);
			
			this.pacList.add(pac);
		}
		
		md1Init = new ExportsModuleDirectiveInitialiser();
		md1 = md1Init.instantiate();
		md1Init.minimalInitialisation(md1);
		md1Init.setAccessablePackage(md1, this.getPacAt(0));
		
		md2Init = new OpensModuleDirectiveInitialiser();
		md2 = md2Init.instantiate();
		md2Init.minimalInitialisation(md2);
		md2Init.setAccessablePackage(md2, this.getPacAt(1));
		
		super.setUp();
	}
	
	protected Package getPacAt(int index) {
		// Clone to make sure that packages are not moved around
		// due to their EObject nature
		return this.pacInit.clone(this.pacList.get(index));
	}
	
	protected Module initElement(IModuleInitialiser initialiser, Package[] pacs, ModuleDirective[] targets, boolean isOpen) {
		Module result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		
		if (pacs != null) {
			for (var pac : pacs) {
				initialiser.addPackage(result, pac);
			}
		}
		
		if (targets != null) {
			for (var t : targets) {
				initialiser.addTarget(result, t);
			}
		}
		
		if (isOpen) {
			initialiser.initialiseOpen(result,
					new OpenInitialiser().instantiate());
		}
		
		return result;
	}
	
	@Test
	public void testSameOpen() {
		this.setResourceFileTestIdentifier("testSameOpen");
		
		var objOne = this.initElement(moduleInitialiser, null, null, true);
		var objTwo = this.initElement(moduleInitialiser, null, null, false);
		
		this.sameX(objOne, moduleInitialiser);
		this.sameX(objTwo, moduleInitialiser);
	}
	
	@Test
	public void testDifferentOpen() {
		this.setResourceFileTestIdentifier("testSameOpen");
		
		var objOne = this.initElement(moduleInitialiser, null, null, true);
		var objTwo = this.initElement(moduleInitialiser, null, null, false);
		
		this.differentX(objOne, objTwo);
	}
	
	@Test
	public void testSamePackages() {
		this.setResourceFileTestIdentifier("testSamePackages");
		
		var objOne = this.initElement(moduleInitialiser, new Package[] {
				this.getPacAt(0), this.getPacAt(1)
		}, null, false);
		
		this.sameX(objOne, moduleInitialiser);
	}
	
	@Test
	public void testDifferentPackages() {
		this.setResourceFileTestIdentifier("testDifferentPackages");
		
		var objOne = this.initElement(moduleInitialiser, new Package[] {
				this.getPacAt(0), this.getPacAt(1)
		}, null, false);
		var objTwo = this.initElement(moduleInitialiser, new Package[] {
				this.getPacAt(2), this.getPacAt(3)
		}, null, false);
		
		this.differentX(objOne, objTwo);
	}
	
	public void testSameModuleDirectives() {
		this.setResourceFileTestIdentifier("testSameModuleDirectives");
		
		var objOne = this.initElement(moduleInitialiser, null, new ModuleDirective[] {
				this.md1Init.clone(this.md1),
				this.md2Init.clone(this.md2)
		}, false);
		
		this.sameX(objOne, moduleInitialiser);
	}
	
	public void testDifferentModuleDirectives() {
		this.setResourceFileTestIdentifier("testSameModuleDirectives");
		
		var objOne = this.initElement(moduleInitialiser, null, new ModuleDirective[] {
				this.md2Init.clone(this.md2)
		}, false);
		var objTwo = this.initElement(moduleInitialiser, null, new ModuleDirective[] {
				this.md1Init.clone(this.md1)
		}, false);
		
		this.differentX(objOne, objTwo);
	}
}
