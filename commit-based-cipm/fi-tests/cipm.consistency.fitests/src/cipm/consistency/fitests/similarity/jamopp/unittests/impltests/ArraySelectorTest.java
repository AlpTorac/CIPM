package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.arrays.ArraySelectorInitialiser;

public class ArraySelectorTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression pos1;
	private Expression pos2;

	protected ArraySelector initElement(Expression pos) {
		var asInit = new ArraySelectorInitialiser();
		var as = asInit.instantiate();
		Assertions.assertTrue(asInit.setPosition(as, pos));
		return as;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		pos1 = this.createDecimalIntegerLiteral(1);
		pos2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(pos1, pos2));
	}

	@Test
	public void testPosition() {
		var objOne = this.initElement(this.cloneEObjWithContainers(pos1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(pos2));

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_SELECTOR__POSITION);
	}

	@Test
	public void testPositionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(pos1)),
				new ArraySelectorInitialiser(), false, ArraysPackage.Literals.ARRAY_SELECTOR__POSITION);
	}
}
