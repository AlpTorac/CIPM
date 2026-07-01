package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.cpr.pcmjava.userinteraction.AbstractUserInteraction;
import cipm.consistency.cpr.pcmjava.userinteraction.ConflictResolutionStrategy;
import cipm.consistency.cpr.pcmjava.userinteraction.CorrespondenceEntry;
import cipm.consistency.cpr.pcmjava.userinteraction.JavaCorrespondentDecisionUserInteraction;
import tools.vitruv.change.correspondence.Correspondence;
import tools.vitruv.change.correspondence.Correspondences;

/**
 * A strategy for looking up correspondences between PCM and Java model elements
 * and providing them to user interactions asking for them.
 * 
 * <p>
 * This strategy is not realistic and is only used to fully automate the PCM to
 * Java change propagation tests.
 * 
 * @author Alp Torac Genc
 */
public class CorrespondenceConflictResolutionStrategy extends ConflictResolutionStrategy {
	private static final Logger LOGGER = Logger.getLogger(CorrespondenceConflictResolutionStrategy.class);

	private Resource propagatedJavaRes;
	private final List<Correspondence> corList = new ArrayList<>();

	public CorrespondenceConflictResolutionStrategy(Resource targetCorRes, Resource propagatedJavaRes) {
		this.propagatedJavaRes = propagatedJavaRes;
		corList.addAll(((Correspondences) targetCorRes.getContents().get(0)).getCorrespondences());
	}

	@Override
	protected void applyStrategy(AbstractUserInteraction userInteraction) {
		var castedUI = (JavaCorrespondentDecisionUserInteraction) userInteraction;

		for (var pcmElem : castedUI.getTriggeringPCMelements()) {
			var pcmURIFragment = pcmElem.eResource().getURIFragment(pcmElem);
			LOGGER.info(String.format("Seeking Java correspondent of %s (uri fragment: %s)", pcmElem, pcmURIFragment));
			for (var cor : corList) {
				var lhsObj = cor.getLeftEObjects().get(0);
				var lhsObjFragment = ((InternalEObject) lhsObj).eProxyURI().fragment();

				var rhsObj = cor.getRightEObjects().get(0);
				var rhsObjFragment = ((InternalEObject) rhsObj).eProxyURI().fragment();

				EObject javaElem = null;

				if (pcmURIFragment.equals(lhsObjFragment)) {
					javaElem = propagatedJavaRes.getEObject(rhsObjFragment);
				} else if (pcmURIFragment.equals(rhsObjFragment)) {
					javaElem = propagatedJavaRes.getEObject(lhsObjFragment);
				}

				if (javaElem != null) {
					LOGGER.info(String.format("Reporting Java correspondent %s for %s", javaElem, pcmElem));
					reportDesiredCorrespondence(userInteraction, new CorrespondenceEntry(pcmElem, javaElem, ""));
					break;
				}
			}
		}
	}

	@Override
	protected boolean checkInternalApplicationConditions(AbstractUserInteraction userInteraction) {
		return userInteraction instanceof JavaCorrespondentDecisionUserInteraction;
	}
}
