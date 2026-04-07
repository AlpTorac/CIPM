package cipm.consistency.fluentapi.gen;

import java.util.Collections;

public class FluentAPIFillableTemplate implements IFluentAPIFillableTemplate {
	private final String template;

	public FluentAPIFillableTemplate(String template) {
		this.template = template;
	}

	@Override
	public String get() {
		return this.template;
	}

	@Override
	public String getFor(Object... params) {
		return String.format(this.template, params);
	}

	@Override
	public String getEmpty() {
		return getFor(Collections.nCopies(this.template.length(), "").toArray());
	}

}
