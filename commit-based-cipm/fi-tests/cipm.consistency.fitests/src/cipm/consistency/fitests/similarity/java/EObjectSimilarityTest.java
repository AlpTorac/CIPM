package cipm.consistency.fitests.similarity.java;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.junit.jupiter.api.Assertions;

/**
 * Contains re-usable test code to spare code duplication.
 * 
 * @author atora
 */
public class EObjectSimilarityTest extends AbstractSimilarityTest {
	public <T extends EObject> T cloneEObj(T obj) {
		return EcoreUtil.copy(obj);
	}
	
	public <T extends EObject> Collection<T> cloneEObjList(Collection<T> objs) {
		return EcoreUtil.copyAll(objs);
	}
	
	public boolean getActualEquality(EObject elem1, EObject elem2) {
		return EcoreUtil.equals(elem1, elem2);
	}
	
	public boolean getActualEquality(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		return EcoreUtil.equals(elems1, elems2);
	}
	
	/**
	 * Clones elem and compares it with its clone.
	 * They are expected to be similar.
	 */
	public void sameX(EObject elem) {
		this.compareX(elem, elem, Boolean.TRUE);
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
		if (!expectedSimilarityResult.booleanValue() &&
				this.getActualEquality(elem1, elem2)) {
			this.getLogger().debug("Elements are expected to be different"+
				" in "+this.getResourceFileTestIdentifier()+" but are similar according to EcoreUtil");
		}
		
		var objOne = this.cloneEObj(elem1);
		var objTwo = this.cloneEObj(elem2);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertEquals(expectedSimilarityResult,
				this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + this.getActualEquality(objOne, objTwo));
	}

	public void testX(EObject elem1, EObject elem2, Boolean expectedSimilarityResult) {
		this.sameX(elem1);
		this.sameX(elem2);
		
		this.compareX(elem1, elem2, expectedSimilarityResult);
		this.compareX(elem2, elem1, expectedSimilarityResult);
	}
	
	public void listSameXSameOrder(EObject elem1, EObject elem2) {
		var obj11 = this.cloneEObj(elem1);
		var obj11Copy = this.cloneEObj(obj11);
		
		var obj12 = this.cloneEObj(elem2);
		var obj12Copy = this.cloneEObj(obj12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj11Copy, obj12Copy);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + this.getActualEquality(list1, list2));
	}
	
	/**
	 * Tests whether the order in the list matters
	 * <br><br>
	 * As of 14.04.2024, it should matter, as the elements are
	 * to be compared pairwise.
	 */
	public void listSameXDifferentOrder(EObject elem1, EObject elem2) {
		var obj11 = this.cloneEObj(elem1);
		var obj11Copy = this.cloneEObj(obj11);
		
		var obj12 = this.cloneEObj(elem2);
		var obj12Copy = this.cloneEObj(obj12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj12Copy, obj11Copy);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + this.getActualEquality(list1, list2));
	}
	
	public void listDifferentX(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		var resOne = this.createResource(elems1);
		var resTwo = this.createResource(elems2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()),
				"EcoreUtil comparison result: " + this.getActualEquality(elems1, elems2));
	}
}
