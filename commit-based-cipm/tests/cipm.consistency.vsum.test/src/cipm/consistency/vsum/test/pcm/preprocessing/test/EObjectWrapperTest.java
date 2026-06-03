package cipm.consistency.vsum.test.pcm.preprocessing.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

import cipm.consistency.cpr.pcmjava.preprocessing.EObjectWrapper;
import cipm.consistency.vsum.test.pcm.cprunittests.ChangeComputer;
import tools.vitruv.change.atomic.eobject.CreateEObject;
import tools.vitruv.change.atomic.eobject.DeleteEObject;
import tools.vitruv.change.atomic.eobject.EobjectPackage;

public class EObjectWrapperTest {
	private static final ChangeComputer cc = new ChangeComputer();

	@Test
	public void a() {
		var wrapper = new EObjectWrapper();
		final var repo = RepositoryFactory.eINSTANCE.createRepository();
		var changes = cc.getEChangesFor(List.of(ChangePreprocessingTestModifications.addRootToResourceAction(repo),
				ChangePreprocessingTestModifications.removeRootFromResourceAction(repo)));

		Assertions.assertEquals(5, changes.size());
		changes.remove(changes.get(1));
		changes.remove(changes.get(1));
		changes.remove(changes.get(1));
		Assertions.assertInstanceOf(CreateEObject.class, changes.get(0));
		Assertions.assertInstanceOf(DeleteEObject.class, changes.get(1));

		wrapper.setInitialChange(changes, changes.get(0),
				EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT);
		Assertions.assertSame(wrapper,
				changes.get(0).eGet(EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT));
		Assertions.assertSame(wrapper,
				changes.get(1).eGet(EobjectPackage.Literals.EOBJECT_EXISTENCE_ECHANGE__AFFECTED_EOBJECT));
		
		// TODO Test for other attributes of wrapper
	}
}
