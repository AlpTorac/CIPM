package cipm.consistency.fluentapi.gen;

import java.util.Map;

public interface IFluentAPIMethodGenerator {
	/*
	 * TODO Introduce a FluentAPIGenerationContext class and pass it to all
	 * generators. That way, referencing already generated model elements gets
	 * easier and tidier.
	 * 
	 * TODO Add further generator interfaces (if feasible) and tidy up the
	 * generation process.
	 */

	public Map<String, String> getMethodNamesToDescriptions();
}
