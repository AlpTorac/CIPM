package cipm.consistency.vsum.test.henshin;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import tools.vitruv.change.atomic.AtomicPackage;
import tools.vitruv.change.atomic.eobject.EobjectPackage;
import tools.vitruv.change.atomic.feature.attribute.AttributePackage;
import tools.vitruv.change.atomic.feature.reference.ReferencePackage;

public class HenshinTest3 {
	/** 
	 * Relative path to the bank model files.
	 */
	public static final String PATH = "propEx";
	
	/**
	 * Run the bank example.
	 * @param path Relative path to the model files.
	 * @param saveResult Whether the result should be saved.
	 */
	public static void run(String path, boolean saveResult) {
		// Create a resource set with a base directory:
		HenshinResourceSet resourceSet = new HenshinResourceSet(path);
		resourceSet.getPackageRegistry().put(AtomicPackage.eINSTANCE.getNsURI(), AtomicPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(EobjectPackage.eINSTANCE.getNsURI(), EobjectPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AttributePackage.eINSTANCE.getNsURI(), AttributePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ReferencePackage.eINSTANCE.getNsURI(), ReferencePackage.eINSTANCE);
		
		// Load the module:
		Module module = resourceSet.getModule("test.henshin", false);

		// Load the example model into an EGraph:
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("changes", new XMIResourceFactoryImpl());
		EGraph graph = new EGraphImpl(resourceSet.getResource("pcmChanges.changes"));
		
		// Create an engine and a rule application:
		Engine engine = new EngineImpl();
		var unit = (Rule) module.getUnit("test");
		var matches = engine.findMatches(unit, graph, null);
		for (var m : matches) {
			UnitApplication unitApp = new UnitApplicationImpl(engine);
			unitApp.setEGraph(graph);
			unitApp.setUnit(unit);
			unitApp.setAssignment(m);
			if (!unitApp.execute(null)) {
				throw new RuntimeException("Error executing test rule");
			}
		}
		
		// Saving the result:
		if (saveResult) {
			resourceSet.saveEObject(graph.getRoots().get(0), "test-result.xmi");
		}
	}
	
	public static void main(String[] args) {
		run(PATH, true); // we assume the working directory is the root of the examples plug-in
	}
}
