package cipm.consistency.initialisers.jamopp;

import java.util.Collection;
import java.util.Set;

import cipm.consistency.initialisers.eobject.IEObjectUtilityTest;

public interface IJaMoPPUtilityTest extends IEObjectUtilityTest {
	/**
	 * {@link JaMoPPHelper#getAllPossibleTypes()}
	 */
	public default Set<Class<?>> getAllPossibleJaMoPPEObjectTypes() {
		return JaMoPPHelper.getAllPossibleTypes();
	}

	/**
	 * {@link JaMoPPHelper#getAllInitialiserCandidates()}
	 */
	public default Collection<Class<?>> getAllInitialiserCandidates() {
		return JaMoPPHelper.getAllInitialiserCandidates();
	}

	/**
	 * {@link JaMoPPHelper#getAllConcreteInitialiserCandidates()}
	 */
	public default Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		return JaMoPPHelper.getAllConcreteInitialiserCandidates();
	}
}
