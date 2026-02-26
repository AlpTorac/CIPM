package cipm.consistency.fluentapi.gen;

public class FluentAPIFixTemplate implements IFluentAPITemplate {
	private final String template;

	public FluentAPIFixTemplate(String template) {
		this.template = template;
	}

	@Override
	public String get() {
		return this.template;
	}
}
