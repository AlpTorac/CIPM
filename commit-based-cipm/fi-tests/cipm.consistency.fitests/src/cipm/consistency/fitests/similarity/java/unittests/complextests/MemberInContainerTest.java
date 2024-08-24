package cipm.consistency.fitests.similarity.java.unittests.complextests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.emftext.language.java.commons.CommonsPackage;
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
 * expected, if they have different types, contain {@link Member} instances and
 * them having differing members breaks their similarity. <br>
 * <br>
 * {@link Member} instances are added as members to {@link MemberContainer}s in
 * some tests and as default members in tests in others. <br>
 * <br>
 * Due to combinational explosion, all possible combinations of
 * {@link MemberContainer} and {@link Member} instances cannot be tested.
 * Therefore, tests are parameterised over all combinations of
 * {@link MemberContainer} implementors, while the type of {@link Member}
 * instance to be added to each side is the same. <br>
 * <br>
 * The difference between this test class and the
 * {@link cipm.consistency.fitests.similarity.java.unittests.interfacetests.MemberContainerTest}
 * is: This test class checks the similarity of different types of
 * {@link MemberContainer} instances too, while the latter only tests the
 * similarity of {@link MemberContainer} instances of the same type.
 * Additionally, the latter keeps {@link Member} instances' type constant within
 * each test. <br>
 * <br>
 * <b>This test class is overshadowed by neither
 * {@link cipm.consistency.fitests.similarity.java.unittests.impltests} nor
 * {@link cipm.consistency.fitests.similarity.java.unittests.interfacetests},
 * because the type of the {@link MemberContainer} containing a certain
 * {@link Member} can influence the similarity checking result of both
 * {@link MemberContainer} instances and {@link Member} instances.</b>
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

		for (var memInit : getAllMemberInitInstances()) {
			for (var memConInit1 : getAllMemberContainerInitInstances()) {
				for (var memConInit2 : getAllMemberContainerInitInstances()) {
					var displayName = memConInit1.getClass().getSimpleName() + " - "
							+ memConInit2.getClass().getSimpleName() + " with " + memInit.getClass().getSimpleName();
					res.add(Arguments.of(displayName, memConInit1, memConInit2, memInit));
				}
			}
		}

		return res.stream();
	}

	/**
	 * Tests whether 2 {@link MemberContainer} instances (can be of different or
	 * same type) are considered to be similar, if a similar {@link Member} is added
	 * to each as an ordinary member ({@code via memConInit.addMember(member)}).
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testSimilarMembersInContainers(String displayName, IMemberContainerInitialiser memConInit1,
			IMemberContainerInitialiser memConInit2, IMemberInitialiser memInit) {
		this.setResourceFileTestIdentifier("testSimilarMembersInContainers");

		var mem1 = memInit.instantiate();
		var mem2 = memInit.instantiate();

		// Perform the assertion for members here, because adding them to member
		// containers can change their similarity
		this.assertSimilarityResult(mem1, mem2, true);

		var memCon1 = memConInit1.instantiate();
		Assertions.assertTrue(memConInit1.initialise(memCon1));
		Assertions.assertTrue(memConInit1.addMember(memCon1, mem1));

		var memCon2 = memConInit2.instantiate();
		Assertions.assertTrue(memConInit2.initialise(memCon2));
		Assertions.assertTrue(memConInit2.addMember(memCon2, mem2));

		var expectedResult = memCon1.getClass().equals(memCon2.getClass());

		this.assertSimilarityResult(memCon1, memCon2, expectedResult);
	}

	/**
	 * Tests whether 2 {@link MemberContainer} instances (can be of different or
	 * same type) are considered to be similar, if a different {@link Member} is
	 * added to each as an ordinary member
	 * ({@code via memConInit.addMember(member)}).
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testDifferentMembersInContainers(String displayName, IMemberContainerInitialiser memConInit1,
			IMemberContainerInitialiser memConInit2, IMemberInitialiser memInit) {
		this.setResourceFileTestIdentifier("testDifferentMembersInContainers");

		// All relevant members' similarity is broken by name differences
		var mem1 = memInit.instantiate();
		Assertions.assertTrue(memInit.setName(mem1, "mem1"));
		var mem2 = memInit.instantiate();
		Assertions.assertTrue(memInit.setName(mem2, "mem2"));

		// Perform the assertion for members here, because adding them to member
		// containers can change their similarity
		this.assertSimilarityResult(mem1, mem2,
				this.getExpectedSimilarityResult(mem1.getClass(), CommonsPackage.Literals.NAMED_ELEMENT__NAME) && this
						.getExpectedSimilarityResult(mem2.getClass(), CommonsPackage.Literals.NAMED_ELEMENT__NAME));

		var memCon1 = memConInit1.instantiate();
		Assertions.assertTrue(memConInit1.initialise(memCon1));
		Assertions.assertTrue(memConInit1.addMember(memCon1, mem1));

		var memCon2 = memConInit2.instantiate();
		Assertions.assertTrue(memConInit2.initialise(memCon2));
		Assertions.assertTrue(memConInit2.addMember(memCon2, mem2));

		var expectedResult = memCon1.getClass().equals(memCon2.getClass())
				&& this.getExpectedSimilarityResult(memCon1.getClass(),
						MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS)
				&& this.getExpectedSimilarityResult(memCon2.getClass(),
						MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);

		this.assertSimilarityResult(memCon1, memCon2, expectedResult);
	}

	/**
	 * Tests whether 2 {@link MemberContainer} instances (can be of different or
	 * same type) are considered to be similar, if a similar {@link Member} is added
	 * to each as a default member
	 * ({@code via memConInit.addDefaultMember(member)}).
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testSimilarDefaultMembersInContainers(String displayName, IMemberContainerInitialiser memConInit1,
			IMemberContainerInitialiser memConInit2, IMemberInitialiser memInit) {
		this.setResourceFileTestIdentifier("testSimilarDefaultMembersInContainers");

		var mem1 = memInit.instantiate();
		var mem2 = memInit.instantiate();

		// Perform the assertion for members here, because adding them to member
		// containers can change their similarity
		this.assertSimilarityResult(mem1, mem2, true);

		var memCon1 = memConInit1.instantiate();
		Assertions.assertTrue(memConInit1.initialise(memCon1));
		Assertions.assertTrue(memConInit1.addDefaultMember(memCon1, mem1));

		var memCon2 = memConInit2.instantiate();
		Assertions.assertTrue(memConInit2.initialise(memCon2));
		Assertions.assertTrue(memConInit2.addDefaultMember(memCon2, mem2));

		var expectedResult = memCon1.getClass().equals(memCon2.getClass());

		this.assertSimilarityResult(memCon1, memCon2, expectedResult);
	}

	/**
	 * Tests whether 2 {@link MemberContainer} instances (can be of different or
	 * same type) are considered to be similar, if a different {@link Member} is
	 * added to each as a default member
	 * ({@code via memConInit.addDefaultMember(member)}).
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("getMemConMemPairs")
	public void testDifferentDefaultMembersInContainers(String displayName, IMemberContainerInitialiser memConInit1,
			IMemberContainerInitialiser memConInit2, IMemberInitialiser memInit) {
		this.setResourceFileTestIdentifier("testDifferentDefaultMembersInContainers");

		// All relevant members' similarity is broken by name differences
		var mem1 = memInit.instantiate();
		Assertions.assertTrue(memInit.setName(mem1, "mem1"));
		var mem2 = memInit.instantiate();
		Assertions.assertTrue(memInit.setName(mem2, "mem2"));

		// Perform the assertion for members here, because adding them to member
		// containers can change their similarity
		this.assertSimilarityResult(mem1, mem2,
				this.getExpectedSimilarityResult(mem1.getClass(), CommonsPackage.Literals.NAMED_ELEMENT__NAME) && this
						.getExpectedSimilarityResult(mem2.getClass(), CommonsPackage.Literals.NAMED_ELEMENT__NAME));

		var memCon1 = memConInit1.instantiate();
		Assertions.assertTrue(memConInit1.initialise(memCon1));
		Assertions.assertTrue(memConInit1.addDefaultMember(memCon1, mem1));

		var memCon2 = memConInit2.instantiate();
		Assertions.assertTrue(memConInit2.initialise(memCon2));
		Assertions.assertTrue(memConInit2.addDefaultMember(memCon2, mem2));

		var expectedResult = memCon1.getClass().equals(memCon2.getClass())
				&& this.getExpectedSimilarityResult(memCon1.getClass(),
						MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS)
				&& this.getExpectedSimilarityResult(memCon2.getClass(),
						MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);

		this.assertSimilarityResult(memCon1, memCon2, expectedResult);
	}
}
