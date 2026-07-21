package cipm.consistency.vsum.test.changedetection;

import java.util.List;
import java.util.stream.Collectors;

import tools.vitruv.change.atomic.EChange;

public class AddTypePCMChangeTest extends AbstractPCMCompositeChangeTest {
	protected List<ICompositeChangeMatcher> getMatches(List<EChange> changes) {
		List<ICompositeChangeMatcher> matches = changes.stream().map((c) -> new AddTypeMatcher(c, changes))
				.collect(Collectors.toList());
		return matches;
	}
}
