package cipm.consistency.fitests.similarity.java.unittests.complextests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

public class MemberInContainer extends EObjectSimilarityTest {
	private static List<IMemberInitialiser> getAllMemberInitInstances() {
		var res = new ArrayList<IMemberInitialiser>();
		var inits = new InitialiserParameters().getAdaptedInitialisersBySuper(IMemberInitialiser.class);
		inits.forEach((i) -> res.add(((IMemberInitialiser) i)));
		return res;
	}

	private static List<IMemberContainerInitialiser> getAllMemberContainerInitInstances() {
		var res = new ArrayList<IMemberContainerInitialiser>();
		var inits = new InitialiserParameters().getAdaptedInitialisersBySuper(IMemberContainerInitialiser.class);
		inits.forEach((i) -> res.add(((IMemberContainerInitialiser) i)));
		return res;
	}

	private static Stream<Arguments> getMemConMemPairs() {
		var res = new ArrayList<Arguments>();

		for (var memConInit1 : getAllMemberContainerInitInstances()) {
			for (var memInit1 : getAllMemberInitInstances()) {
				for (var memConInit2 : getAllMemberContainerInitInstances()) {
					for (var memInit2 : getAllMemberInitInstances()) {
						var displayName = "(" + memConInit1.getClass().getSimpleName() + ", "
								+ memInit1.getClass().getSimpleName() + ") - (" + memConInit2.getClass().getSimpleName()
								+ ", " + memInit2.getClass().getSimpleName() + ")";
						res.add(Arguments.of(displayName, memConInit1, memConInit2, memInit1, memInit2));
					}
				}
			}
		}

		return res.stream();
	}

	// TODO: Address the failures found here
	
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testMembersInContainers(String displayName, IMemberContainerInitialiser memConInit1,
			IMemberContainerInitialiser memConInit2, IMemberInitialiser memInit1, IMemberInitialiser memInit2) {
		this.setResourceFileTestIdentifier("testMembersInContainers");

		var memCon1 = memConInit1.instantiate();
		Assertions.assertTrue(memConInit1.initialise(memCon1));
		var mem1 = memInit1.instantiate();
		Assertions.assertTrue(memInit1.initialise(mem1));
		Assertions.assertTrue(memConInit1.addMember(memCon1, mem1));

		var memCon2 = memConInit2.instantiate();
		Assertions.assertTrue(memConInit2.initialise(memCon2));
		var mem2 = memInit2.instantiate();
		Assertions.assertTrue(memInit2.initialise(mem2));
		Assertions.assertTrue(memConInit2.addMember(memCon2, mem2));

		var expectedResult = memCon1.getClass().equals(memCon2.getClass()) && mem1.getClass().equals(mem2.getClass());

		this.assertSimilarityResult(memCon1, memCon2, expectedResult);
	}
}
