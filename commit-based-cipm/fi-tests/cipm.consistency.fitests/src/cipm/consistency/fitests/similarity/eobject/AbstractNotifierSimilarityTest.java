package cipm.consistency.fitests.similarity.eobject;

import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.postprocessor.IPostProcessor;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.AbstractSimilarityTest;

public abstract class AbstractNotifierSimilarityTest extends AbstractSimilarityTest {
	protected Comparison compareModels(Notifier newState, Notifier currentState, List<Resource> newResources,
			List<Resource> currentResources, IPostProcessor postProcessor) {
		
		// Comparing mechanism from JaMoPP, here as a reference
		
//		return JavaModelComparator.compareJavaModels(newState, currentState, newResources, currentResources,
//		postProcessor);
		
		// Use default comparing mechanisms to get attribute changes too
		
		var scope = new DefaultComparisonScope(newState, currentState, null);
		
		var diffProcessor = new DiffBuilder();
		var diffEngine = new DefaultDiffEngine(diffProcessor);
		
		var builder = EMFCompare.builder()
			.setDiffEngine(diffEngine);
		
		return builder.build().compare(scope);
	}

	protected Comparison compareModels(Notifier newState, Notifier currentState) {
		return this.compareModels(newState, currentState, null, null, null);
	}

	protected void replayChanges(Notifier newState, Notifier currentState, List<Resource> newResources,
			List<Resource> currentResources, IPostProcessor postProcessor) {
		var changes = this.compareModels(newState, currentState, newResources,
				currentResources, postProcessor);

		var diffs = changes.getDifferences();

		var mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		var merger = new BatchMerger(mergerRegistry);

		merger.copyAllLeftToRight(diffs, new BasicMonitor());
	}
	
	protected void replayChanges(Notifier newState, Notifier currentState) {
		this.replayChanges(newState, currentState, null, null, null);
	}
	
	// TODO Implement assertion methods for model comparisons and use them in tests
	
	/*
	 * TODO Use DynamicNode (DynamicTest, DynamicContainer) and TestFactory in
	 * unit tests (and complex tests, if possible):
	 * 
	 * 1) Write an interface for test methods, such as testSimilarity, that takes
	 * all test-relevant information via parameters.
	 * 
	 * 2) Extract all test methods from abstract test class for EObject and
	 * encapsulate them in dynamic tests.
	 * 
	 * 3) Use loops instead of parameterized tests and structure the tests using
	 * dynamic nodes.
	 * 
	 * This way, adding new tests becomes much easier with much better structure.
	 * 
	 * Summary: Use the current tests as "generators" that create dynamic tests
	 * by using their EObject creation methods.
	 */
}
