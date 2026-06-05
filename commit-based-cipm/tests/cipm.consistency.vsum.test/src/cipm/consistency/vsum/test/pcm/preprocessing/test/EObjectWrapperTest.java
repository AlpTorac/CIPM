package cipm.consistency.vsum.test.pcm.preprocessing.test;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceFactoryImpl;

import cipm.consistency.cpr.pcmjava.preprocessing.EObjectDependencyTracker;
import cipm.consistency.cpr.pcmjava.preprocessing.UUIDAdjustingStrategy;
import cipm.consistency.vsum.test.pcm.cprunittests.ChangeComputer;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
import tools.vitruv.change.atomic.eobject.EobjectPackage;
import tools.vitruv.change.atomic.feature.FeaturePackage;
import tools.vitruv.change.atomic.feature.attribute.ReplaceSingleValuedEAttribute;
import tools.vitruv.change.atomic.root.InsertRootEObject;
import tools.vitruv.change.atomic.root.RemoveRootEObject;

public class EObjectWrapperTest {
	private static final ChangeComputer cc = new ChangeComputer();

	@BeforeAll
	public static void startUpBeforeAll() {
//		Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put("*", new RepositoryResourceFactoryImpl());
	}

	@Test
	public void a() {
		var wrapper = new EObjectDependencyTracker();
		wrapper.setIDAdjustingStrategy(new UUIDAdjustingStrategy());
		final var repo = RepositoryFactory.eINSTANCE.createRepository();
		var changes = cc.getEChangesFor(List.of(ChangePreprocessingTestModifications.addRootToResourceAction(repo),
				ChangePreprocessingTestModifications.removeRootFromResourceAction(repo)));

		Assertions.assertEquals(5, changes.size());
		changes.remove(changes.get(1));
		changes.remove(changes.get(1));
		changes.remove(changes.get(1));

		var createChange = (CreateEObject<?>) changes.get(0);
		var deleteChange = (DeleteEObject<?>) changes.get(1);

		wrapper.setInitialChange(changes, createChange,
				EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);

		Assertions.assertEquals(createChange.getAffectedEObjectType(), wrapper.getEobjectType());

		var expectedID = "cache:/0";

		Assertions.assertEquals(expectedID, wrapper.getIDInChange(createChange));
		Assertions.assertEquals(EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT,
				wrapper.getContainingFeatInChange(createChange));

		Assertions.assertEquals(expectedID, wrapper.getIDInChange(deleteChange));
		Assertions.assertEquals(EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT,
				wrapper.getContainingFeatInChange(deleteChange));
	}

	@Test
	public void b() {

		// TODO Check how change IDs actually are in PCM changes and adjust if needed

		/*
		 * TODO
		 * 
		 * If computed PCM changes do not utilise the UUIDs, mention it in the next
		 * meeting. Currently, the PCM changes computed here use the structural IDs,
		 * even though the UUIDs are present.
		 * 
		 * Tested whether UUIDs can actually be used in PCM changes and get resolved,
		 * turns out that they can. The following test code runs without failures:
		 * 
		 * var expectedIDAfterSetting = (String) replaceIDChange.getNewValue();
		 * 
		 * var copier = new EcoreUtil.Copier();
		 * 
		 * var change = (RemoveRootEObject<?>) copier.copy(removeRootChange);
		 * 
		 * copier.copyReferences();
		 * 
		 * change.setOldValueID(expectedIDAfterSetting);
		 * 
		 * ChangePreprocessingTestAssertions.assertChangeSequencesHaveSameEffect(
		 * changes, List.of(createChange, insertRootChange, replaceIDChange, change,
		 * deleteChange));
		 */

		var wrapper = new EObjectDependencyTracker();
		wrapper.setIDAdjustingStrategy(new UUIDAdjustingStrategy());
		final var repo = RepositoryFactory.eINSTANCE.createRepository();
		var changes = cc.getEChangesFor(List.of(ChangePreprocessingTestModifications.addRootToResourceAction(repo),
				ChangePreprocessingTestModifications.removeRootFromResourceAction(repo)));

		Assertions.assertEquals(5, changes.size());

		var createChange = (CreateEObject<?>) changes.get(0);
		var insertRootChange = (InsertRootEObject<?>) changes.get(1);
		var replaceIDChange = (ReplaceSingleValuedEAttribute<?, ?>) changes.get(2);
		var removeRootChange = (RemoveRootEObject<?>) changes.get(3);
		var deleteChange = (DeleteEObject<?>) changes.get(4);

		wrapper.setInitialChange(changes, createChange,
				EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);
		var expectedCacheID = "cache:/0";
		var expectedType = createChange.getAffectedEObjectType();
		var expectedRes = insertRootChange.getResource();
		var expectedResURI = insertRootChange.getUri();
		Assertions.assertEquals(expectedType, wrapper.getEobjectType());
		Assertions.assertEquals(expectedResURI, wrapper.getResourceUri());

		Assertions.assertEquals(expectedCacheID, wrapper.getIDInChange(createChange));
		Assertions.assertEquals(EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT,
				wrapper.getContainingFeatInChange(createChange));

		Assertions.assertEquals(expectedCacheID, wrapper.getIDInChange(insertRootChange));
		Assertions.assertEquals(EobjectPackage.Literals.EOBJECT_ADDED_ECHANGE__NEW_VALUE,
				wrapper.getContainingFeatInChange(insertRootChange));

		var expectedIDAfterInsert = expectedRes.getURI().appendFragment("/0").toString();

		Assertions.assertEquals(expectedIDAfterInsert, wrapper.getIDInChange(replaceIDChange));
		Assertions.assertEquals(FeaturePackage.Literals.FEATURE_ECHANGE__AFFECTED_EOBJECT,
				wrapper.getContainingFeatInChange(replaceIDChange));

		// FIXME Enable or clean once fixed
//		var expectedIDAfterSetting = (String) replaceIDChange.getNewValue();
//		Assertions.assertEquals(expectedIDAfterSetting, wrapper.getIDInChange(removeRootChange));

		Assertions.assertEquals(expectedIDAfterInsert, wrapper.getIDInChange(removeRootChange));
		Assertions.assertEquals(EobjectPackage.Literals.EOBJECT_SUBTRACTED_ECHANGE__OLD_VALUE,
				wrapper.getContainingFeatInChange(removeRootChange));

		Assertions.assertEquals(expectedCacheID, wrapper.getIDInChange(deleteChange));
		Assertions.assertEquals(EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT,
				wrapper.getContainingFeatInChange(deleteChange));
	}
}
