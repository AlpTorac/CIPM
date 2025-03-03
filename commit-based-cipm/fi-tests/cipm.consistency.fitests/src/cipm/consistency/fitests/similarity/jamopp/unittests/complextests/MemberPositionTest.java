package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
 * TODO Refactor and clean up
 * 
 * @author Alp Torac Genc
 */
public class MemberPositionTest extends AbstractJaMoPPSimilarityTest {
	private Member[] createMembers(IMemberInitialiser memInit, String namePrefix, int count) {
		var mems = new Member[count];
		for (int i = 0; i < count; i++) {
			var mem = memInit.instantiate();
			memInit.setName(mem, namePrefix + i);
			mems[i] = mem;
		}
		return mems;
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
	@TestFactory
	public Collection<DynamicNode> testMemberPosition() {
		var tests = new ArrayList<DynamicNode>();

		var allInits = this.getUsedInitialiserPackage().getAllInitialiserInstances();
		var memInits = List.of(
				allInits.stream().filter((i) -> i instanceof IMemberInitialiser).toArray(IMemberInitialiser[]::new));
		var memConInits = List.of(allInits.stream().filter((i) -> i instanceof IMemberContainerInitialiser)
				.toArray(IMemberContainerInitialiser[]::new));

		for (var memInit : memInits) {
			var memTestList = new ArrayList<DynamicNode>();
			for (var memConInit : memConInits) {
				var memConTestList = new ArrayList<DynamicNode>();

				var memCon1Outer = memConInit.instantiate();
				var memCon1Inner = memConInit.instantiate();
				var memCon2Outer = memConInit.instantiate();
				var memCon2Inner = memConInit.instantiate();

				Assertions.assertTrue(memConInit.addMembers(memCon1Outer, this.createMembers(memInit, "mem", 2)));
				Assertions.assertTrue(memConInit.addMembers(memCon2Outer, this.createMembers(memInit, "mem", 2)));
				Assertions.assertTrue(
						memConInit.addDefaultMembers(memCon1Outer, this.createMembers(memInit, "defMem", 2)));
				Assertions.assertTrue(
						memConInit.addDefaultMembers(memCon2Outer, this.createMembers(memInit, "defMem", 2)));

				Assertions.assertTrue(memConInit.addMembers(memCon1Inner, this.createMembers(memInit, "mem", 2)));
				Assertions.assertTrue(memConInit.addMembers(memCon2Inner, this.createMembers(memInit, "mem", 2)));
				Assertions.assertTrue(
						memConInit.addDefaultMembers(memCon1Inner, this.createMembers(memInit, "defMem", 2)));
				Assertions.assertTrue(
						memConInit.addDefaultMembers(memCon2Inner, this.createMembers(memInit, "defMem", 2)));

				// No need to test if both sides neither have a nested element nor are nested in
				// another container themselves. Such cases are already covered on other tests.

				if (memConInit instanceof IMemberInitialiser) {
					Assertions.assertTrue(memConInit.addMember(memCon1Outer, (Member) memCon1Inner));

					// Only memCon1Inner nested in memCon1Outer, memCon2Inner is not nested yet
					memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Outer, memCon1Inner));
					memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Inner, memCon2Outer));
					memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Inner, memCon2Inner));
					memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Outer, memCon2Outer));

					Assertions.assertTrue(memConInit.addMember(memCon2Outer, (Member) memCon2Inner));
					this.testSimilarity(memCon1Outer, memCon1Inner, false);
					this.testSimilarity(memCon2Outer, memCon2Inner, false);
				}

				this.testSimilarity(memCon1Outer, memCon2Outer, true);
				this.testSimilarity(memCon1Inner, memCon2Inner, true);

				memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Outer, memCon1Inner));
				memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Outer, memCon2Outer));
				memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Outer, memCon2Inner));

				memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Inner, memCon2Outer));
				memConTestList.addAll(this.generateTestsFor(memConInit, memCon1Inner, memCon2Inner));

				memConTestList.addAll(this.generateTestsFor(memConInit, memCon2Outer, memCon2Inner));

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

	private boolean sameNestLevel(EObject obj1, EObject obj2) {
		var cObj1 = obj1;
		var cObj2 = obj2;
		while (cObj1 != null && cObj2 != null) {
			cObj1 = cObj1.eContainer();
			cObj2 = cObj2.eContainer();
		}
		return cObj1 == null && cObj2 == null;
	}

	private Collection<DynamicTest> generateTestsFor(IMemberContainerInitialiser memConInit, MemberContainer memCon1,
			MemberContainer memCon2) {
		var tests = new ArrayList<DynamicTest>();

		/*
		 * Ordinary members and default members are separated due to scalability
		 * reasons.
		 */

		var mems1Size = memCon1.getMembers().size();
		var mems2Size = memCon2.getMembers().size();

		var defMems1Size = memCon1.getDefaultMembers().size();
		var defMems2Size = memCon2.getDefaultMembers().size();

		/*
		 * Exclude the symmetric cases, since test methods within this class are
		 * symmetric already.
		 */

		// Indices for ordinary members of memCon1
		for (int i1 = 0; i1 < mems1Size; i1++) {
			for (int i2 = i1; i2 < mems1Size; i2++) {

				// Indices for ordinary members of memCon2
				for (int j1 = 0; j1 < mems2Size; j1++) {
					for (int j2 = j1; j2 < mems2Size; j2++) {

						// Indices for default members of memCon1
						for (int di1 = 0; di1 < defMems1Size; di1++) {
							for (int di2 = di1; di2 < defMems1Size; di2++) {

								// Indices for default members of memCon2
								for (int dj1 = 0; dj1 < defMems2Size; dj1++) {
									for (int dj2 = dj1; dj2 < defMems2Size; dj2++) {
										/*
										 * Make sure to clone the member containers along with their own containers, so
										 * that cases with inner containers can also be accounted for.
										 */

										var memCon1Clone = this.cloneEObjWithContainers(memCon1);
										var memCon2Clone = this.cloneEObjWithContainers(memCon2);
										tests.add(this.genTest(memConInit, memCon1Clone, i1, i2, di1, di2, memCon2Clone,
												j1, j2, dj1, dj2));
									}
								}
							}
						}
					}
				}
			}
		}

		return tests;
	}

	private DynamicTest genTest(IMemberContainerInitialiser memConInit, MemberContainer memCon1Clone, int i1, int i2,
			int di1, int di2, MemberContainer memCon2Clone, int j1, int j2, int dj1, int dj2) {
		var mems1 = memCon1Clone.getMembers();
		var mems2 = memCon2Clone.getMembers();

		var defMems1 = memCon1Clone.getDefaultMembers();
		var defMems2 = memCon2Clone.getDefaultMembers();

		final var mem11Pos = i1;
		final var mem11 = mems1.get(mem11Pos);
		final var mem11NewPos = i2;

		final var mem21Pos = j1;
		final var mem21 = mems2.get(mem21Pos);
		final var mem21NewPos = j2;

		final var defMem11Pos = di1;
		final var defMem11 = defMems1.get(defMem11Pos);
		final var defMem11NewPos = di2;

		final var defMem21Pos = dj1;
		final var defMem21 = defMems2.get(defMem21Pos);
		final var defMem21NewPos = dj2;

		final var namespaceDoesNotMatterForMems = this.getExpectedSimilarityResult(mem11,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		final var namespaceDoesNotMatterForDefMems = this.getExpectedSimilarityResult(defMem11,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);

		var sameNestingLevel = this.sameNestLevel(memCon1Clone, memCon2Clone);

		/*
		 * There are member types, whose namespace becomes relevant in similarity
		 * checking. Therefore, the container of a member being nested in another
		 * container has to be accounted for, if its namespace is matters.
		 */
		final var membersSimilar = (sameNestingLevel || namespaceDoesNotMatterForMems)
				&& (mems1.size() == mems2.size());
		final var defMembersSimilar = (sameNestingLevel || namespaceDoesNotMatterForDefMems)
				&& (defMems1.size() == defMems2.size());

		return DynamicTest.dynamicTest(String.format("(%d, %d) <-> (%d, %d); (%d, %d) <-> (%d, %d) %s vs %s", mem11Pos,
				mem11NewPos, mem21Pos, mem21NewPos, defMem11Pos, defMem11NewPos, defMem21Pos, defMem21NewPos,
				memCon1Clone.eContainer(), memCon2Clone.eContainer()), () -> {

					/*
					 * Ordinary and default members are kept in separate containers. Therefore,
					 * moving ordinary members or default members first does not matter.
					 */

					Assertions.assertTrue(memConInit.changeAttributeValuePosition(mem11, mem11NewPos));
					Assertions.assertTrue(memConInit.changeAttributeValuePosition(mem21, mem21NewPos));

					Assertions.assertTrue(memConInit.changeAttributeValuePosition(defMem11, defMem11NewPos));
					Assertions.assertTrue(memConInit.changeAttributeValuePosition(defMem21, defMem21NewPos));

					var sameMemMovement = this.moveLeadsToSameResult(mem11Pos, mem11NewPos, mem21Pos, mem21NewPos);
					var sameDefMemMovement = this.moveLeadsToSameResult(defMem11Pos, defMem11NewPos, defMem21Pos,
							defMem21NewPos);

					/*
					 * These assertions are symmetric for both sides, i.e. swapping parameters
					 * yields the same result.
					 */

					Assertions.assertEquals(membersSimilar, this.areOrdinaryMembersSimilar(memCon1Clone, memCon2Clone));
					Assertions.assertEquals(membersSimilar && sameMemMovement,
							this.areOrdinaryMemberOrdersSimilar(memCon1Clone, memCon2Clone));

					Assertions.assertEquals(defMembersSimilar,
							this.areDefaultMembersSimilar(memCon1Clone, memCon2Clone));
					Assertions.assertEquals(defMembersSimilar && sameDefMemMovement,
							this.areDefaultMemberOrdersSimilar(memCon1Clone, memCon2Clone));
				});
	}

	private boolean moveLeadsToSameResult(int mem11Pos, int mem11NewPos, int mem21Pos, int mem21NewPos) {
		// Nothing moves on both sides
		var moveInPlace = mem11Pos == mem11NewPos && mem21Pos == mem21NewPos;
		// Same move on both sides
		var mimicedMove = mem11Pos == mem21Pos && mem11NewPos == mem21NewPos;
		/*
		 * Swap with neighbour (adjacent element):
		 * 
		 * {A, B, C} -> {A, C, B} is either possible by moving B to C's position or
		 * moving C to B's position. Only works for adjacent elements, as illustrated.
		 */
		var swapMove = (mem11Pos == mem21NewPos && mem21Pos == mem11NewPos)
				&& ((mem11NewPos == mem11Pos + 1 && mem21NewPos == mem21Pos - 1)
						|| (mem11NewPos == mem11Pos - 1 && mem21NewPos == mem21Pos + 1));

		return moveInPlace || mimicedMove || swapMove;
	}
}
