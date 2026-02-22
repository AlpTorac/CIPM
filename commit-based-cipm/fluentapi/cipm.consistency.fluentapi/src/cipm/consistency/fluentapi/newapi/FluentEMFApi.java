package cipm.consistency.fluentapi.newapi;

public class FluentEMFApi<CC extends ClassChoice> {
	private CC cc;

	public FluentEMFApi(CC cc) {
		this.cc = cc;
	}

	public FluentEMFApi<CC> mark() {
		return this;
	}

	public FluentEMFApi<CC> unmark() {
		return this;
	}

	public Object end() {
		return null;
	}

	public FluentEMFApi<CC> onceExists() {
		return this;
	}

	public CC makeMinimalObj() {
		return cc;
	}

	public CC makeObj() {
		return cc;
	}

	public CC modify() {
		return cc;
	}

	public CC getMarked() {
		return cc;
	}
}
