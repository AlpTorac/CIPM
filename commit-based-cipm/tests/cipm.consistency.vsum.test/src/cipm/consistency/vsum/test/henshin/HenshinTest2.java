package cipm.consistency.vsum.test.henshin;

import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

public class HenshinTest2 {
	/** 
	 * Relative path to the bank model files.
	 */
	public static final String PATH = "henshinEx";
	
	/**
	 * Run the bank example.
	 * @param path Relative path to the model files.
	 * @param saveResult Whether the result should be saved.
	 */
	public static void run(String path, boolean saveResult) {
		
		// Create a resource set with a base directory:
		HenshinResourceSet resourceSet = new HenshinResourceSet(path);
		
		// Load the module:
		Module module = resourceSet.getModule("bank2.henshin", false);

		// Load the example model into an EGraph:
		EGraph graph = new EGraphImpl(resourceSet.getResource("example-bank.xmi"));
		
		// Create an engine and a rule application:
		Engine engine = new EngineImpl();
		var unit = (Rule) module.getUnit("addBonus");
		var matches = engine.findMatches(unit, graph, null);
		for (var m : matches) {
			UnitApplication unitApp = new UnitApplicationImpl(engine);
			unitApp.setEGraph(graph);
			unitApp.setUnit(unit);
			unitApp.setAssignment(m);
			if (!unitApp.execute(null)) {
				throw new RuntimeException("Error executing addBonus");
			}
		}
		
		// Saving the result:
		if (saveResult) {
			resourceSet.saveEObject(graph.getRoots().get(0), "example-result.xmi");
		}
	}
	
	public static void main(String[] args) {
		run(PATH, true); // we assume the working directory is the root of the examples plug-in
	}
}
