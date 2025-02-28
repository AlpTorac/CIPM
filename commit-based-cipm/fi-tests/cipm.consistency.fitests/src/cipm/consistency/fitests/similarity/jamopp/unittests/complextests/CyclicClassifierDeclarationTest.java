package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.emftext.language.java.imports.ImportsPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.initialisers.jamopp.containers.CompilationUnitInitialiser;
import cipm.consistency.initialisers.jamopp.containers.PackageInitialiser;
import cipm.consistency.initialisers.jamopp.generics.TypeParameterInitialiser;
import cipm.consistency.initialisers.jamopp.imports.IImportInitialiser;
import cipm.consistency.initialisers.jamopp.types.ClassifierReferenceInitialiser;

/**
 * Contains tests that check the robustness of similarity checking, in the face
 * of classifiers, whose declaration forces them to import each other. Here,
 * type parameters are preferred to test this.
 * 
 * @author Alp Torac Genc
 */
public class CyclicClassifierDeclarationTest extends AbstractJaMoPPSimilarityTest {
	/**
	 * @return All {@link ConcreteClassifier} implementor types.
	 */
	private static Stream<Arguments> genCls() {
		return AbstractJaMoPPSimilarityTest.getNonAdaptedInitialiserArgumentsFor(IConcreteClassifierInitialiser.class);
	}

	/**
	 * @return All possible combinations of {@link ConcreteClassifier} types with
	 *         {@link Import} types.
	 */
	private static Stream<Arguments> genClsXImport() {
		var res = new ArrayList<Arguments>();
		var clsfierInits = AbstractJaMoPPSimilarityTest
				.getNonAdaptedInitialisersFor(IConcreteClassifierInitialiser.class);
		var impInits = AbstractJaMoPPSimilarityTest.getNonAdaptedInitialisersFor(IImportInitialiser.class);
		for (var cInit : clsfierInits) {
			for (var iInit : impInits) {
				res.add(Arguments.of(cInit, iInit,
						String.format("Classifier type: %s, Import type: %s",
								cInit.getInstanceClassOfInitialiser().getSimpleName(),
								iInit.getInstanceClassOfInitialiser().getSimpleName())));
			}
		}
		return res.stream();
	}

