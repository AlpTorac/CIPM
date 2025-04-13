package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesFields;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesMethods;
import cipm.consistency.initialisers.jamopp.members.IMemberContainerInitialiser;

public class MemberContainerTest extends AbstractJaMoPPSimilarityTest
		implements UsesMethods, UsesFields, UsesConcreteClassifiers {
	private Member mem1;
	private Member mem2;
	private Member defMem1;
	private Member defMem2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IMemberContainerInitialiser.class);
	}

	protected MemberContainer initElement(IMemberContainerInitialiser init, Member[] members, Member[] defMembers) {
		MemberContainer result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addMembers(result, members));
		Assertions.assertTrue(init.addDefaultMembers(result, defMembers));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		mem1 = this.createMinimalClass("cls1");
		mem2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(mem1, mem2));

		defMem1 = this.createMinimalClass("cls1");
		defMem2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(defMem1, defMem2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testMember(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init, new Member[] { this.cloneEObjWithContainers(mem1) }, null);
		var objTwo = this.initElement(init, new Member[] { this.cloneEObjWithContainers(mem2) }, null);

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testMemberSize(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Member[] { this.cloneEObjWithContainers(mem1), this.cloneEObjWithContainers(mem2) }, null);
		var objTwo = this.initElement(init, new Member[] { this.cloneEObjWithContainers(mem1) }, null);

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testMemberPosition(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Member[] { this.cloneEObjWithContainers(mem1), this.cloneEObjWithContainers(mem2) }, null);
		var objTwo = this.initElement(init,
				new Member[] { this.cloneEObjWithContainers(mem2), this.cloneEObjWithContainers(mem1) }, null);

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testMemberDuplication(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Member[] { this.cloneEObjWithContainers(mem1), this.cloneEObjWithContainers(mem1) }, null);
		var objTwo = this.initElement(init, new Member[] { this.cloneEObjWithContainers(mem1) }, null);

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testMemberNullCheck(IMemberContainerInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, new Member[] { this.cloneEObjWithContainers(mem1) }, null),
				init, true, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testDefaultMember(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, new Member[] { this.cloneEObjWithContainers(defMem1) });
		var objTwo = this.initElement(init, null, new Member[] { this.cloneEObjWithContainers(defMem2) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testDefaultMemberSize(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new Member[] { this.cloneEObjWithContainers(defMem1), this.cloneEObjWithContainers(defMem2) });
		var objTwo = this.initElement(init, null, new Member[] { this.cloneEObjWithContainers(defMem1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testDefaultMemberPosition(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new Member[] { this.cloneEObjWithContainers(defMem1), this.cloneEObjWithContainers(defMem2) });
		var objTwo = this.initElement(init, null,
				new Member[] { this.cloneEObjWithContainers(defMem2), this.cloneEObjWithContainers(defMem1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testDefaultMemberDuplication(IMemberContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new Member[] { this.cloneEObjWithContainers(defMem1), this.cloneEObjWithContainers(defMem1) });
		var objTwo = this.initElement(init, null, new Member[] { this.cloneEObjWithContainers(defMem1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testDefaultMemberNullCheck(IMemberContainerInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, null, new Member[] { this.cloneEObjWithContainers(defMem1) }), init, true,
				MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}
}
