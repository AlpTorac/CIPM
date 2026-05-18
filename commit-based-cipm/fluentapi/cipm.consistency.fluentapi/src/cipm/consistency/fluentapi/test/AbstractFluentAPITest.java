package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.fluentapi.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.methods.mark.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.methods.mark.FluentAPIWaitForMarkExtension;

public abstract class AbstractFluentAPITest {
	@BeforeEach
	public void setUp() {
		FluentAPIMarkExtension.clearAllMarks();
		FluentAPIWaitForMarkExtension.clearAllTasks();
		FluentAPIInitialisationStorage.clearAllOngoingInitialisations();
	}
}
