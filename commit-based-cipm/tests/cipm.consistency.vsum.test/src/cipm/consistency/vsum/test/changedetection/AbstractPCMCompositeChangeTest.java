package cipm.consistency.vsum.test.changedetection;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tools.vitruv.change.atomic.EChange;

public abstract class AbstractPCMCompositeChangeTest extends AbstractCompositeChangeTest {
	protected static final Path PCM_CHANGE_RESOURCE = Path.of("changes", "pcmChanges.changes");

	protected static final Path TEAMMATES_1_PCM_PATH = ROOT.resolve("TEAMMATESCITest-1-6484257")
			.resolve(PCM_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_2_PCM_PATH = ROOT.resolve("TEAMMATESCITest-2-48b67ba")
			.resolve(PCM_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_3_PCM_PATH = ROOT.resolve("TEAMMATESCITest-3-83f518e")
			.resolve(PCM_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_4_PCM_PATH = ROOT.resolve("TEAMMATESCITest-4-f33d0bc")
			.resolve(PCM_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_5_PCM_PATH = ROOT.resolve("TEAMMATESCITest-5-ce4463a")
			.resolve(PCM_CHANGE_RESOURCE);

	protected static final Resource PCM_1_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_1_PCM_PATH.toAbsolutePath().toString()));
	protected static final Resource PCM_2_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_2_PCM_PATH.toAbsolutePath().toString()));
	protected static final Resource PCM_3_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_3_PCM_PATH.toAbsolutePath().toString()));
	protected static final Resource PCM_4_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_4_PCM_PATH.toAbsolutePath().toString()));
	protected static final Resource PCM_5_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_5_PCM_PATH.toAbsolutePath().toString()));

	protected static final List<List<EChange>> PCM_CHANGES = new ArrayList<>();

	private static List<EChange> getChanges(Resource res) {
		return res.getContents().stream().filter((c) -> c instanceof EChange).map((c) -> (EChange) c)
				.collect(Collectors.toList());
	}

	@BeforeAll
	public static void setUpBeforeAll() {
		for (var res : resSet.getResources()) {
			if (!res.isLoaded()) {
				try {
					res.load(null);
				} catch (IOException e) {
					e.printStackTrace();
					Assertions.fail(e);
				}

			}
		}

		for (var res : List.of(PCM_1_CHANGE_RESOURCE, PCM_2_CHANGE_RESOURCE, PCM_3_CHANGE_RESOURCE,
				PCM_4_CHANGE_RESOURCE, PCM_5_CHANGE_RESOURCE)) {
			PCM_CHANGES.add(getChanges(res));
		}
	}

	@Test
	public void teammatesMatchTest() {
		for (int i = 0; i < PCM_CHANGES.size(); i++) {
			var changes = PCM_CHANGES.get(i);
			LOGGER.debug("Teammates test: " + i);
			this.processChanges(changes);
		}
	}
}
