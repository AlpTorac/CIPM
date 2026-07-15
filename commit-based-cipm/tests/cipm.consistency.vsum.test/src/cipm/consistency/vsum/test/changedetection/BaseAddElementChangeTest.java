package cipm.consistency.vsum.test.changedetection;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tools.vitruv.change.atomic.EChange;

public class BaseAddElementChangeTest {
	@Test
	public void singleMatch() {
		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(URI.createFileURI(Path
				.of("pcmjava-testmodels", "datatype", "withMultipleExistingClassifiers", "oldPcmChanges.changes").toAbsolutePath().toString()));
		try {
			res.load(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
		var changes = new ArrayList<EChange>();
		res.getContents().forEach((c) -> changes.add((EChange) c));
		var matches = changes.stream().map((c) -> new BaseAddElementChangeMatcher(c, changes))
				.collect(Collectors.toList());
		var candidates = matches.stream().filter((m) -> m.match()).collect(Collectors.toList());
		Assertions.assertEquals(1, candidates.size());
	}
}
