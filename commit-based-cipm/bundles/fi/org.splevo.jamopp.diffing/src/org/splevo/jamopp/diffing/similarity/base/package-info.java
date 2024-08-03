/**
 * This package contains interfaces and abstract classes that can be used to
 * implement similarity checking. An overview of the similarity checking
 * process, as well as the roles of individual elements can be found below. <br>
 * <br>
 * The similarity checking process begins with the similarity checker
 * {@link ISimilarityChecker}, which serves as a facade to outside. It
 * translates the incoming similarity checking calls to similarity checking
 * request(s), i.e. {@link ISimilarityRequest}. It then delegates those requests
 * to its {@link ISimilarityComparer}, which serves as a layer of indirection
 * between {@link ISimilarityChecker} and the similarity checking logic. The
 * purpose of {@link ISimilarityComparer} is to keep {@link ISimilarityChecker}
 * as clean and simple as possible, since it is supposed to serve as a facade.
 * {@link ISimilarityComparer} delegates {@link ISimilarityRequest} instances
 * delegated to it to the similarity checking "tools", which can be implemented
 * in form of {@link ISimilarityRequestHandler} instances. <br>
 * <br>
 * {@link ISimilarityToolbox} contains {@link ISimilarityRequestHandler}
 * instances, which are meant to handle the incoming {@link ISimilarityRequest}
 * instances. It is responsible for organising {@link ISimilarityRequestHandler}
 * instances in a way that the {@link ISimilarityRequestHandler} instance(s)
 * corresponding to the incoming {@link ISimilarityRequest} instances can be
 * passed to the corresponding {@link ISimilarityRequestHandler} instances.
 * Because of this, the {@link ISimilarityToolbox} itself is also a
 * {@link ISimilarityRequestHandler}. Likewise, {@link ISimilarityComparer} is
 * also a {@link ISimilarityRequestHandler}. <br>
 * <br>
 * Further similarity checking tools can be added to {@link ISimilarityToolbox}
 * dynamically in form of ({@link ISimilarityRequest} class,
 * {@link ISimilarityRequestHandler} instance) pairs (or request-handler pairs).
 * The request-handler pairs can also be removed dynamically. To build a
 * {@link ISimilarityToolbox} instance, one can use
 * {@link ISimilarityToolboxBuilder}. {@link ISimilarityToolboxBuilder}
 * encapsulates the means to construct {@link ISimilarityToolbox} instances
 * dynamically and it may also provide "standard" building methods to bundle
 * certain request-handler pairs to ease the building process. Since there are
 * many ways to implement {@link ISimilarityToolbox}, one needs to pass a
 * {@link ISimilarityToolboxFactory} to the {@link ISimilarityToolboxBuilder}.
 * {@link ISimilarityToolboxFactory} is used to create the initial instance of
 * the {@link ISimilarityToolbox}, which can then be filled with either the
 * {@link ISimilarityToolboxBuilder} or manually. While implementing
 * {@link ISimilarityRequestHandler}, giving them a reference to
 * {@link ISimilarityToolbox} will allow them to create further
 * {@link ISimilarityRequest} instances. Doing so allows re-using code and
 * creating chains of requests, which can help separate concerns better. <br>
 * <br>
 * From a design standpoint, using {@link ISimilarityToolbox} helps centralise
 * similarity checking operations and makes them easier to modify. Furthermore,
 * dynamic modifications are also made possible with this approach, as
 * request-handler pairs can be changed during runtime. Outside parameters that
 * are only relevant for certain operations can also be stored within the
 * relevant {@link ISimilarityRequestHandler} instances. Doing so frees
 * similarity checking mechanisms from storing all such parameters and stops
 * them from getting dragged around (as constructor parameters, getter/setter
 * methods, etc.). It is therefore important to extract such
 * parameters/variables into {@link ISimilarityRequestHandler} implementors
 * whenever plausible. Not abiding this can easily pollute classes implementing
 * similarity checking logic with those parameters, even though they have no
 * purpose being in them. This in return can bloat the said classes'
 * constructors and/or the classes themselves with setter/getter methods for the
 * said parameters.
 */
package org.splevo.jamopp.diffing.similarity.base;