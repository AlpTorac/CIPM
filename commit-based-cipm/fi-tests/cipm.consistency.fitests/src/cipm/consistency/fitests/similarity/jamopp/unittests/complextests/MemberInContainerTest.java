package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.params.JaMoPPSimilarityCriterionExtension;
import cipm.consistency.initialisers.jamopp.members.IMemberContainerInitialiser;
import cipm.consistency.initialisers.jamopp.members.IMemberInitialiser;

/**
 * Tests whether {@link MemberContainer} implementors' similarity is computed as
 * expected, if they contain different types of {@link Member} instances.
 * {@link Member} instances are added as members to {@link MemberContainer}s in
 * some tests and as default members in tests in others. <br>
 * <br>
 * There are differences between this test class and the
 * {@link cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests.MemberContainerTest}.
 * This test class checks the similarity of 2 {@link MemberContainer} instances
 * of the same type but with varying {@link Member} instances. The latter only
 * tests the similarity of {@link MemberContainer} instances of the same type
 * with the same {@link Member} instances.<br>
 * <br>
 * <b>This test class is overshadowed by neither
 * {@link cipm.consistency.fitests.similarity.jamopp.unittests.impltests} nor
 * {@link cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests},
 * because the type of the {@link MemberContainer} containing a certain
 * {@link Member} can indirectly influence the similarity checking result of
 * both {@link MemberContainer} instances and {@link Member} instances (via
 * qualified name differences for instance).</b>
 * 
 * @author Alp Torac Genc
 */
public class MemberInContainerTest extends AbstractJaMoPPSimilarityTest {
	/**
	 * @return Parameters for the test methods in this test class. Refer to their
	 *         documentation for more information.
	 */
	private static Stream<Arguments> genTestParams() {
		var res = new ArrayList<Arguments>();

		for (var memInit : getEachInitialiserOnceFor(IMemberInitialiser.class)) {
			for (var memConInit1 : getEachInitialiserOnceFor(IMemberContainerInitialiser.class)) {
				for (var memConInit2 : getEachInitialiserOnceFor(IMemberContainerInitialiser.class)) {
					var displayName = "Member " + memInit.getClass().getSimpleName() + " used with containers ("
							+ memConInit1.getClass().getSimpleName() + ", " + memConInit2.getClass().getSimpleName()
							+ ")";
					res.add(Arguments.of(displayName, memInit, memConInit1, memConInit2));
				}
			}
		}

		return res.stream();
	}

	/**
	 * TODO Add commentary
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("genTestParams")
	public void testMembersInContainers(String displayName, IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit1, IMemberContainerInitialiser memConInit2) {
		var member1 = memInit.instantiate();
		var member2 = memInit.instantiate();

		var memCon1 = memConInit1.instantiate();
		var memCon2 = memConInit2.instantiate();

		memConInit1.addMember(memCon1, member1);
		memConInit2.addMember(memCon2, member2);

		this.testSimilarity(member1, member2, this.getExpectedSimilarityResult(member1, member2, memCon1, memCon2));
	}

	/**
	 * TODO Add commentary
	 */
	@ParameterizedTest(name = "{0}")
	@MethodSource("genTestParams")
	public void testDefaultMembersInContainers(String displayName, IMemberInitialiser memInit,
			IMemberContainerInitialiser memConInit1, IMemberContainerInitialiser memConInit2) {
		var member1 = memInit.instantiate();
		var member2 = memInit.instantiate();

		var memCon1 = memConInit1.instantiate();
		var memCon2 = memConInit2.instantiate();

		memConInit1.addDefaultMember(memCon1, member1);
		memConInit2.addDefaultMember(memCon2, member2);

		this.testSimilarity(member1, member2, this.getExpectedSimilarityResult(member1, member2, memCon1, memCon2));
	}

	/**
	 * TODO Add commentary
	 * TODO Extract similarity entry
	 */
	private Boolean getExpectedSimilarityResult(Member member1, Member member2, MemberContainer memCon1,
			MemberContainer memCon2) {
		var memberCls = member1.getClass();
		var containerExpectedSimRes = this.getExpectedSimilarityResult(memberCls,
				JaMoPPSimilarityCriterionExtension.ECONTAINER);
		var containerClssEqual = memCon1.getClass().equals(memCon2.getClass());

		return containerClssEqual || (containerExpectedSimRes &&

		/*
		 * ConcreteClassifier indirectly cares about its eContainer, because its
		 * qualified name can be influenced by its container.
		 */
				(!ConcreteClassifier.class.isAssignableFrom(memberCls) || ((ConcreteClassifier) member1)
						.getQualifiedName().equals(((ConcreteClassifier) member2).getQualifiedName())));
	}
}
