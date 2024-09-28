package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.members.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesFields;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesMethods;

public class MemberContainerTest extends AbstractEObjectJavaSimilarityTest
		implements UsesMethods, UsesFields, UsesConcreteClassifiers {
	protected MemberContainer initElement(IMemberContainerInitialiser init, Member[] members, Member[] defMembers) {
		MemberContainer result = init.instantiate();
		Assertions.assertTrue(init.addMembers(result, members));
		Assertions.assertTrue(init.addDefaultMembers(result, defMembers));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testMember(IMemberContainerInitialiser init) {
		var objOne = this.initElement(init, new Member[] { this.createMinimalClass("cls1") }, null);
		var objTwo = this.initElement(init, new Member[] { this.createMinimalClass("cls2") }, null);

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testMemberSize(IMemberContainerInitialiser init) {
		var objOne = this.initElement(init,
				new Member[] { this.createMinimalClass("cls1"), this.createMinimalClass("cls2") }, null);
		var objTwo = this.initElement(init, new Member[] { this.createMinimalClass("cls1") }, null);

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testMemberNullCheck(IMemberContainerInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, new Member[] { this.createMinimalClass("cls1") }, null),
				init, false, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}

	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testDefaultMember(IMemberContainerInitialiser init) {
		var objOne = this.initElement(init, null, new Member[] { this.createMinimalClass("cls1") });
		var objTwo = this.initElement(init, null, new Member[] { this.createMinimalClass("cls2") });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}

	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testDefaultMemberSize(IMemberContainerInitialiser init) {
		var objOne = this.initElement(init, null,
				new Member[] { this.createMinimalClass("cls1"), this.createMinimalClass("cls2") });
		var objTwo = this.initElement(init, null, new Member[] { this.createMinimalClass("cls1") });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}

	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testDefaultMemberNullCheck(IMemberContainerInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, null, new Member[] { this.createMinimalClass("cls1") }),
				init, false, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}
}
