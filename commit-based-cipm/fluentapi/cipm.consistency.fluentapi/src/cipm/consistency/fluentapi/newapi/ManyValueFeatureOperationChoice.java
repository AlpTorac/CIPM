package cipm.consistency.fluentapi.newapi;

public class ManyValueFeatureOperationChoice<CC extends ClassChoice, FeatVal> {
	private FluentEMFApi<CC> api;

	public ManyValueFeatureOperationChoice(FluentEMFApi<CC> api) {
		this.api = api;
	}

	public ManyValueFeatureValueChoice<CC, FeatVal> with() {
		return null;
	}

	public FluentEMFApi<CC> without() {
		return api;
	}

	public ManyValueFeatureValueChoice<CC, FeatVal> withAdded() {
		return new ManyValueFeatureValueChoice<CC, FeatVal>(api);
	}

	public ManyValueFeatureValueChoice<CC, FeatVal> withRemoved() {
		return new ManyValueFeatureValueChoice<CC, FeatVal>(api);
	}

	public FluentEMFApi<CC> clean() {
		return api;
	}
}