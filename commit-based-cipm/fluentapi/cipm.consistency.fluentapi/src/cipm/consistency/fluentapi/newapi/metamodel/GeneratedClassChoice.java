package cipm.consistency.fluentapi.newapi.metamodel;

import cipm.consistency.fluentapi.newapi.APIStep;
import cipm.consistency.fluentapi.newapi.ClassChoice;
import cipm.consistency.fluentapi.newapi.FluentEMFApi;

public class GeneratedClassChoice<Prev extends APIStep<Prev>> extends ClassChoice<Prev, GeneratedClassChoice<Prev>> {
	public GeneratedClassChoice(FluentEMFApi<GeneratedClassChoice<Prev>> api) {
		super(api);
	}

	public GeneratedClassFeatureChoice<Prev> classObj() {
		return new GeneratedClassFeatureChoice<Prev>();
	}
}
