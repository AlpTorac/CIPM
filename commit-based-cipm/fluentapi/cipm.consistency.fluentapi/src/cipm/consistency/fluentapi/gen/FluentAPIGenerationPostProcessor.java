package cipm.consistency.fluentapi.gen;

public interface FluentAPIGenerationPostProcessor {
	/*
	 * TODO Extract array / collection overloads into a post processor
	 * 
	 * TODO Extract api.newX(theOnlyFeaturesValue) overloads into a post processor
	 * 
	 * TODO Extract simple delegation method generation (ex: superInit.drop()
	 * delegates to api.drop()) into a post processor
	 * 
	 * TODO Extract simple signature overriding method generation into a post
	 * processor (ex: init.newElement())
	 */
	public void apply(FluentAPIGenerationContext context);
}
