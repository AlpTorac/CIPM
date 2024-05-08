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
	public Boolean compareWithEcore(EObject obj1, EObject obj2) {
		return EcoreUtil.equals(obj1, obj2);
	}
	
	public Boolean compareListWithEcore(List<? extends EObject> obj1, List<? extends EObject> obj2) {
		return EcoreUtil.equals(obj1, obj2);
	}
	
	public String getEcoreComparisonMessage(EObject obj1, EObject obj2) {
		return "EcoreUtil comparison result: " + this.compareWithEcore(obj1, obj2);
	}
	
	public String getEcoreListComparisonMessage(List<? extends EObject> obj1, List<? extends EObject> obj2) {
		return "EcoreUtil comparison (list) result: " + this.compareListWithEcore(obj1, obj2);
	}
	
	/**
	 * Clones elem with initialiser and compares elem with its clone.
	 * They are expected to be similar.
	 */
	public void sameX(EObject elem, EObjectInitialiser initialiser) {
		var objOne = initialiser.clone(elem);
		var objTwo = initialiser.clone(objOne);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"Unexpected clone comparison result, " +
				this.getEcoreComparisonMessage(objOne, objTwo));
	}
	
	/**
	 * Compares elem1 with elem2, expects them to be different (not similar).
	 */
	public void differentX(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
		this.compareX(elem1, elem2, initialiser, Boolean.FALSE);
	}
	
	/**
	 * Compares elem1 with elem2, expects the similarity result to be the
	 * same with the given expected value.
	 */
	public void compareX(EObject elem1, EObject elem2, EObjectInitialiser initialiser, Boolean expectedSimilarityResult) {
		var objOne = initialiser.clone(elem1);
		var objTwo = initialiser.clone(elem2);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertEquals(expectedSimilarityResult,
				this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"Unexpected comparison result, " +
				this.getEcoreComparisonMessage(objOne, objTwo));
	}

	/**
	 * Combines {@link #sameX(EObject, EObjectInitialiser)} and {@link #compareX(EObject, EObject, Boolean)}
	 */
	public void testX(EObject elem1, EObject elem2, EObjectInitialiser initialiser, Boolean expectedSimilarityResult) {
		this.sameX(elem1, initialiser);
		this.sameX(elem2, initialiser);
		
		this.compareX(elem1, elem2, initialiser, expectedSimilarityResult);
		this.compareX(elem2, elem1, initialiser, expectedSimilarityResult);
	}
	
	public void testX(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
		this.sameX(elem1, initialiser);
		this.sameX(elem2, initialiser);
		
		this.compareX(elem1, elem2, initialiser, false);
		this.compareX(elem2, elem1, initialiser, false);
	}
	
	// TODO: If needed, uncomment and clone all EObjects before using them
//	public void listSameXSameOrder(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
//		var obj11 = elem1;
//		var obj11Copy = initialiser.clone(obj11);
//		
//		var obj12 = elem2;
//		var obj12Copy = initialiser.clone(obj12);
//		
//		var list1 = List.of(obj11, obj12);
//		var list2 = List.of(obj11Copy, obj12Copy);
//		
//		var resOne = this.createResource(list1);
//		var resTwo = this.createResource(list2);
//		
//		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()),
//				"Unexpected same list same order comparison, " +
//				this.getEcoreListComparisonMessage(list1, list2));
//	}
//	
//	/**
//	 * Tests whether the order in the list matters
//	 * <br><br>
//	 * As of 14.04.2024, it should matter, as the elements are
//	 * to be compared pairwise.
//	 */
//	public void listSameXDifferentOrder(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
//		var obj11 = elem1;
//		var obj11Copy = initialiser.clone(obj11);
//		
//		var obj12 = elem2;
//		var obj12Copy = initialiser.clone(obj12);
//		
//		var list1 = List.of(obj11, obj12);
//		var list2 = List.of(obj12Copy, obj11Copy);
//		
//		var resOne = this.createResource(list1);
//		var resTwo = this.createResource(list2);
//		
//		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()),
//				"Unexpected same list different order comparison, " +
//				this.getEcoreListComparisonMessage(list1, list2));
//	}
//	
//	public void listDifferentX(List<? extends EObject> elems1, List<? extends EObject> elems2) {
//		var resOne = this.createResource(elems1);
//		var resTwo = this.createResource(elems2);
//		
//		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()),
//				"Unexpected different list comparison, " +
//				this.getEcoreListComparisonMessage(elems1, elems2));
//	}
}
