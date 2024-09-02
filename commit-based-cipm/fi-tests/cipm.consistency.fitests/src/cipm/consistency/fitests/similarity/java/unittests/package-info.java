/**
 * Contains interfaces that can be implemented by tests, where initialisers are
 * used to instantiate {@link EObject} instances. Each initialiser in this
 * package offers default methods that help encapsulate the creation of
 * {@link EObject} instances, which are needed in tests. <br>
 * <br>
 * It is recommended to only use the interfaces in this package to instantiate
 * {@link EObject} instances that are needed to setup the "main" {@link EObject}
 * instances of the tests. Otherwise, the creation of "main" {@link EObject}
 * instances in the tests can be heavily convoluted and this could lead to one
 * losing the overview. Moreover, doing so would also lead to the creation of
 * "main" {@link EObject} instances depending on methods, which are merely meant
 * to offer utility. <br>
 * <br>
 * While adding further interfaces to this package, it is suggested to check the
 * existing interfaces and to extend them, if possible. Doing so will help keep
 * consistency across test cases and may spare re-inventing the wheel. <br>
 * <br>
 * The sub-packages of this package contain unit tests for {@link EObject}
 * classes/interfaces using initialisers.
 */
package cipm.consistency.fitests.similarity.java.unittests;