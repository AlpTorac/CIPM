package cipm.consistency.fluentapi.newapi;

public abstract class ClassChoice<Prev extends APIStep<Prev>, CC extends ClassChoice<Prev, CC>> implements APIStep<Prev> {
	private FluentEMFApi<CC> api;
	public ClassChoice(FluentEMFApi<CC> api) {
		this.api = api;
	}

	public FluentEMFApi<CC> getAPI() {
		return this.api;
	}
}
