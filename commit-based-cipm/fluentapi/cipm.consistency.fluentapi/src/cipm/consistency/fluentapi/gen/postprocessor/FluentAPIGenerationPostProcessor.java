package cipm.consistency.fluentapi.gen.postprocessor;

/**
 * An interface for classes that are meant to post-process the generated fluent
 * API model. Refer to the documentation of individual concrete implementors for
 * more information.
 * 
 * @author Alp Torac Genc
 */
public interface FluentAPIGenerationPostProcessor {
	public void apply();
}
