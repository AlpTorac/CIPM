package cipm.consistency.fitests.similarity.jamopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.FeatureMapChange;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.postprocessor.IPostProcessor;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

import cipm.consistency.commitintegration.diff.util.JavaModelComparator;
import cipm.consistency.fitests.similarity.eobject.EcoreUtilHelper;

public class JaMoPPChangeDetectionTestGenerator {
	public Collection<DynamicTest> generateTestsFor(Notifier newState, Notifier currentState, Collection<Diff> expectedDiffs) {
		var tests = new ArrayList<DynamicTest>();

		final var lhsRhsDefCmp = this.compareModelsWithDefaultComparer(newState, currentState);
		final var rhsLhsDefCmp = this.compareModelsWithDefaultComparer(currentState, newState);

		tests.add(this.generateDifferenceSymmetryTest("defaultCompare(lhs, rhs) ~ defaultCompare(rhs, lhs)", lhsRhsDefCmp.getDifferences(), rhsLhsDefCmp.getDifferences()));

		final var lhsRhsJamoppCmp = this.compareModelsWithJaMoPPComparer(newState, currentState);
		final var rhsLhsJamoppCmp = this.compareModelsWithJaMoPPComparer(currentState, newState);

		tests.add(this.generateDifferenceSymmetryTest("jamoppCompare(lhs, rhs) ~ jamoppCompare(rhs, lhs)", lhsRhsJamoppCmp.getDifferences(), rhsLhsJamoppCmp.getDifferences()));

		// FIXME "Split" differences from default comparison into 2, such that one side only has left and the other one only has right
		
		// Use "splitToLeftAndRight"
		
		tests.add(DynamicTest.dynamicTest("jamoppCompare has all ReferenceChanges", () -> {
			var splitDiffs = new ArrayList<Diff>();
			for (var defDiff : lhsRhsDefCmp.getDifferences()) {
				splitDiffs.addAll(this.splitToLeftAndRight(defDiff));
			}
			for (var splitDefDiff : splitDiffs) {
				if (splitDefDiff instanceof ReferenceChange) {
					Assertions.assertTrue(lhsRhsJamoppCmp.getDifferences().stream().anyMatch((d) ->
							this.diffsEqual(d, splitDefDiff)));
				}
			}
		}));

		tests.add(DynamicTest.dynamicTest("jamoppCompare ~ expectedDiffs", () -> {
			var diffs = lhsRhsJamoppCmp.getDifferences();
			Assertions.assertEquals(expectedDiffs.size(), diffs.size());
			for (var ed : expectedDiffs) {
				Assertions.assertTrue(diffs.stream().anyMatch((d) -> this.diffsEqual(d, ed)));
			}
			
			for (var d : diffs) {
				Assertions.assertTrue(expectedDiffs.stream().anyMatch((ed) -> this.diffsEqual(ed, d)));
			}
		}));
		
		return tests;
	}

	private Collection<Diff> splitToLeftAndRight(Diff diff) {
		var helper = new EcoreUtilHelper();
		var diffMatch = diff.getMatch();
		var diffMatchBase = helper.cloneEObj(diffMatch);
		diffMatchBase.getDifferences().clear();
		
		var diffLeftMatch = helper.cloneEObj(diffMatchBase);
		var diffLeft = this.cloneDiff(diff);
		diffLeftMatch.setLeft(diffMatch.getLeft());
		diffLeftMatch.setRight(null);
		diffLeft.setMatch(diffLeftMatch);
		
		var diffRightMatch = helper.cloneEObj(diffMatchBase);
		var diffRight = this.cloneDiff(diff);
		diffRightMatch.setRight(diffMatch.getRight());
		diffRightMatch.setLeft(null);
		diffRight.setMatch(diffRightMatch);
		
		return new ArrayList<>() {{
			add(diffLeft);
			add(diffRight);
		}};
	}

