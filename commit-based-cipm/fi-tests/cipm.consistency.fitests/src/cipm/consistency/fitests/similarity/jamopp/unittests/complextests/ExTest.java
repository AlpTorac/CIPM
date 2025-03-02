package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import org.emftext.language.java.members.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.classifiers.ClassInitialiser;

public class ExTest extends AbstractJaMoPPSimilarityTest {
	@Test
	public void test() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				var mc = new ClassInitialiser();
				var cls = mc.instantiate();
				mc.addMembers(cls, new Member[] { mc.instantiate(), mc.instantiate(), mc.instantiate(),
						mc.instantiate(), mc.instantiate() });
				Assertions.assertTrue(mc.changeAttributeValuePosition(cls.getMembers(), i, j));
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				var mc = new ClassInitialiser();
				var cls = mc.instantiate();
				mc.addMembers(cls, new Member[] { mc.instantiate(), mc.instantiate(), mc.instantiate(),
						mc.instantiate(), mc.instantiate() });
				Assertions.assertTrue(mc.changeAttributeValuePosition(cls.getMembers().get(i), j));
			}
		}
	}

	@Test
	public void test2() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				var mc = new ClassInitialiser();
				var cls = mc.instantiate();
				mc.addMembers(cls, new Member[] { mc.instantiate(), mc.instantiate(), mc.instantiate(),
						mc.instantiate(), mc.instantiate() });
				Assertions.assertTrue(mc.swapAttributeValuePosition(cls.getMembers(), i, j));
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				var mc = new ClassInitialiser();
				var cls = mc.instantiate();
				mc.addMembers(cls, new Member[] { mc.instantiate(), mc.instantiate(), mc.instantiate(),
						mc.instantiate(), mc.instantiate() });
				Assertions.assertTrue(mc.swapAttributeValuePosition(cls.getMembers().get(i), cls.getMembers().get(j)));
			}
		}
	}
}
