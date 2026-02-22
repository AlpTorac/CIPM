package cipm.consistency.fluentapi.newapi;

import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.newapi.metamodel.GeneratedClassChoice;

public class FluentEMFApiTest {
	@Test
	public void test() {
		var cc = new GeneratedClassChoice();
		var api = new FluentEMFApi<>(cc);
		api.makeMinimalObj().classObj().nameFeature().with().use("name");
		
	}
}
