package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.Collection;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.base.JavaSimilarityCheckerContainer;

public class JaMoPPChangeReplayTestGenerator {
	private ISimilarityCheckerContainer simCon = new JavaSimilarityCheckerContainer();
	
	public Collection<DynamicTest> generateTestsFor(Notifier newState, Notifier currentState) {
		var tests = new ArrayList<DynamicTest>();
		
		tests.add(DynamicTest.dynamicTest("Current state + changes = new state", () -> {
				this.replayChanges(newState, currentState);
				Assertions.assertEquals(0, this.compareModels(newState, currentState).getDifferences().size());
				Assertions.assertTrue(this.simCon.isSimilar(newState, currentState));
		}));
		
		return tests;
	}

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
}
