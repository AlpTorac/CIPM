package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.members.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.members.IMemberContainerInitialiser;
import cipm.consistency.initialisers.jamopp.members.IMemberInitialiser;

/**
 * Tests whether the positioning of {@link Member}s within their
 * {@link MemberContainer} influences the similarity checking of member
 * containers. Accounts for some member implementors also being member container
 * implementors. Contains tests for both ordinary members (member instances that
 * are added as members) and default members (member instances that are added as
 * default members).
 * 
 * @author Alp Torac Genc
 */
public class MemberPositionTest extends AbstractJaMoPPSimilarityTest {
	/**
	 * See the tests in this class for more information.
	 * 
	 * @return All combinations of {@link IMemberInitialiser} and
	 *         {@link IMemberContainerInitialiser} implementors, along with a
	 *         display name for the tests that use these parameters.
	 */
	private static Stream<Arguments> genMemXMemCon() {
		var res = new ArrayList<Arguments>();

		for (var memInit : getNonAdaptedInitialisersFor(IMemberInitialiser.class)) {
			for (var memConInit : getNonAdaptedInitialisersFor(IMemberContainerInitialiser.class)) {
				res.add(Arguments.of(memInit, memConInit,
						String.format("%s instances in %s", memInit.getInstanceClassOfInitialiser().getSimpleName(),
								memConInit.getInstanceClassOfInitialiser().getSimpleName())));
			}
		}

		return res.stream();
	}

	/**
	 * See the tests in this class for more information.
	 * 
	 * @return All combinations of {@link IMemberInitialiser} implementors with
	 *         implementors of both {@link IMemberInitialiser} and
	 *         {@link IMemberContainerInitialiser}, along with a display name for
	 *         the tests that use these parameters.
	 */
	private static Stream<Arguments> genMemXMemConMem() {
		var res = new ArrayList<Arguments>();

		for (var memInit : getNonAdaptedInitialisersFor(IMemberInitialiser.class)) {
			for (var memConInit : getNonAdaptedInitialisersFor(IMemberContainerInitialiser.class)) {
				if (memConInit instanceof IMemberInitialiser) {
					res.add(Arguments.of(memInit, memConInit,
							String.format("%s instances in %s", memInit.getInstanceClassOfInitialiser().getSimpleName(),
									memConInit.getInstanceClassOfInitialiser().getSimpleName())));
				}
			}
		}

		return res.stream();
	}

	/**
	 * @return Whether all contents (i.e. {@code eAllContents()}) of given objects
	 *         are similar with respect to {@link #isSimilar(Object, Object)}.
	 */
	private boolean checkSimilarityContentWise(EObject obj1, EObject obj2) {
		var it1 = obj1.eAllContents();
		var it2 = obj2.eAllContents();
		while (it1.hasNext() && it2.hasNext()) {
			var elem1 = it1.next();
			var elem2 = it2.next();
			var res1 = this.isSimilar(elem1, elem2);
			if (res1 == null || !res1) {
				return false;
			}
			var res2 = this.isSimilar(elem2, elem1);
			if (res2 == null || !res2) {
				return false;
			}
		}

		// Check if all contents on both sides are covered
		if (it1.hasNext() ^ it2.hasNext()) {
			return false;
		}
		return true;
	}

	/**
	 * Ensures that positioning of {@link Member}s added to {@link MemberContainer}
	 * is not detected as a difference, when comparing member containers. Said
	 * members are added as ordinary members to member containers.
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemCon")
	public void testMemberOrder_DifferentOrder_NoNesting(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1 = memConInit.instantiate();
		var memCon2 = memConInit.instantiate();

		memConInit.addMembers(memCon1, new Member[] { member11, member12 });
		memConInit.addMembers(memCon2, new Member[] { member22, member21 });

		// Ensure that the order of members is as intended
		Assertions.assertEquals(nameDoesNotMatter, this.areSimilar(memCon1.getMembers(), memCon2.getMembers()));

		// Ensure that both member containers (on their own) are similar
		this.testSimilarity(memCon1, memCon2, true);

		// Ensure that both member containers are different based on content
		Assertions.assertEquals(nameDoesNotMatter, this.checkSimilarityContentWise(memCon1, memCon2));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testMemberOrder_DifferentOrder_OneSide_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2 = memConInit.instantiate();

		memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addMembers(memCon2, new Member[] { member22, member21 });
		memConInit.addMember(memCon1Outer, (Member) memCon1Inner);

		// Ensure that the order of members is as intended
		Assertions.assertEquals(nameDoesNotMatter, this.areSimilar(memCon1Inner.getMembers(), memCon2.getMembers()));

		// Ensure that the members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getMembers().size());
		Assertions.assertEquals(2, memCon2.getMembers().size());

		/*
		 * Ensure that memCon1Outer and memCon2 (on their own) are similar, while
		 * memCon1Inner and memCon2 are not similar, due to memCon1Inner being nested
		 * within memCon1Outer.
		 */
		this.testSimilarity(memCon1Outer, memCon2, true);
		this.testSimilarity(memCon1Inner, memCon2, false);

