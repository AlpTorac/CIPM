package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import cipm.consistency.cpr.pcmjava.userinteraction.ConflictResolutionStrategy;

/**
 * A class that contains the means to produce {@link ConflictResolutionStrategy}
 * (CRS) instances, in order to automate certain user interactions in CPR tests.
 * <p>
 * Since each CPR test creates and maintains its own Resources via
 * {@link PCMJavaTestResourceWrapper}, this class internally uses
 * {@link Supplier}s as CRS factories that make use of the {@link #getWrapper()}
 * method to get access to those Resource instances.
 * 
 * @author Alp Torac Genc
 */
public class CRSConfig {
	private PCMJavaTestResourceWrapper wrapper;
	private List<Supplier<ConflictResolutionStrategy>> crsFacs = new ArrayList<>();

	public void setCRSFacs(List<Supplier<ConflictResolutionStrategy>> crsFacs) {
		this.crsFacs = crsFacs;
	}

	public void setWrapper(PCMJavaTestResourceWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public List<Supplier<ConflictResolutionStrategy>> getCRSFacs() {
		return this.crsFacs;
	}

	public PCMJavaTestResourceWrapper getWrapper() {
		return this.wrapper;
	}

	public List<ConflictResolutionStrategy> getCRSs() {
		return this.crsFacs.stream().map((s) -> s.get()).collect(Collectors.toList());
	}
}
