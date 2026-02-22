package cipm.consistency.fluentapi.newapi;

import java.util.Collection;

public class ManyValueFeatureValueChoice<CC extends ClassChoice, FeatVal> {
	private final FluentEMFApi<CC> fluentEMFApi;

	public ManyValueFeatureValueChoice(FluentEMFApi<CC> fluentEMFApi) {
		this.fluentEMFApi = fluentEMFApi;
	}

	public FluentEMFApi<CC> use(FeatVal featVal) {
		return fluentEMFApi;
	}

	public FluentEMFApi<CC> use(FeatVal[] featVal) {
		return fluentEMFApi;
	}

	public FluentEMFApi<CC> use(Collection<? extends FeatVal> featVal) {
		return fluentEMFApi;
	}
}