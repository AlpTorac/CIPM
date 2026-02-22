package cipm.consistency.fluentapi.newapi;

public class SingleValueFeatureOperationChoice<Prev extends APIStep<Prev>, CC extends ClassChoice<Prev>, FeatVal> implements APIStep<Prev> {
	private final FluentEMFApi<CC> fluentEMFApi;

	public SingleValueFeatureOperationChoice(FluentEMFApi<CC> fluentEMFApi) {
		this.fluentEMFApi = fluentEMFApi;
	}

	public SingleValueFeatureValueChoice<Prev, CC, FeatVal> with() {
		return new SingleValueFeatureValueChoice<Prev, CC, FeatVal>(this.fluentEMFApi);
	}

	public FluentEMFApi<CC> without() {
		return fluentEMFApi;
	}
}