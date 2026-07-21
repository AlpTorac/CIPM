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

public abstract class AbstractJavaCompositeChangeTest extends AbstractCompositeChangeTest {
	protected static final Path JAVA_CHANGE_RESOURCE = Path.of("changes", "javaChanges.changes");
	
	protected static final Path TEAMMATES_1_JAVA_PATH = ROOT.resolve("TEAMMATESCITest-1-6484257").resolve(JAVA_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_2_JAVA_PATH = ROOT.resolve("TEAMMATESCITest-2-48b67ba").resolve(JAVA_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_3_JAVA_PATH = ROOT.resolve("TEAMMATESCITest-3-83f518e").resolve(JAVA_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_4_JAVA_PATH = ROOT.resolve("TEAMMATESCITest-4-f33d0bc").resolve(JAVA_CHANGE_RESOURCE);
	protected static final Path TEAMMATES_5_JAVA_PATH = ROOT.resolve("TEAMMATESCITest-5-ce4463a").resolve(JAVA_CHANGE_RESOURCE);

	protected static final Resource JAVA_1_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_1_JAVA_PATH.toAbsolutePath().toString()));
	protected static final Resource JAVA_2_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_2_JAVA_PATH.toAbsolutePath().toString()));
	protected static final Resource JAVA_3_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_3_JAVA_PATH.toAbsolutePath().toString()));
	protected static final Resource JAVA_4_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_4_JAVA_PATH.toAbsolutePath().toString()));
	protected static final Resource JAVA_5_CHANGE_RESOURCE = resSet
			.createResource(URI.createFileURI(TEAMMATES_5_JAVA_PATH.toAbsolutePath().toString()));

	protected static final List<List<EChange>> JAVA_CHANGES = new ArrayList<>();
	
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
		
		for (var res : List.of(JAVA_1_CHANGE_RESOURCE, JAVA_2_CHANGE_RESOURCE, JAVA_3_CHANGE_RESOURCE,
				JAVA_4_CHANGE_RESOURCE, JAVA_5_CHANGE_RESOURCE)) {
			JAVA_CHANGES.add(getChanges(res));
		}
	}
	
	@Test
	public void teammatesMatchTest() {
		for (int i = 0; i < JAVA_CHANGES.size(); i++) {
			var changes = JAVA_CHANGES.get(i);
			LOGGER.debug("Teammates test: " + i);
			this.processChanges(changes);
		}
	}
}
