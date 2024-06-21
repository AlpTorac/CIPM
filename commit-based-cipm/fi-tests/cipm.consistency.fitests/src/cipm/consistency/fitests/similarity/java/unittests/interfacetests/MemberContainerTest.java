package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.java.unittests.UsesFields;
import cipm.consistency.fitests.similarity.java.unittests.UsesMethods;

public class MemberContainerTest extends EObjectSimilarityTest implements
	UsesMethods, UsesFields, UsesConcreteClassifiers {
	protected MemberContainer initElement(IMemberContainerInitialiser init,
			Member[] members, Member[] defMembers) {
		MemberContainer result = init.instantiate();
		init.minimalInitialisation(result);
		init.addMembers(result, members);
		init.addDefaultMembers(result, defMembers);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testMember(IMemberContainerInitialiser init) {
		this.setResourceFileTestIdentifier("testMember");
		
		var objOne = this.initElement(init, new Member[] {
				this.createMinimalClass("cls1")
		}, null);
		var objTwo = this.initElement(init, new Member[] {
				this.createMinimalClass("cls2")
		}, null);
		
		this.testX(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS);
	}
	
	@ParameterizedTest
	@ArgumentsSource(MemberContainerTestParams.class)
	public void testDefaultMember(IMemberContainerInitialiser init) {
		this.setResourceFileTestIdentifier("testDefaultMember");
		
		var objOne = this.initElement(init, null, new Member[] {
				this.createMinimalClass("cls1")
		});
		var objTwo = this.initElement(init, null, new Member[] {
				this.createMinimalClass("cls2")
		});
		
		this.testX(objOne, objTwo, MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS);
	}
}
