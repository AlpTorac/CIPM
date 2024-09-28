/**
 * Contains interfaces that can be implemented by tests, where initialisers are
 * used to instantiate {@link EObject} implementors present in EMFText. Each
 * initialiser in this package offers default methods that help encapsulate the
 * creation of such {@link EObject} instances, which are needed in tests. <br>
 * <br>
 * It is recommended to only use the interfaces in this package to instantiate
 * {@link EObject} instances that are needed to setup the "main" {@link EObject}
 * instances under test (i.e. instances used in similarity checking methods).
 * The creation of the "main" EObject instances should be done within the
 * corresponding test class explicitly via initialisers, in order to avoid
 * implicit steps as much as possible. <br>
 * <br>
 * While adding further interfaces to this package, it is suggested to check the
 * existing interfaces and to extend them, if possible. Doing so will help keep
 * consistency across test cases and may spare re-inventing the wheel. <br>
 * <br>
 * The sub-packages of this package contain unit tests for {@link EObject}
 * classes/interfaces using initialisers. For each attribute X of EObject
 * implementor/interface EO, there are currently up to 3 test categories. Let
 * eo1 and eo2 be instances of type EO, then test categories do the following:
 * <ul>
 * <li><b>testX</b>: Performs similarity checking on eo1 and eo2, whose
 * attributes are all equal except for X, and checks the result.
 * <li><b>testXSize</b>: Same as testX, where X's value is an array and the
 * corresponding arrays in eo1 and eo2 contain different amounts of elements.
 * <li><b>testXNullCheck</b>: Same as testX, where eo1's X is set and eo2's X is
 * not.
 * </ul>
 */
package cipm.consistency.fitests.similarity.emftext.unittests;