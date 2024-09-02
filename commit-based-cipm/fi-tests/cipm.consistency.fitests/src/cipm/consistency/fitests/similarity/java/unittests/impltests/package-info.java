/**
 * Contains unit tests for {@link EObject} classes/interfaces, which are
 * terminal in the {@link Commentable} type hierarchy, i.e. they have no further
 * sub-classes/-interfaces. In the said tests, {@link EObject} instances are
 * constructed programmatically and are checked for similarity. <br>
 * <br>
 * The tests within this package are meant to be minimal and only test the
 * individual attributes of {@link EObject} instances isolated. <br>
 * <br>
 * It is highly recommended to make the construction of the "main"
 * {@link EObject} instances as obvious and visible as possible, as their
 * construction can get complicated and not knowing all the construction steps
 * can lead to tests not fulfilling their goal.
 */
package cipm.consistency.fitests.similarity.java.unittests.impltests;