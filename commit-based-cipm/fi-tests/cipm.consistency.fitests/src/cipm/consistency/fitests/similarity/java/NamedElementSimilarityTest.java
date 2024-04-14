package cipm.consistency.fitests.similarity.java;

import java.util.List;
import java.util.stream.Stream;

import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.java.initialiser.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ModuleInitialiser;

public class NamedElementSimilarityTest extends AbstractSimilarityTest {
	private final String name11 = "name11";
	private final String name12 = "name12";
	
	private final String name21 = "name21";
	private final String name22 = "name22";
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamedElementSimilarityTest.class.getSimpleName());
		super.setUp();
	}
	
	private static Stream<Arguments> generateNameSimilarityParams() {
		return Stream.of(
					Arguments.of(new ModuleInitialiser()),
//					Arguments.of(new PackageInitialiser()),
					Arguments.of(new CompilationUnitInitialiser())
				);
	}
	
	protected NamedElement initElement(INamedElementInitialiser initialiser, String name) {
		var result = initialiser.instantiate();
		initialiser.initialiseName(result, name);
		return result;
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testSameReference(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameReference");
		
		var objOne = this.initElement(initialiser, name11);
		var resOne = this.createResource(List.of(objOne));
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testSameName(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameName");
		
		var objOne = this.initElement(initialiser, name11);
		var objTwo = this.initElement(initialiser, name11);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testDifferentName(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testDifferentName");
		
		var objOne = this.initElement(initialiser, name11);
		var objTwo = this.initElement(initialiser, name22);
		
		var resOne = this.createResource(List.of(objOne));
		var resTwo = this.createResource(List.of(objTwo));
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testListSameReference(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListSameReference");
		
		var obj11 = this.initElement(initialiser, name11);
		var obj12 = this.initElement(initialiser, name12);
		
		var list1 = List.of(obj11, obj12);
		
		var resOne = this.createResource(list1);
		
		Assertions.assertTrue(this.areSimilar(resOne.getContents(), resOne.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testListSameNamesSameOrder(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListSameNamesSameOrder");
		
		var obj11 = this.initElement(initialiser, name11);
		var obj21 = this.initElement(initialiser, name11);
		
		var obj12 = this.initElement(initialiser, name12);
		var obj22 = this.initElement(initialiser, name12);
		
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
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testListSameNamesDifferentOrder(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListSameNamesDifferentOrder");
		
		var obj11 = this.initElement(initialiser, name11);
		var obj21 = this.initElement(initialiser, name11);
		
		var obj12 = this.initElement(initialiser, name12);
		var obj22 = this.initElement(initialiser, name12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj22, obj21);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testListDifferentNames(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListDifferentNames");
		
		var obj11 = this.initElement(initialiser, name11);
		var obj12 = this.initElement(initialiser, name12);
		
		var obj21 = this.initElement(initialiser, name21);
		var obj22 = this.initElement(initialiser, name22);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj21, obj22);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testListLeftSubset(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListLeftSubset");
		
		var obj11 = this.initElement(initialiser, name11);
		var obj21 = this.initElement(initialiser, name11);
		
		var obj12 = this.initElement(initialiser, name12);
		
		var list1 = List.of(obj11);
		var list2 = List.of(obj21, obj12);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
	
	@ParameterizedTest
	@MethodSource("generateNameSimilarityParams")
	public void testListRightSubset(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testListRightSubset");
		
		var obj11 = this.initElement(initialiser, name11);
		var obj21 = this.initElement(initialiser, name11);
		
		var obj12 = this.initElement(initialiser, name12);
		
		var list1 = List.of(obj11, obj12);
		var list2 = List.of(obj21);
		
		var resOne = this.createResource(list1);
		var resTwo = this.createResource(list2);
		
		Assertions.assertFalse(this.areSimilar(resOne.getContents(), resTwo.getContents()));
	}
}