		/*
		 * Ensure that outside member containers are different based on content
		 * 
		 * Note: They have to be different, because of memCon1Inner being nested in
		 * memCon1Outer.
		 */
		Assertions.assertFalse(this.checkSimilarityContentWise(memCon1Outer, memCon2));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testMemberOrder_DifferentOrder_BothSides_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2Outer = memConInit.instantiate();
		var memCon2Inner = memConInit.instantiate();

		memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addMembers(memCon2Inner, new Member[] { member22, member21 });
		memConInit.addMember(memCon1Outer, (Member) memCon1Inner);
		memConInit.addMember(memCon2Outer, (Member) memCon2Inner);

		// Ensure that the order of members is as intended
		Assertions.assertEquals(nameDoesNotMatter,
				this.areSimilar(memCon1Inner.getMembers(), memCon2Inner.getMembers()));

		// Ensure that the members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getMembers().size());
		Assertions.assertEquals(1, memCon2Outer.getMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getMembers().size());
		Assertions.assertEquals(2, memCon2Inner.getMembers().size());

		/*
		 * Ensure that member containers of the same level (inner/outer) are similar
		 * amongst themselves.
		 */
		this.testSimilarity(memCon1Outer, memCon2Outer, true);
		this.testSimilarity(memCon1Inner, memCon2Inner, true);
		this.testSimilarity(memCon1Outer, memCon1Inner, false);
		this.testSimilarity(memCon2Outer, memCon2Inner, false);

		// Ensure that member containers are different based on content, if names matter
		Assertions.assertEquals(nameDoesNotMatter, this.checkSimilarityContentWise(memCon1Outer, memCon2Outer));
		Assertions.assertEquals(nameDoesNotMatter, this.checkSimilarityContentWise(memCon1Inner, memCon2Inner));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testMemberOrder_SameOrder_OneSide_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		/**
		 * There are members and member containers that care about namespaces in
		 * similarity checking, which is relevant here, since there are nested
		 * classifiers (memCon1Inner).
		 */
		var namespaceDoesNotMatterForMembers = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2 = memConInit.instantiate();

		memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addMembers(memCon2, new Member[] { member21, member22 });
		memConInit.addMember(memCon1Outer, (Member) memCon1Inner);

		// Ensure that the order of members is as intended
		var expectedMembersSimilarityResult = namespaceDoesNotMatterForMembers;
		Assertions.assertEquals(expectedMembersSimilarityResult,
				this.areSimilar(memCon1Inner.getMembers(), memCon2.getMembers()));

		// Ensure that the members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getMembers().size());
		Assertions.assertEquals(2, memCon2.getMembers().size());

		/*
		 * Ensure that memCon1Outer and memCon2 (as member containers, without their
		 * members) are similar, while memCon1Inner and memCon2 are not similar, due to
		 * memCon1Inner being nested within memCon1Outer.
		 */
		this.testSimilarity(memCon1Outer, memCon2, true);
		this.testSimilarity(memCon1Inner, memCon2, false);

		/*
		 * Ensure that outside member containers are different based on content
		 * 
		 * Note: They have to be different, because of memCon1Inner being nested in
		 * memCon1Outer.
		 */
		Assertions.assertFalse(this.checkSimilarityContentWise(memCon1Outer, memCon2));

		// Ensure that memCon1Inner and memCon2 are similar based on content
		Assertions.assertEquals(expectedMembersSimilarityResult,
				this.checkSimilarityContentWise(memCon1Inner, memCon2));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testMemberOrder_SameOrder_BothSides_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2Outer = memConInit.instantiate();
		var memCon2Inner = memConInit.instantiate();

		memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addMembers(memCon2Inner, new Member[] { member21, member22 });
		memConInit.addMember(memCon1Outer, (Member) memCon1Inner);
		memConInit.addMember(memCon2Outer, (Member) memCon2Inner);

		// Ensure that the order of members is as intended
		Assertions.assertTrue(this.areSimilar(memCon1Inner.getMembers(), memCon2Inner.getMembers()));

		// Ensure that the members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getMembers().size());
		Assertions.assertEquals(1, memCon2Outer.getMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getMembers().size());
		Assertions.assertEquals(2, memCon2Inner.getMembers().size());

