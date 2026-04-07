package cipm.consistency.fluentapi.gen.postprocessor;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;

/**
 * An interface for classes that are meant to post-process the generated fluent
 * API model. Refer to the documentation of individual concrete implementors for
 * more information.
 * 
 * @author Alp Torac Genc
 */
public interface FluentAPIGenerationPostProcessor {
	/**
	 * Applies this post-processor to relevant contents of the given
	 * {@link FluentAPIGenerationContext}
	 */
	public void apply(FluentAPIGenerationContext context);
}
