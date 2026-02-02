package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public abstract class AbstractFluentAPITest {
	// TODO Extract helpful testing methods
	@BeforeEach
	public void setUp() {
		FluentAPIMarkExtension.clearAllMarks();
		FluentAPIOnceExistsExtension.clearAllOnceExists();
		FluentAPIInitialisationStorage.clear();
	}
}