	private <T extends Diff> T cloneDiff(T originalDiff) {
		return new EcoreUtilHelper().cloneEObj(originalDiff);
//		var fac = CompareFactory.eINSTANCE;
//		
//		@SuppressWarnings("unchecked")
//		var newDiff = (T) fac.create(originalDiff.eClass());
//		newDiff.setKind(originalDiff.getKind());
//		if (newDiff instanceof ReferenceChange) {
//			var castedOriginalDiff = (ReferenceChange) originalDiff;
//			var castedNewDiff = (ReferenceChange) newDiff;
//			castedNewDiff.setReference(castedOriginalDiff.getReference());
//			castedNewDiff.setValue(castedOriginalDiff.getValue());
//		}
//		else if (newDiff instanceof AttributeChange) {
//			var castedOriginalDiff = (AttributeChange) originalDiff;
//			var castedNewDiff = (AttributeChange) newDiff;
//			castedNewDiff.setAttribute(castedOriginalDiff.getAttribute());
//			castedNewDiff.setValue(castedOriginalDiff.getValue());
//		}
//		else if (newDiff instanceof FeatureMapChange) {
//			var castedOriginalDiff = (FeatureMapChange) originalDiff;
//			var castedNewDiff = (FeatureMapChange) newDiff;
//			castedNewDiff.setAttribute(castedOriginalDiff.getAttribute());
//			castedNewDiff.setValue(castedOriginalDiff.getValue());
//		}
//		return newDiff;
	}
	
	private boolean diffsEqual(Diff diff1, Diff diff2) {
		return  diff1.getClass().equals(diff2.getClass()) && 
				diff1.getKind().equals(diff2.getKind()) &&
				diff1.getMatch().getLeft() == diff2.getMatch().getLeft() &&
				diff1.getMatch().getRight() == diff2.getMatch().getRight() &&
				
				diff1 instanceof ReferenceChange &&
				(((ReferenceChange) diff1).getReference().equals(((ReferenceChange) diff2).getReference()));
	}

	private boolean diffsSymmetric(Diff diff1, Diff diff2) {
		return  diff1.getClass().equals(diff2.getClass()) && 
				diff1.getKind().equals(diff2.getKind()) &&
				diff1.getMatch().getLeft() == diff2.getMatch().getRight() &&
				diff1.getMatch().getRight() == diff2.getMatch().getLeft() &&
				
				diff1 instanceof ReferenceChange &&
				(((ReferenceChange) diff1).getReference().equals(((ReferenceChange) diff2).getReference()));
	}
	
	private DynamicTest generateDifferenceSymmetryTest(String testDisplay, Collection<Diff> lhsRhsDiffs, Collection<Diff> rhsLhsDiffs) {
		return DynamicTest.dynamicTest(testDisplay, () -> {
			Assertions.assertEquals(lhsRhsDiffs.size(), rhsLhsDiffs.size());

			for (var diff1 : lhsRhsDiffs) {
				Assertions.assertTrue(rhsLhsDiffs.stream()
						.anyMatch((diff2) ->this.diffsSymmetric(diff1, diff2)));
			}

			for (var diff2 : rhsLhsDiffs) {
				Assertions.assertTrue(lhsRhsDiffs.stream()
						.anyMatch((diff1) ->this.diffsSymmetric(diff2, diff1)));
			}
		});
	}

	protected Comparison compareModelsWithDefaultComparer(Notifier newState, Notifier currentState,
			List<Resource> newResources, List<Resource> currentResources, IPostProcessor postProcessor) {

		var scope = new DefaultComparisonScope(newState, currentState, null);

		var diffProcessor = new DiffBuilder();
		var diffEngine = new DefaultDiffEngine(diffProcessor);

		var builder = EMFCompare.builder().setDiffEngine(diffEngine);

		return builder.build().compare(scope);
	}

	protected Comparison compareModelsWithDefaultComparer(Notifier newState, Notifier currentState) {
		return this.compareModelsWithDefaultComparer(newState, currentState, null, null, null);
	}

	protected Comparison compareModelsWithJaMoPPComparer(Notifier newState, Notifier currentState,
			List<Resource> newResources, List<Resource> currentResources, IPostProcessor postProcessor) {
		return JavaModelComparator.compareJavaModels(newState, currentState, newResources, currentResources,
				postProcessor);
	}

	protected Comparison compareModelsWithJaMoPPComparer(Notifier newState, Notifier currentState) {
		return this.compareModelsWithJaMoPPComparer(newState, currentState, null, null, null);
	}

	protected void replayChanges(Comparison cmp) {
		var diffs = cmp.getDifferences();

		var mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		var merger = new BatchMerger(mergerRegistry);

		merger.copyAllLeftToRight(diffs, new BasicMonitor());
	}

	// TODO Implement assertion methods for model comparisons and use them in tests

	/*
	 * TODO Use DynamicNode (DynamicTest, DynamicContainer) and TestFactory in unit
	 * tests (and complex tests, if possible):
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
	 * Summary: Use the current tests as "generators" that create dynamic tests by
	 * using their EObject creation methods.
	 */
}
