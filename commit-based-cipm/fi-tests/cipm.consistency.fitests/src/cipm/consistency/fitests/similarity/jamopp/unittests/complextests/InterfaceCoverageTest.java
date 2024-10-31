package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
 * A test class, which ensures that all interfaces extending {@link Commentable}
 * are addressed by similarity checking. <br>
 * <br>
 * Note: These tests may include cases that are not currently addressed.
 * 
 * @author Alp Torac Genc
 */
public class InterfaceCoverageTest extends AbstractJaMoPPSimilarityTest {
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
	 * @return The {@link EClass} corresponding to the class represented by cls.
	 *         Null, if no such {@link EClass} is found under {@link JavaPackage}.
	 */
	private EClass getEClassFor(Class<?> cls) {
		var ePacs = JavaPackage.eINSTANCE.getESubpackages();
		for (var ePac : ePacs) {
			var eClss = ePac.getEClassifiers();
			for (var eCls : eClss) {
				if (eCls.getInstanceClass().equals(cls)) {
					return (EClass) eCls;
				}
			}
		}
		return null;
	}

	/**
	 * Makes sure that all interfaces extending {@link Commentable} are addressed by
	 * similarity checking, i.e. computing the similarity of 2 mocked instances of
	 * cls returns true.
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

		var clsMock1 = mock(cls);
		when(clsMock1.eClass()).thenReturn(this.getEClassFor(cls));
		var clsMock2 = mock(cls);
		when(clsMock2.eClass()).thenReturn(this.getEClassFor(cls));

		Assertions.assertTrue(this.isSimilar(clsMock1, clsMock2));
	}
}
