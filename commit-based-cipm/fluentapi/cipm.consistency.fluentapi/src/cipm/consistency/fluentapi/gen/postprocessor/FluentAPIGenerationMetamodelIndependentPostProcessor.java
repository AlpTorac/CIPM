package cipm.consistency.fluentapi.gen.postprocessor;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;

public class FluentAPIGenerationMetamodelIndependentPostProcessor implements FluentAPIGenerationPostProcessor {
	@Override
	public void apply(FluentAPIGenerationContext context) {
		new FluentAPIGenerationBigNumberParameterPostProcessor().apply(context);
		new FluentAPIGenerationForEachOverloadPostProcessor().apply(context);
		new FluentAPIGenerationSameMethodOverloadPostProcessor().apply(context);
	}
}