		/*
		 * Ensure that member containers of the same level (inner/outer) are similar
		 * amongst themselves.
		 */
		this.testSimilarity(memCon1Outer, memCon2Outer, true);
		this.testSimilarity(memCon1Inner, memCon2Inner, true);
		this.testSimilarity(memCon1Outer, memCon1Inner, false);
		this.testSimilarity(memCon2Outer, memCon2Inner, false);

		// Ensure that member containers are different based on content, if names matter
		Assertions.assertTrue(this.checkSimilarityContentWise(memCon1Outer, memCon2Outer));
		Assertions.assertTrue(this.checkSimilarityContentWise(memCon1Inner, memCon2Inner));
	}

	/**
	 * Ensures that positioning of {@link Member}s added to {@link MemberContainer}
	 * is not detected as a difference, when comparing member containers. Said
	 * members are added as default members to member containers.
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Default member: {2}")
	@MethodSource("genMemXMemCon")
	public void testDefaultMemberOrder_DifferentOrder_NoNesting(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1 = memConInit.instantiate();
		var memCon2 = memConInit.instantiate();

		memConInit.addDefaultMembers(memCon1, new Member[] { member11, member12 });
		memConInit.addDefaultMembers(memCon2, new Member[] { member22, member21 });

		// Ensure that the order of default members is as intended
		Assertions.assertEquals(nameDoesNotMatter,
				this.areSimilar(memCon1.getDefaultMembers(), memCon2.getDefaultMembers()));

		// Ensure that both member containers (on their own) are similar
		this.testSimilarity(memCon1, memCon2, true);

		// Ensure that both member containers are different based on content
		Assertions.assertEquals(nameDoesNotMatter, this.checkSimilarityContentWise(memCon1, memCon2));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testDefaultMemberOrder_DifferentOrder_OneSide_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2 = memConInit.instantiate();

		memConInit.addDefaultMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addDefaultMembers(memCon2, new Member[] { member22, member21 });
		memConInit.addDefaultMember(memCon1Outer, (Member) memCon1Inner);

		// Ensure that the order of default members is as intended
		Assertions.assertEquals(nameDoesNotMatter,
				this.areSimilar(memCon1Inner.getDefaultMembers(), memCon2.getDefaultMembers()));

		// Ensure that the default members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon2.getDefaultMembers().size());

		/*
		 * Ensure that memCon1Outer and memCon2 (on their own) are similar, while
		 * memCon1Inner and memCon2 are not similar, due to memCon1Inner being nested
		 * within memCon1Outer.
		 */
		this.testSimilarity(memCon1Outer, memCon2, true);
		this.testSimilarity(memCon1Inner, memCon2, false);

		/*
		 * Ensure that outside member containers are different based on content
		 * 
		 * Note: They have to be different, because of memCon1Inner being nested in
		 * memCon1Outer.
		 */
		Assertions.assertFalse(this.checkSimilarityContentWise(memCon1Outer, memCon2));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testDefaultMemberOrder_DifferentOrder_BothSides_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2Outer = memConInit.instantiate();
		var memCon2Inner = memConInit.instantiate();

		memConInit.addDefaultMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addDefaultMembers(memCon2Inner, new Member[] { member22, member21 });
		memConInit.addDefaultMember(memCon1Outer, (Member) memCon1Inner);
		memConInit.addDefaultMember(memCon2Outer, (Member) memCon2Inner);

		// Ensure that the order of default members is as intended
		Assertions.assertEquals(nameDoesNotMatter,
				this.areSimilar(memCon1Inner.getDefaultMembers(), memCon2Inner.getDefaultMembers()));

		// Ensure that the default members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getDefaultMembers().size());
		Assertions.assertEquals(1, memCon2Outer.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon2Inner.getDefaultMembers().size());

		/*
		 * Ensure that member containers of the same level (inner/outer) are similar
		 * amongst themselves.
		 */
		this.testSimilarity(memCon1Outer, memCon2Outer, true);
		this.testSimilarity(memCon1Inner, memCon2Inner, true);
		this.testSimilarity(memCon1Outer, memCon1Inner, false);
		this.testSimilarity(memCon2Outer, memCon2Inner, false);

		// Ensure that member containers are different based on content, if names matter
		Assertions.assertEquals(nameDoesNotMatter, this.checkSimilarityContentWise(memCon1Outer, memCon2Outer));
		Assertions.assertEquals(nameDoesNotMatter, this.checkSimilarityContentWise(memCon1Inner, memCon2Inner));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testDefaultMemberOrder_SameOrder_OneSide_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		/**
		 * There are members and member containers that care about namespaces in
		 * similarity checking, which is relevant here, since there are nested
		 * classifiers (memCon1Inner).
		 */
		var namespaceDoesNotMatterForMembers = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2 = memConInit.instantiate();

		memConInit.addDefaultMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addDefaultMembers(memCon2, new Member[] { member21, member22 });
		memConInit.addDefaultMember(memCon1Outer, (Member) memCon1Inner);

		// Ensure that the order of default members is as intended
		var expectedMembersSimilarityResult = namespaceDoesNotMatterForMembers;
		Assertions.assertEquals(expectedMembersSimilarityResult,
				this.areSimilar(memCon1Inner.getDefaultMembers(), memCon2.getDefaultMembers()));

		// Ensure that the default members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon2.getDefaultMembers().size());

		/*
		 * Ensure that memCon1Outer and memCon2 (as member containers, without their
		 * members) are similar, while memCon1Inner and memCon2 are not similar, due to
		 * memCon1Inner being nested within memCon1Outer.
		 */
		this.testSimilarity(memCon1Outer, memCon2, true);
		this.testSimilarity(memCon1Inner, memCon2, false);

		/*
		 * Ensure that outside member containers are different based on content
		 * 
		 * Note: They have to be different, because of memCon1Inner being nested in
		 * memCon1Outer.
		 */
		Assertions.assertFalse(this.checkSimilarityContentWise(memCon1Outer, memCon2));

		// Ensure that memCon1Inner and memCon2 are similar based on content
		Assertions.assertEquals(expectedMembersSimilarityResult,
				this.checkSimilarityContentWise(memCon1Inner, memCon2));
	}

	/**
	 * TODO Add commentary
	 * 
	 * @param memInit     Initialiser that will be used to instantiate members
	 * @param memConInit  Initialiser that will be used to instantiate member
	 *                    containers, which will contain the members
	 * @param displayName Parameter dependent part of the display name
	 */
	@ParameterizedTest(name = "Member: {2}")
	@MethodSource("genMemXMemConMem")
	public void testDefaultMemberOrder_SameOrder_BothSides_Nested(IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit, String displayName) {
		// Use the name attribute as the difference factor between elements
		var member11 = memInit.instantiate();
		memInit.setName(member11, "mem1");
		var member12 = memInit.instantiate();
		memInit.setName(member12, "mem2");
		var member21 = memInit.instantiate();
		memInit.setName(member21, "mem1");
		var member22 = memInit.instantiate();
		memInit.setName(member22, "mem2");

		/*
		 * Since there are members that are currently impossible to make different (ex:
		 * Block), there is no way to always assert false. Therefore, use the expected
		 * similarity result instead.
		 */
		var nameDoesNotMatter = this.getExpectedSimilarityResult(memInit.instantiate(),
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		// Make sure the setup is correct
		Assertions.assertTrue(this.isSimilar(member11, member21));
		Assertions.assertTrue(this.isSimilar(member12, member22));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member11, member12));
		Assertions.assertEquals(nameDoesNotMatter, this.isSimilar(member21, member22));

		var memCon1Outer = memConInit.instantiate();
		var memCon1Inner = memConInit.instantiate();
		var memCon2Outer = memConInit.instantiate();
		var memCon2Inner = memConInit.instantiate();

		memConInit.addDefaultMembers(memCon1Inner, new Member[] { member11, member12 });
		memConInit.addDefaultMembers(memCon2Inner, new Member[] { member21, member22 });
		memConInit.addDefaultMember(memCon1Outer, (Member) memCon1Inner);
		memConInit.addDefaultMember(memCon2Outer, (Member) memCon2Inner);

		// Ensure that the order of default members is as intended
		Assertions.assertTrue(this.areSimilar(memCon1Inner.getDefaultMembers(), memCon2Inner.getDefaultMembers()));

		// Ensure that the default members are in correct containers
		Assertions.assertEquals(1, memCon1Outer.getDefaultMembers().size());
		Assertions.assertEquals(1, memCon2Outer.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon1Inner.getDefaultMembers().size());
		Assertions.assertEquals(2, memCon2Inner.getDefaultMembers().size());

		/*
		 * Ensure that member containers of the same level (inner/outer) are similar
		 * amongst themselves.
		 */
		this.testSimilarity(memCon1Outer, memCon2Outer, true);
		this.testSimilarity(memCon1Inner, memCon2Inner, true);
		this.testSimilarity(memCon1Outer, memCon1Inner, false);
		this.testSimilarity(memCon2Outer, memCon2Inner, false);

		// Ensure that member containers are different based on content, if names matter
		Assertions.assertTrue(this.checkSimilarityContentWise(memCon1Outer, memCon2Outer));
		Assertions.assertTrue(this.checkSimilarityContentWise(memCon1Inner, memCon2Inner));
	}
}
