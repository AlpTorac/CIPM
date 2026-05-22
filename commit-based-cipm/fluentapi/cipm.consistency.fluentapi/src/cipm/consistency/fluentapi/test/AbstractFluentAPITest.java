package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.fluentapi.extensions.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.extensions.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.extensions.FluentAPIWaitForMarkExtension;

public abstract class AbstractFluentAPITest {
	@BeforeEach
	public void setUp() {
		FluentAPIMarkExtension.clearAllMarks();
		FluentAPIWaitForMarkExtension.clearAllTasks();
		FluentAPIInitialisationStorage.clearAllOngoingInitialisations();
	}
}
