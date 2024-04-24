package cipm.consistency.fitests.similarity.java;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;

/**
 * Contains re-usable test code to spare code duplication.
 * 
 * @author atora
 */
public class EObjectSimilarityTest extends AbstractSimilarityTest {
	public void sameX(EObject elem, EObjectInitialiser initialiser) {
		var objOne = elem;
		var objTwo = initialiser.clone(objOne);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	public void differentX(EObject elem1, EObject elem2) {
		var objOne = elem1;
		var objTwo = elem2;
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}

	public void listSameXSameOrder(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
		var obj11 = elem1;
		var obj21 = initialiser.clone(obj11);
		
		var obj12 = elem2;
		var obj22 = initialiser.clone(obj12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj21, obj22);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	/**
	 * Tests whether the order in the list matters
	 * <br><br>
	 * As of 14.04.2024, it should matter, as the elements are
	 * to be compared pairwise.
	 */
	public void listSameXDifferentOrder(EObject elem1, EObject elem2, EObjectInitialiser initialiser) {
		var obj11 = elem1;
		var obj21 = initialiser.clone(obj11);
		
		var obj12 = elem2;
		var obj22 = initialiser.clone(obj12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj22, obj21);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	public void listDifferentX(List<? extends EObject> elems1, List<? extends EObject> elems2) {
		var resOne = this.createResource(elems1);
		var resTwo = this.createResource(elems2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
