package cipm.consistency.fluentapi.newapi;

public class SingleValueFeatureValueChoice<Prev extends APIStep<Prev>, CC extends ClassChoice<Prev>, FeatVal> implements APIStep<Prev> {
	private final FluentEMFApi<CC> fluentEMFApi;

	public SingleValueFeatureValueChoice(FluentEMFApi<CC> fluentEMFApi) {
		this.fluentEMFApi = fluentEMFApi;
	}

	public FluentEMFApi<CC> use(FeatVal featVal) {
		return fluentEMFApi;
	}

	public FluentEMFApi<CC> use(Object adapter, FeatVal featVal) {
		return fluentEMFApi;
	}
}