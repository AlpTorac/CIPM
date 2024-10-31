package cipm.consistency.fitests.similarity.jamopp.unittests.mocktests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.JavaPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;

/**
 * A test class, which ensures that all instances of all Java element types
 * present in {@link JavaPackage} are addressed by similarity checking. <br>
 * <br>
 * Note: These tests may include cases that are not currently addressed.
 * 
 * @author Alp Torac Genc
 */
public class InterfaceCoverageTest extends AbstractJaMoPPSimilarityTest implements IMockTest {
	private static Stream<Arguments> genTestParams() {
		return getAllClasses().stream().map(Arguments::of);
	}

	/**
	 * @return All types accessible under the sub-packages of {@link JavaPackage} in
	 *         form of {@link EClass}, whose instance class
	 *         {@code eClass.getInstanceClass()} will be in the return value.
	 */
	private static Collection<Class<?>> getAllClasses() {
		var res = new ArrayList<Class<?>>();
		getAllEClasses().forEach((eCls) -> res.add(eCls.getInstanceClass()));
		return res;
	}

	/**
	 * @return All {@link EClass}es accessible under the sub-packages of
	 *         {@link JavaPackage}.
	 */
	private static Collection<EClass> getAllEClasses() {
		var res = new ArrayList<EClass>();
		var ePacs = JavaPackage.eINSTANCE.getESubpackages();
		ePacs.forEach((pac) -> pac.getEClassifiers().stream().filter((eClsf) -> eClsf instanceof EClass)
				.forEach((c) -> res.add((EClass) c)));
		return res;
	}

	/**
	 * Makes sure that all types that are present in {@link JavaPackage} are
	 * addressed by similarity checking, i.e. computing the similarity of 2 mocked
	 * instances of cls returns true.
	 * 
	 * @param cls The type extending {@link EObject} that will be mocked.
	 */
	@ParameterizedTest
	@MethodSource("genTestParams")
	public void testInterfaceCoverage(Class<? extends EObject> cls) {
		/*
		 * Mock the given class and make sure that the mocks return their corresponding
		 * EClass, so that method calls till reaching similarity checking process do not
		 * cause Null Pointer Exceptions.
		 */

		var clsMock1 = this.mockEObject(cls);
		var clsMock2 = this.mockEObject(cls);

		Assertions.assertTrue(this.isSimilar(clsMock1, clsMock2));
	}
}
