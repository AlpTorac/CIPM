package cipm.consistency.fitests.similarity.jamopp.unittests.mocktests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.JavaPackage;

/**
 * An interface that provides methods for tests that use mock elements to
 * increase test coverage.
 * 
 * @author Alp Torac Genc
 */
public interface IMockTest {
	/**
	 * TODO Move to JaMoPPHelper in the future (use this as delegation)
	 * 
	 * @param cls The type of the Java element, whose {@link EClass} will be
	 *            returned, if cls is the type of a Java element.
	 * 
	 * @return The {@link EClass} corresponding to the class represented by cls.
	 *         Null, if no such {@link EClass} is found under {@link JavaPackage}.
	 */
	public default EClass getEClassForJavaElement(Class<?> cls) {
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
	 * Mocks an {@link EObject} sub-type and overrides the
	 * {@code mockedClass.eClass()} method, so that the corresponding similarity
	 * checking mechanism can be found without throwing null pointer exceptions.
	 * 
	 * @param <T>       The type of the mock that will be returned
	 * @param clsToMock The class that will be mocked
	 * @return A mock that is an instance of the given class
	 */
	public default <T extends EObject> T mockEObject(Class<T> clsToMock) {
		var mockedClass = mock(clsToMock);
		when(mockedClass.eClass()).thenReturn(this.getEClassForJavaElement(clsToMock));
		return mockedClass;
	}

	/**
	 * Mocks the given class and overrides its eContainer with the given container,
	 * so that {@code mock.eContainer() == container}.
	 * 
	 * @return A mock of the given class, which is an instance of that class.
	 * 
	 * @see {@link #mockEObject(Class)}
	 */
	public default <T extends EObject> T mockEObjectWithContainer(Class<T> clsToMock, EObject container) {
		var mockedClass = this.mockEObject(clsToMock);
		when(mockedClass.eContainer()).thenReturn(container);
		return mockedClass;
	}
}
