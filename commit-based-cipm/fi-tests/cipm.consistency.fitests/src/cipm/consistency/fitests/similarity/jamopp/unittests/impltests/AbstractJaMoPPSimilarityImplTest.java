package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.ReferenceChange;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPChangeDetectionTestGenerator;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPChangeReplayTestGenerator;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPSimilarityTestGenerator;

public abstract class AbstractJaMoPPSimilarityImplTest extends AbstractJaMoPPSimilarityTest {
	protected ReferenceChange createReferenceChange(EObject left, EObject right, DifferenceKind kind, EReference type) {
		var fac = CompareFactory.eINSTANCE;
		var match = fac.createMatch();
		match.setLeft(left);
		match.setRight(right);
		var diff = this.createReferenceChange(match, left, right, kind, type);
		return diff;
	}

	protected ReferenceChange createReferenceChange(Match match, EObject left, EObject right, DifferenceKind kind,
			EReference type) {
		var fac = CompareFactory.eINSTANCE;
		var diff = fac.createReferenceChange();
		diff.setMatch(match);
		diff.setKind(kind);
		diff.setReference(type);
		return diff;
	}

	public Collection<DynamicNode> generateTestsFor(EObject currentState, EObject newState, EReference changedAttrType,
			Collection<Diff> diffs) {
		var tests = new ArrayList<DynamicNode>();

		tests.add(DynamicContainer.dynamicContainer("Similarity checking tests",
				new JaMoPPSimilarityTestGenerator().generateTestsFor(currentState, newState, changedAttrType)));
		tests.add(DynamicContainer.dynamicContainer("Change detection tests",
				new JaMoPPChangeDetectionTestGenerator().generateTestsFor(newState, currentState, diffs)));

		var currentStateClone = this.cloneEObjWithContainers(currentState);
		var newStateClone = this.cloneEObjWithContainers(newState);

		tests.add(DynamicContainer.dynamicContainer("Change replay tests",
				new JaMoPPChangeReplayTestGenerator().generateTestsFor(newStateClone, currentStateClone)));

		return tests;
	}
}
