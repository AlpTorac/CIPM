package cipm.consistency.vsum.test.changedetection;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.vsum.test.appspace.LoggingSetup;
import tools.vitruv.change.atomic.EChange;

public abstract class AbstractCompositeChangeTest {
	protected static Logger LOGGER = Logger.getLogger(AbstractCompositeChangeTest.class);

	protected static final Path ROOT = Path.of("target");
	
	protected static final ResourceSet resSet = new ResourceSetImpl();

	@BeforeEach
	public void setUp() {
		LOGGER = Logger.getLogger(this.getClass());
		LoggingSetup.setMinLogLevel(Level.DEBUG);
	}

	protected void processChanges(List<EChange> changes) {
		LOGGER.debug("Change count: " + changes.size());
		var matches = getMatches(changes);
		var candidates = matches.stream().filter((m) -> m.match()).collect(Collectors.toList());
		LOGGER.debug("Composite change candidate count: " + candidates.size());
		LOGGER.debug("Composite change candidate count / atomic change count: "
				+ ((double) candidates.size()) / ((double) changes.size()));
	}
	
	protected abstract List<ICompositeChangeMatcher> getMatches(List<EChange> changes);
}