	/**
	 * Ensures that similarity checking handles classifiers with type parameters
	 * that are forced to depend on one another: <br>
	 * <br>
	 * {@code cls1<T1 extends cls2>} <br>
	 * {@code cls2<T2 extends cls1>}
	 * 
	 * @param cInit       The initialiser that will instantiate the classifiers,
	 *                    which will be imported
	 * @param displayName The display of this test
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("genCls")
	public void testCyclicTypeParameter(IConcreteClassifierInitialiser cInit, String displayName) {
		var cls1 = cInit.instantiate();
		cInit.setName(cls1, "cls1");
		var cls2 = cInit.instantiate();
		cInit.setName(cls2, "cls2");

		var clsRefInit = new ClassifierReferenceInitialiser();
		var cls1Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls1Ref, cls1);
		var cls2Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls2Ref, cls2);

		var tpInit = new TypeParameterInitialiser();
		var t1 = tpInit.instantiate();
		tpInit.addExtendType(t1, cls1Ref);
		var t2 = tpInit.instantiate();
		tpInit.addExtendType(t2, cls2Ref);

		cInit.addTypeParameter(cls1, t2);
		cInit.addTypeParameter(cls2, t1);

		this.testSimilarity(cls1, cls2, false);
		this.testSimilarity(cls1Ref, cls2Ref, false);
		this.testSimilarity(t1, t2, false);
	}

	/**
	 * Ensures that similarity checking handles importing classifiers with type
	 * parameters that are forced to depend on one another: <br>
	 * <br>
	 * {@code cls1<T1 extends cls2>} <br>
	 * {@code cls2<T2 extends cls1>} <br>
	 * <br>
	 * cls1 imports cls2, cls2 imports cls1
	 * 
	 * @param cInit       The initialiser that will instantiate the classifiers,
	 *                    which will be imported
	 * @param iInit       The initialiser that will instantiate the said imports
	 * @param displayName The display of this test
	 */
	@ParameterizedTest(name = "{2}")
	@MethodSource("genClsXImport")
	public void testCyclicImport_WithoutContainer(IConcreteClassifierInitialiser cInit, IImportInitialiser iInit,
			String displayName) {
		var cls1 = cInit.instantiate();
		cInit.setName(cls1, "cls1");
		var cls2 = cInit.instantiate();
		cInit.setName(cls2, "cls2");

		var clsRefInit = new ClassifierReferenceInitialiser();
		var cls1Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls1Ref, cls1);
		var cls2Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls2Ref, cls2);

		var tpInit = new TypeParameterInitialiser();
		var t1 = tpInit.instantiate();
		tpInit.addExtendType(t1, cls1Ref);
		var t2 = tpInit.instantiate();
		tpInit.addExtendType(t2, cls2Ref);

		cInit.addTypeParameter(cls1, t2);
		cInit.addTypeParameter(cls2, t1);

		this.testSimilarity(cls1, cls2, false);
		this.testSimilarity(cls1Ref, cls2Ref, false);
		this.testSimilarity(t1, t2, false);

		var imp1 = iInit.instantiate();
		iInit.setClassifier(imp1, cls1);
		var imp2 = iInit.instantiate();
		iInit.setClassifier(imp2, cls2);
		this.testSimilarity(imp1, imp2, this.getExpectedSimilarityResult(iInit.getInstanceClassOfInitialiser(),
				ImportsPackage.Literals.IMPORT__CLASSIFIER));
	}

	/**
	 * Ensures that similarity checking handles importing classifiers (in their own
	 * packages) with type parameters that are forced to depend on one another: <br>
	 * <br>
	 * {@code cls1<T1 extends cls2>} <br>
	 * {@code cls2<T2 extends cls1>} <br>
	 * <br>
	 * p1.cls1 imports p2.cls2, p2.cls2 imports p1.cls1
	 * 
	 * @param cInit       The initialiser that will instantiate the classifiers,
	 *                    which will be imported
	 * @param iInit       The initialiser that will instantiate the said imports
	 * @param displayName The display of this test
	 */
	@ParameterizedTest(name = "{2}")
	@MethodSource("genClsXImport")
	public void testCyclicImport_WithPackage(IConcreteClassifierInitialiser cInit, IImportInitialiser iInit,
			String displayName) {
		var cls1 = cInit.instantiate();
		cInit.setName(cls1, "cls1");
		var cls2 = cInit.instantiate();
		cInit.setName(cls2, "cls2");

		var clsRefInit = new ClassifierReferenceInitialiser();
		var cls1Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls1Ref, cls1);
		var cls2Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls2Ref, cls2);

		var tpInit = new TypeParameterInitialiser();
		var t1 = tpInit.instantiate();
		tpInit.addExtendType(t1, cls1Ref);
		var t2 = tpInit.instantiate();
		tpInit.addExtendType(t2, cls2Ref);

		cInit.addTypeParameter(cls1, t2);
		cInit.addTypeParameter(cls2, t1);

		this.testSimilarity(cls1, cls2, false);
		this.testSimilarity(cls1Ref, cls2Ref, false);
		this.testSimilarity(t1, t2, false);

		var pInit = new PackageInitialiser();
		var p1 = pInit.instantiate();
		pInit.addNamespace(p1, "p1");
		pInit.addClassifier(p1, cls1);
		var p2 = pInit.instantiate();
		pInit.addNamespace(p2, "p2");
		pInit.addClassifier(p2, cls2);

		this.testSimilarity(p1, p2, false);

		var imp1 = iInit.instantiate();
		iInit.addNamespaces(imp1, p1.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp1, cls1);
		var imp2 = iInit.instantiate();
		iInit.addNamespaces(imp2, p2.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp2, cls2);

		this.testSimilarity(imp1, imp2, false);
	}

	/**
	 * Ensures that similarity checking handles importing classifiers (in their own
	 * compilation unit) with type parameters that are forced to depend on one
	 * another: <br>
	 * <br>
	 * {@code cls1<T1 extends cls2>} <br>
	 * {@code cls2<T2 extends cls1>} <br>
	 * <br>
	 * Both classifiers cls1 and cls2 are stored in their own compilation units.
	 * <br>
	 * <br>
	 * cls1 imports cls2, cls2 imports cls1
	 * 
	 * @param cInit       The initialiser that will instantiate the classifiers,
	 *                    which will be imported
	 * @param iInit       The initialiser that will instantiate the said imports
	 * @param displayName The display of this test
	 */
	@ParameterizedTest(name = "{2}")
	@MethodSource("genClsXImport")
	public void testCyclicImport_WithCompilationUnit(IConcreteClassifierInitialiser cInit, IImportInitialiser iInit,
			String displayName) {
		var cls1 = cInit.instantiate();
		cInit.setName(cls1, "cls1");
		var cls2 = cInit.instantiate();
		cInit.setName(cls2, "cls2");

		var clsRefInit = new ClassifierReferenceInitialiser();
		var cls1Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls1Ref, cls1);
		var cls2Ref = clsRefInit.instantiate();
		clsRefInit.setTarget(cls2Ref, cls2);

		var tpInit = new TypeParameterInitialiser();
		var t1 = tpInit.instantiate();
		tpInit.addExtendType(t1, cls1Ref);
		var t2 = tpInit.instantiate();
		tpInit.addExtendType(t2, cls2Ref);

		cInit.addTypeParameter(cls1, t2);
		cInit.addTypeParameter(cls2, t1);

		this.testSimilarity(cls1, cls2, false);
		this.testSimilarity(cls1Ref, cls2Ref, false);
		this.testSimilarity(t1, t2, false);

		var cuInit = new CompilationUnitInitialiser();
		var cu1 = cuInit.instantiate();
		cuInit.addNamespace(cu1, "cu1");
		cuInit.addClassifier(cu1, cls1);
		var cu2 = cuInit.instantiate();
		cuInit.addNamespace(cu2, "cu2");
		cuInit.addClassifier(cu2, cls2);

		this.testSimilarity(cu1, cu2, false);

		var imp1 = iInit.instantiate();
		iInit.addNamespaces(imp1, cu1.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp1, cls1);
		var imp2 = iInit.instantiate();
		iInit.addNamespaces(imp2, cu2.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp2, cls2);

		this.testSimilarity(imp1, imp2, false);
	}
}
