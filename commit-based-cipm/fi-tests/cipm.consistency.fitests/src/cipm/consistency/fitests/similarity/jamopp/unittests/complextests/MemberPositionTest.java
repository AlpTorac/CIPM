package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

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

	@TestFactory
	public Collection<DynamicNode> test() {
		var tests = new ArrayList<DynamicNode>();

		var allInits = this.getUsedInitialiserPackage().getAllInitialiserInstances();
		var memInits = List.of(
				allInits.stream().filter((i) -> i instanceof IMemberInitialiser).toArray(IMemberInitialiser[]::new));
		var memConInits = List.of(allInits.stream().filter((i) -> i instanceof IMemberContainerInitialiser)
				.toArray(IMemberContainerInitialiser[]::new));

		for (var memInit : memInits) {
			var memTestList = new ArrayList<DynamicNode>();

			var member11 = memInit.instantiate();
			memInit.setName(member11, "mem1");
			var member12 = memInit.instantiate();
			memInit.setName(member12, "mem2");
			var member21 = memInit.instantiate();
			memInit.setName(member21, "mem1");
			var member22 = memInit.instantiate();
			memInit.setName(member22, "mem2");

			for (var memConInit : memConInits) {
				var memConTestList = new ArrayList<DynamicNode>();
				memConTestList.add(this.testMemberOrder_SameOrder_NoNesting(this.cloneEObj(member11),
						this.cloneEObj(member12), this.cloneEObj(member21), this.cloneEObj(member22), memConInit));
				memConTestList.add(this.testMemberOrder_DifferentOrder_NoNesting(this.cloneEObj(member11),
						this.cloneEObj(member12), this.cloneEObj(member21), this.cloneEObj(member22), memConInit));
				if (memConInit instanceof IMemberInitialiser) {
					memConTestList.add(this.testMemberOrder_DifferentOrder_OneSide_Nested(this.cloneEObj(member11),
							this.cloneEObj(member12), this.cloneEObj(member21), this.cloneEObj(member22), memConInit));
					memConTestList.add(this.testMemberOrder_DifferentOrder_BothSides_Nested(this.cloneEObj(member11),
							this.cloneEObj(member12), this.cloneEObj(member21), this.cloneEObj(member22), memConInit));
					memConTestList.add(this.testMemberOrder_SameOrder_OneSide_Nested(this.cloneEObj(member11),
							this.cloneEObj(member12), this.cloneEObj(member21), this.cloneEObj(member22), memConInit));
					memConTestList.add(this.testMemberOrder_SameOrder_BothSides_Nested(this.cloneEObj(member11),
							this.cloneEObj(member12), this.cloneEObj(member21), this.cloneEObj(member22), memConInit));
				}
				var memConTestNode = DynamicContainer.dynamicContainer(
						String.format("Container: %s", memConInit.getInstanceClassOfInitialiser().getSimpleName()),
						memConTestList);
				memTestList.add(memConTestNode);
			}
			var memTestNode = DynamicContainer.dynamicContainer(
					String.format("Member: %s", memInit.getInstanceClassOfInitialiser().getSimpleName()), memTestList);
			tests.add(memTestNode);
		}
		return tests;
	}

	/**
	 * Checks the given parameters' member similarity first (uses the underlying
	 * similarity checking mechanisms for this part). <br>
	 * <br>
	 * <b>!!! USES MEMBER NAMES TO CHECK FOR THEIR ORDER !!!</b> <br>
	 * <br>
	 * Since there are member types, which do not care about name changes, order
	 * comparison is hard-coded to use member names AND NOT SIMILARITY CHECKING
	 * MECHANISMS.
	 * 
	 * @return Whether the order of members of the given parameters is the same.
	 */
	private boolean areMemberOrdersSimilar(EList<Member> mems1, EList<Member> mems2) {
		if (!this.areMembersSimilar(mems1, mems2)) {
			return false;
		}

		for (int i = 0; i < mems1.size(); i++) {
			var mem1 = mems1.get(i);
			var mem2 = mems2.get(i);
			var mem1Name = mem1.getName();
			var mem2Name = mem2.getName();
			if (mem1Name == null && mem2Name == null) {
				continue;
			}
			if ((mem1Name == null ^ mem2Name == null) || !mem1Name.equals(mem2Name)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @return Whether the order of ordinary members of the given parameters is the
	 *         same.
	 */
	private boolean areOrdinaryMemberOrdersSimilar(MemberContainer memCon1, MemberContainer memCon2) {
		return this.areMemberOrdersSimilar(memCon1.getMembers(), memCon2.getMembers())
				&& this.areMemberOrdersSimilar(memCon2.getMembers(), memCon1.getMembers());
	}

	/**
	 * @return Whether the order of default members of the given parameters is the
	 *         same.
	 */
	private boolean areDefaultMemberOrdersSimilar(MemberContainer memCon1, MemberContainer memCon2) {
		return this.areMemberOrdersSimilar(memCon1.getDefaultMembers(), memCon2.getDefaultMembers())
				&& this.areMemberOrdersSimilar(memCon2.getDefaultMembers(), memCon1.getDefaultMembers());
	}

	/**
	 * @return Whether the given parameters' members are similar regardless of their
	 *         order, according to the underlying similarity checking mechanism.
	 */
	private boolean areMembersSimilar(EList<Member> mems1, EList<Member> mems2) {
		if (mems1.size() != mems2.size()) {
			return false;
		}

		/*
		 * Eliminate already matched elements, in order to avoid matching an element
		 * with the same element over and over.
		 * 
		 * Put all elements into another list, so that list modifications will not be
		 * reflected to mems2.
		 */
		var unmatchedMems = new ArrayList<Member>(mems2);

		/*
		 * Iterate over mems2 instead of unmatchedMems, so that removing matched
		 * elements becomes possible.
		 */
		for (var mem1 : mems1) {
			// Look for a similar element regardless of its order
			for (var mem2 : mems2) {
				// Make sure the element is currently unmatched
				// If it is matched, go to the next element
				if (!unmatchedMems.contains(mem2)) {
					continue;
				}

				if (this.isSimilar(mem1, mem2) && this.isSimilar(mem2, mem1)) {
					// Match found, remove from unmatchedMems
					unmatchedMems.remove(mem2);
					break;
				}
			}
		}
		return unmatchedMems.isEmpty();
	}

	/**
	 * Variant of {@link #areMembersSimilar(EList, EList)} for ordinary members of
	 * member containers
	 */
	private boolean areOrdinaryMembersSimilar(MemberContainer memCon1, MemberContainer memCon2) {
		return this.areMembersSimilar(memCon1.getMembers(), memCon2.getMembers())
				&& this.areMembersSimilar(memCon2.getMembers(), memCon1.getMembers());
	}

	/**
	 * Variant of {@link #areMembersSimilar(EList, EList)} for default members of
	 * member containers
	 */
	private boolean areDefaultMembersSimilar(MemberContainer memCon1, MemberContainer memCon2) {
		return this.areMembersSimilar(memCon1.getDefaultMembers(), memCon2.getDefaultMembers())
				&& this.areMembersSimilar(memCon2.getDefaultMembers(), memCon1.getDefaultMembers());
	}

	/**
	 * TODO Add commentary
	 */
	public DynamicTest testMemberOrder_DifferentOrder_NoNesting(Member member11, Member member12, Member member21,
			Member member22, IMemberContainerInitialiser memConInit) {
		return DynamicTest.dynamicTest("a", () -> {
			var memCon1 = memConInit.instantiate();
			var memCon2 = memConInit.instantiate();

			Assertions.assertTrue(memConInit.addMembers(memCon1, new Member[] { member11, member12 }));
			Assertions.assertTrue(memConInit.addMembers(memCon2, new Member[] { member22, member21 }));

			/*
			 * Ensure that member containers of the same level (inner/outer) are similar
			 * amongst themselves.
			 */
			this.testSimilarity(memCon1, memCon2, true);

			Assertions.assertTrue(this.areOrdinaryMembersSimilar(memCon1, memCon2));
			Assertions.assertFalse(this.areOrdinaryMemberOrdersSimilar(memCon1, memCon2));
		});
	}

	/**
	 * TODO Add commentary
	 */
	public DynamicTest testMemberOrder_DifferentOrder_OneSide_Nested(Member member11, Member member12, Member member21,
			Member member22, IMemberContainerInitialiser memConInit) {
		return DynamicTest.dynamicTest("a", () -> {
			var memCon1Outer = memConInit.instantiate();
			var memCon1Inner = memConInit.instantiate();
			var memCon2 = memConInit.instantiate();

			Assertions.assertTrue(memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 }));
			Assertions.assertTrue(memConInit.addMembers(memCon2, new Member[] { member22, member21 }));
			Assertions.assertTrue(memConInit.addMember(memCon1Outer, (Member) memCon1Inner));

			/*
			 * Ensure that member containers of the same level (inner/outer) are similar
			 * amongst themselves.
			 */
			this.testSimilarity(memCon1Outer, memCon2, true);
			this.testSimilarity(memCon1Inner, memCon2, false);

			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon1Inner));
			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon2));

			/*
			 * Although the members are similar in a vacuum, they may be different because
			 * of memCon1Inner being nested in another member container, which influences
			 * their namespaces and by extension their qualified name.
			 */
			Assertions.assertEquals(
					this.getExpectedSimilarityResult(member11,
							CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
					this.areOrdinaryMembersSimilar(memCon1Inner, memCon2));
			Assertions.assertFalse(this.areOrdinaryMemberOrdersSimilar(memCon1Inner, memCon2));
		});
	}

	/**
	 * TODO Add commentary
	 */
	public DynamicTest testMemberOrder_DifferentOrder_BothSides_Nested(Member member11, Member member12,
			Member member21, Member member22, IMemberContainerInitialiser memConInit) {
		return DynamicTest.dynamicTest("a", () -> {
			var memCon1Outer = memConInit.instantiate();
			var memCon1Inner = memConInit.instantiate();
			var memCon2Outer = memConInit.instantiate();
			var memCon2Inner = memConInit.instantiate();

			Assertions.assertTrue(memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 }));
			Assertions.assertTrue(memConInit.addMembers(memCon2Inner, new Member[] { member22, member21 }));
			Assertions.assertTrue(memConInit.addMember(memCon1Outer, (Member) memCon1Inner));
			Assertions.assertTrue(memConInit.addMember(memCon2Outer, (Member) memCon2Inner));

			/*
			 * Ensure that member containers of the same level (inner/outer) are similar
			 * amongst themselves.
			 */
			this.testSimilarity(memCon1Outer, memCon2Outer, true);
			this.testSimilarity(memCon1Inner, memCon2Inner, true);
			this.testSimilarity(memCon1Outer, memCon1Inner, false);
			this.testSimilarity(memCon2Outer, memCon2Inner, false);

			Assertions.assertTrue(this.areOrdinaryMembersSimilar(memCon1Outer, memCon2Outer));
			Assertions.assertTrue(this.areOrdinaryMemberOrdersSimilar(memCon1Outer, memCon2Outer));

			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon2Inner));
			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon2Outer, memCon1Inner));

			Assertions.assertTrue(this.areOrdinaryMembersSimilar(memCon1Inner, memCon2Inner));
			Assertions.assertFalse(this.areOrdinaryMemberOrdersSimilar(memCon1Inner, memCon2Inner));
		});
	}

	/**
	 * TODO Add commentary
	 */
	public DynamicTest testMemberOrder_SameOrder_NoNesting(Member member11, Member member12, Member member21,
			Member member22, IMemberContainerInitialiser memConInit) {
		return DynamicTest.dynamicTest("a", () -> {
			var memCon1 = memConInit.instantiate();
			var memCon2 = memConInit.instantiate();

			Assertions.assertTrue(memConInit.addMembers(memCon1, new Member[] { member11, member12 }));
			Assertions.assertTrue(memConInit.addMembers(memCon2, new Member[] { member21, member22 }));

			/*
			 * Ensure that member containers of the same level (inner/outer) are similar
			 * amongst themselves.
			 */
			this.testSimilarity(memCon1, memCon2, true);

			Assertions.assertTrue(this.areOrdinaryMemberOrdersSimilar(memCon1, memCon2));
		});
	}

	/**
	 * TODO Add commentary
	 */
	public DynamicTest testMemberOrder_SameOrder_OneSide_Nested(Member member11, Member member12, Member member21,
			Member member22, IMemberContainerInitialiser memConInit) {
		return DynamicTest.dynamicTest("a", () -> {
			var memCon1Outer = memConInit.instantiate();
			var memCon1Inner = memConInit.instantiate();
			var memCon2 = memConInit.instantiate();

			Assertions.assertTrue(memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 }));
			Assertions.assertTrue(memConInit.addMembers(memCon2, new Member[] { member21, member22 }));
			Assertions.assertTrue(memConInit.addMember(memCon1Outer, (Member) memCon1Inner));

			/*
			 * Ensure that member containers of the same level (inner/outer) are similar
			 * amongst themselves.
			 */
			this.testSimilarity(memCon1Outer, memCon2, true);
			this.testSimilarity(memCon1Inner, memCon2, false);

			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon1Inner));
			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon2));

			/*
			 * Although the members are similar in a vacuum, they may be different because
			 * of memCon1Inner being nested in another member container, which influences
			 * their namespaces and by extension their qualified name.
			 */
			Assertions.assertEquals(
					this.getExpectedSimilarityResult(member11,
							CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
					this.areOrdinaryMemberOrdersSimilar(memCon1Inner, memCon2));
		});
	}

	/**
	 * TODO Add commentary
	 */
	public DynamicTest testMemberOrder_SameOrder_BothSides_Nested(Member member11, Member member12, Member member21,
			Member member22, IMemberContainerInitialiser memConInit) {
		return DynamicTest.dynamicTest("a", () -> {
			var memCon1Outer = memConInit.instantiate();
			var memCon1Inner = memConInit.instantiate();
			var memCon2Outer = memConInit.instantiate();
			var memCon2Inner = memConInit.instantiate();

			Assertions.assertTrue(memConInit.addMembers(memCon1Inner, new Member[] { member11, member12 }));
			Assertions.assertTrue(memConInit.addMembers(memCon2Inner, new Member[] { member21, member22 }));
			Assertions.assertTrue(memConInit.addMember(memCon1Outer, (Member) memCon1Inner));
			Assertions.assertTrue(memConInit.addMember(memCon2Outer, (Member) memCon2Inner));

			/*
			 * Ensure that member containers of the same level (inner/outer) are similar
			 * amongst themselves.
			 */
			this.testSimilarity(memCon1Outer, memCon2Outer, true);
			this.testSimilarity(memCon1Inner, memCon2Inner, true);
			this.testSimilarity(memCon1Outer, memCon1Inner, false);
			this.testSimilarity(memCon2Outer, memCon2Inner, false);

			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon1Inner));
			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon1Outer, memCon2Inner));
			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon2Outer, memCon1Inner));
			Assertions.assertFalse(this.areOrdinaryMembersSimilar(memCon2Outer, memCon2Inner));

			Assertions.assertTrue(this.areOrdinaryMemberOrdersSimilar(memCon1Outer, memCon2Outer));
			Assertions.assertTrue(this.areOrdinaryMemberOrdersSimilar(memCon1Inner, memCon2Inner));
		});
	}
}
