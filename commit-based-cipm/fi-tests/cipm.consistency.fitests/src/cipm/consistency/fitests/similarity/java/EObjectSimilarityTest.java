package cipm.consistency.fitests.similarity.java;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.junit.jupiter.api.Assertions;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;

/**
 * Contains re-usable test code to spare code duplication.
 * 
 * @author atora
 */
public class EObjectSimilarityTest extends AbstractSimilarityTest {
	/**
	 * Clones elem with initialiser and compares elem with its clone.
	 * They are expected to be similar.
	 */
	public void sameX(EObject elem, EObjectInitialiser initialiser) {
		var objOne = elem;
		var objTwo = initialiser.clone(objOne);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + EcoreUtil.equals(objOne, objTwo));
	}
	
	/**
	 * Compares elem1 with elem2, expects them to be different (not similar).
	 */
	public void differentX(EObject elem1, EObject elem2) {
		this.compareX(elem1, elem2, Boolean.FALSE);
	}
	
	/**
	 * Compares elem1 with elem2, expects the similarity result to be the
	 * same with the given expected value.
	 */
	public void compareX(EObject elem1, EObject elem2, Boolean expectedSimilarityResult) {
		var objOne = elem1;
		var objTwo = elem2;
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertEquals(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				expectedSimilarityResult,
				"EcoreUtil comparison result: " + EcoreUtil.equals(objOne, objTwo));
	}

	public void listSameXSameOrder(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
		var obj11 = elem1;
		var obj11Copy = initialiser.clone(obj11);
		
		var obj12 = elem2;
		var obj12Copy = initialiser.clone(obj12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj11Copy, obj12Copy);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + EcoreUtil.equals(list1, list2));
	}
	
	/**
	 * Tests whether the order in the list matters
	 * <br><br>
	 * As of 14.04.2024, it should matter, as the elements are
	 * to be compared pairwise.
	 */
	public void listSameXDifferentOrder(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
		var obj11 = elem1;
		var obj11Copy = initialiser.clone(obj11);
		
		var obj12 = elem2;
		var obj12Copy = initialiser.clone(obj12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj12Copy, obj11Copy);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + EcoreUtil.equals(list1, list2));
	}
	
	public void listDifferentX(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		var resOne = this.createResource(elems1);
		var resTwo = this.createResource(elems2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + EcoreUtil.equals(elems1, elems2));
	}
}
