/**
 * Contains interfaces for initialiser implementations (such as
 * {@link cipm.consistency.fitests.similarity.java.initialiser.IInitialiser} as
 * well as for classes that can be used to adapt them. Also has an interface
 * {@link cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage},
 * which can be implemented to access initialiser interfaces and classes easier.
 * <br>
 * <br>
 * Initialisers are classes/interfaces, whose purpose is to instantiate,
 * initialise and modify certain objects. What objects they are meant for is
 * denoted by the name of the individual initialiser. Defining initialisers
 * similar to their designated objects, may help making the initialisers more
 * flexible and can ease adding further initialisers later on. It is strongly
 * recommended to define atomic modification methods in initialisers and reusing
 * them, rather than defining complex modification methods. <br>
 * <br>
 * In order to make parameterized tests using initialisers as parameters
 * possible, one can use
 * {@link cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy}
 * to augment certain initialisers in ways that lets them be used in such tests
 * without throwing certain exceptions. The most common cause of such exceptions
 * is initialisers not setting any required attributes, while instantiating
 * objects. In such cases, the said adapters can be used to have them set those
 * attributes, so that using the instances in tests do not throw exceptions, due
 * to their essential attributes not being set. <br>
 * <br>
 * {@link cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage}
 * defines a nestable structure that enables finding the proper initialisers as
 * well as certain groups of initialisers. It is recommended to implement that
 * interface for each package containing initialisers and to add all
 * initialisers to that implementation.
 */
package cipm.consistency.fitests.similarity.java.initialiser;