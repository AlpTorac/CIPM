/**
 * Contains unit tests for {@link EObject} classes/interfaces, which are
 * non-terminal in the {@link Commentable} type hierarchy, i.e. they have
 * sub-classes/-interfaces. In the said tests, {@link EObject} instances are
 * constructed programmatically and are checked for similarity. <br>
 * <br>
 * Since non-terminal elements within the {@link Commentable} type hierarchy can
 * have multiple sub-classes/-interfaces, all tests should be parameterised over
 * initialisers. Those initialisers may have to be adapted, if there are
 * sub-classes/-interfaces, which require additional setup steps to be "valid".
 * Parameterising such tests help spare test code, as sub-classes/-interfaces
 * all need their mutual attributes to be tested. <br>
 * <br>
 * The tests within this package are meant to be minimal and only to test
 * similarity checking with respect to individual attributes of {@link EObject}
 * instances, in an isolated fashion. This means, each test performs similarity
 * checking on 2 {@link EObject} instances, whose attributes are equal except
 * for one of them. This way, one can pinpoint basic similarity checking errors
 * regarding certain attributes of certain {@link EObject} implementors.<br>
 * <br>
 * It is highly recommended to make the construction of the "main"
 * {@link EObject} instances as obvious and visible as possible, as their
 * construction can get complicated and not knowing all the construction steps
 * can lead to tests not fulfilling their goal. Furthermore, it is also
 * important to adapt the initialisers, rather than using instanceof/if-blocks
 * to define type-specific behaviour. Should certain sub-classes/-interfaces
 * diverge from their super type so much that they are incompatible with
 * parameterised tests, it is suggested to instead define special unit tests for
 * the said sub-classes/-interfaces.<br>
 * <br>
 * See {@link cipm.consistency.fitests.similarity.jamopp.unittests} to find out
 * more about what test methods do.
 */
package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;