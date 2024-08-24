package cipm.consistency.fitests.similarity.java.unittests.complextests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.params.InitialiserParameters;

/**
 * Tests whether {@link MemberContainer} implementors' similarity is computed as
 * expected, if they contain different types of {@link Member} instances.
 * {@link Member} instances are added as members to {@link MemberContainer}s in
 * some tests and as default members in tests in others. <br>
 * <br>
 * There are differences between this test class and the
 * {@link cipm.consistency.fitests.similarity.java.unittests.interfacetests.MemberContainerTest}.
 * This test class checks the similarity of 2 {@link MemberContainer} instances
 * of the same type but with varying {@link Member} instances. The latter only
 * tests the similarity of {@link MemberContainer} instances of the same type
 * with the same {@link Member} instances.<br>
 * <br>
 * <b>This test class is overshadowed by neither
 * {@link cipm.consistency.fitests.similarity.java.unittests.impltests} nor
 * {@link cipm.consistency.fitests.similarity.java.unittests.interfacetests},
 * because the type of the {@link MemberContainer} containing a certain
 * {@link Member} can indirectly influence the similarity checking result of
 * both {@link MemberContainer} instances and {@link Member} instances (via
 * qualified name differences for instance).</b>
 * 
 * @author atora
 */
public class MemberInContainerTest extends EObjectSimilarityTest {
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

		for (var memConInit : getAllMemberContainerInitInstances()) {
			for (var memInit1 : getAllMemberInitInstances()) {
				for (var memInit2 : getAllMemberInitInstances()) {
					var displayName = memInit1.getClass().getSimpleName() + " - " + memInit2.getClass().getSimpleName()
							+ " in " + memConInit.getClass().getSimpleName();
					res.add(Arguments.of(displayName, memConInit, memInit1, memInit2));
				}
			}
		}

		return res.stream();
	}

	/**
	 * Tests whether 2 {@link MemberContainer} instances of the same type are
	 * considered to be similar, if certain {@link Member} instances are added to
	 * each as ordinary members ({@code via memConInit.addMember(member)}).
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testMembersInContainers(String displayName, IMemberContainerInitialiser memConInit,
			IMemberInitialiser memInit1, IMemberInitialiser memInit2) {
		this.setResourceFileTestIdentifier("testMembersInContainers");

		var mem1 = memInit1.instantiate();
		var mem2 = memInit2.instantiate();

		var memCon1 = memConInit.instantiate();
		Assertions.assertTrue(memConInit.initialise(memCon1));
		var memCon2 = memConInit.instantiate();
		Assertions.assertTrue(memConInit.initialise(memCon2));

		this.assertSimilarityResult(memCon1, memCon2, true);

		Assertions.assertTrue(memConInit.addMember(memCon1, mem1));
		Assertions.assertTrue(memConInit.addMember(memCon2, mem2));

		this.assertSimilarityResult(memCon1, memCon2,
				mem1.getClass().equals(mem2.getClass()) || (this.getExpectedSimilarityResult(memCon1.getClass(),
						MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS)
						&& this.getExpectedSimilarityResult(memCon2.getClass(),
								MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS)));
	}

	/**
	 * Tests whether 2 {@link MemberContainer} instances of the same type are
	 * considered to be similar, if certain {@link Member} instances are added to
	 * each as default members ({@code via memConInit.addDefaultMember(member)}).
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testDefaultMembersInContainers(String displayName, IMemberContainerInitialiser memConInit,
			IMemberInitialiser memInit1, IMemberInitialiser memInit2) {
		this.setResourceFileTestIdentifier("testDefaultMembersInContainers");

		var mem1 = memInit1.instantiate();
		var mem2 = memInit2.instantiate();

		var memCon1 = memConInit.instantiate();
		Assertions.assertTrue(memConInit.initialise(memCon1));
		var memCon2 = memConInit.instantiate();
		Assertions.assertTrue(memConInit.initialise(memCon2));

		this.assertSimilarityResult(memCon1, memCon2, true);

		Assertions.assertTrue(memConInit.addDefaultMember(memCon1, mem1));
		Assertions.assertTrue(memConInit.addDefaultMember(memCon2, mem2));

		this.assertSimilarityResult(memCon1, memCon2,
				mem1.getClass().equals(mem2.getClass()) || (this.getExpectedSimilarityResult(memCon1.getClass(),
						MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS)
						&& this.getExpectedSimilarityResult(memCon2.getClass(),
								MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS)));
	}
}
