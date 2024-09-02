/**
 * Contains unit tests for {@link EObject} classes/interfaces, which are
 * non-terminal in the {@link Commentable} type hierarchy, i.e. they have
 * sub-classes/-interfaces. In the said tests, {@link EObject} instances are
 * constructed programmatically and are checked for similarity. <br>
 * <br>
 * Since non-terminal elements within the {@link Commentable} type hierarchy can
 * have multiple sub-classes/-interfaces, all tests should be parameterized over
 * inits. Those inits may have to be adapted, if there are
 * sub-classes/-interfaces, which require additional setup steps to be "valid".
 * Parameterizing such tests help spare test code, as sub-classes/-interfaces
 * all need their mutual attributes to be tested. <br>
 * <br>
 * The tests within this package are meant to be minimal and only test the
 * individual attributes of {@link EObject} instances isolated. <br>
 * <br>
 * In order to supply init parameters to the parameterized tests, XParam classes
 * that implement {@link ArgumentsProvider} can be declared and used with the
 * {@link ArgumentsSource} annotation. Elements declared within
 * {@link cipm.consistency.fitests.similarity.java.params} can be used in the
 * implementation of the XParam classes to keep the consistency. <br>
 * <br>
 * It is highly recommended to make the construction of the "main"
 * {@link EObject} instances as obvious and visible as possible, as their
 * construction can get complicated and not knowing all the construction steps
 * can lead to tests not fulfilling their goal. Furthermore, it is also
 * important to adapt the inits, rather than using instanceof/if-blocks to
 * define type-specific behaviour. Should certain sub-classes/-interfaces
 * diverge from their super type so much that they are incompatible with
 * parameterized tests, it is suggested to instead define special unit tests for
 * the said sub-classes/-interfaces.
 */
package cipm.consistency.fitests.similarity.java.unittests.interfacetests;