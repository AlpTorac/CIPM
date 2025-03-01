package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.types.ClassifierReference;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

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
	 * Ensures that similarity checking handles classifiers with type parameters
	 * that are forced to depend on one another: <br>
	 * <br>
	 * {@code cls1<T1 extends cls2>} <br>
	 * {@code cls2<T2 extends cls1>}
	 */
	@TestFactory
	public Collection<DynamicNode> testCyclicDeclaration() {
		var tests = new ArrayList<DynamicNode>();

		var allInits = this.getUsedInitialiserPackage().getAllInitialiserInstances();
		var clsInits = List.of(allInits.stream().filter((i) -> i instanceof IConcreteClassifierInitialiser)
				.toArray(IConcreteClassifierInitialiser[]::new));
		var impInits = List.of(
				allInits.stream().filter((i) -> i instanceof IImportInitialiser).toArray(IImportInitialiser[]::new));
		var tpInit = new TypeParameterInitialiser();
		var clsRefInit = new ClassifierReferenceInitialiser();

		for (var cInit : clsInits) {
			var cls1 = cInit.instantiate();
			cInit.setName(cls1, "cls1");
			var cls2 = cInit.instantiate();
			cInit.setName(cls2, "cls2");

			var cls1Ref = clsRefInit.instantiate();
			clsRefInit.setTarget(cls1Ref, cls1);
			var cls2Ref = clsRefInit.instantiate();
			clsRefInit.setTarget(cls2Ref, cls2);

			var t1 = tpInit.instantiate();
			tpInit.addExtendType(t1, cls1Ref);
			var t2 = tpInit.instantiate();
			tpInit.addExtendType(t2, cls2Ref);
			cInit.addTypeParameter(cls1, t2);
			cInit.addTypeParameter(cls2, t1);

			var clsTestList = new ArrayList<DynamicNode>();

			clsTestList
					.addAll(this.testDeclaration(this.cloneEObjWithContainers(cls1), this.cloneEObjWithContainers(cls2),
							this.cloneEObjWithContainers(cls1Ref), this.cloneEObjWithContainers(cls2Ref),
							this.cloneEObjWithContainers(t1), this.cloneEObjWithContainers(t2)));

			for (var iInit : impInits) {
				var impTestList = new ArrayList<DynamicNode>();

				impTestList.addAll(this.testImport_FromPackage(this.cloneEObjWithContainers(cls1),
						this.cloneEObjWithContainers(cls2), iInit));
				impTestList.addAll(this.testImport_FromCompilationUnit(this.cloneEObjWithContainers(cls1),
						this.cloneEObjWithContainers(cls2), iInit));

				var impNode = DynamicContainer.dynamicContainer(
						String.format("Import type: %s", iInit.getInstanceClassOfInitialiser().getSimpleName()),
						impTestList);
				clsTestList.add(impNode);
			}
			var clsNode = DynamicContainer.dynamicContainer(
					String.format("Classifier type: %s", cInit.getInstanceClassOfInitialiser().getSimpleName()),
					clsTestList);
			tests.add(clsNode);
		}

		return tests;
	}

	/**
	 * TODO Add commentary
	 */
	public Collection<DynamicNode> testDeclaration(ConcreteClassifier cls1, ConcreteClassifier cls2,
			ClassifierReference cls1Ref, ClassifierReference cls2Ref, TypeParameter t1, TypeParameter t2) {
		var tests = new ArrayList<DynamicNode>();

		var cyclicDefTest1 = DynamicTest.dynamicTest("Compare classifiers", () -> {
			this.testSimilarity(cls1, cls2, false);
		});
		var cyclicDefTest2 = DynamicTest.dynamicTest("Compare classifier reference", () -> {
			this.testSimilarity(cls1Ref, cls2Ref, false);
		});
		var cyclicDefTest3 = DynamicTest.dynamicTest("Compare Type parameter", () -> {
			this.testSimilarity(t1, t2, false);
		});
		var cyclicDefTestNode = DynamicContainer.dynamicContainer("Compare declaration",
				List.of(cyclicDefTest1, cyclicDefTest2, cyclicDefTest3));
		tests.add(cyclicDefTestNode);
		return tests;
	}

	/**
	 * TODO Add commentary
	 */
	public Collection<DynamicNode> testImport_FromPackage(ConcreteClassifier cls1, ConcreteClassifier cls2,
			IImportInitialiser iInit) {
		var tests = new ArrayList<DynamicNode>();

		var pInit = new PackageInitialiser();
		var p1 = pInit.instantiate();
		pInit.addNamespace(p1, "p1");
		pInit.addClassifier(p1, cls1);
		var p2 = pInit.instantiate();
		pInit.addNamespace(p2, "p2");
		pInit.addClassifier(p2, cls2);

		var cyclicDefWithImpTest1 = DynamicTest.dynamicTest("Compare packages (of classifiers)", () -> {
			this.testSimilarity(p1, p2, false);
		});

		var imp1 = iInit.instantiate();
		iInit.addNamespaces(imp1, p1.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp1, cls1);
		var imp2 = iInit.instantiate();
		iInit.addNamespaces(imp2, p2.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp2, cls2);

		var impClassifierDoesNotMatter = this.getExpectedSimilarityResult(iInit.getInstanceClassOfInitialiser(),
				ImportsPackage.Literals.IMPORT__CLASSIFIER);
		var impNsDoesNotMatter = this.getExpectedSimilarityResult(iInit.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		var expectedImpComparisonResult = impClassifierDoesNotMatter && impNsDoesNotMatter;

		var cyclicDefWithImpTest2 = DynamicTest.dynamicTest("Compare imports (package.classifier)", () -> {
			this.testSimilarity(imp1, imp2, expectedImpComparisonResult);
		});

		// Test again after putting the imports into the packages

		pInit.addImport(p1, imp2);
		pInit.addImport(p2, imp1);

		var cyclicDefWithImpTest3 = DynamicTest.dynamicTest("Compare packages (of classifiers) with imports", () -> {
			this.testSimilarity(p1, p2, false);
		});

		var cyclicDefWithImpTest4 = DynamicTest
				.dynamicTest("Compare imports (package.classifier) after adding them to packages", () -> {
					this.testSimilarity(imp1, imp2, expectedImpComparisonResult);
				});

		var cyclicDefTestNode = DynamicContainer.dynamicContainer("Compare imports from packages",
				List.of(cyclicDefWithImpTest1, cyclicDefWithImpTest2, cyclicDefWithImpTest3, cyclicDefWithImpTest4));
		tests.add(cyclicDefTestNode);
		return tests;
	}

	/**
	 * TODO Add commentary
	 */
	public Collection<DynamicNode> testImport_FromCompilationUnit(ConcreteClassifier cls1, ConcreteClassifier cls2,
			IImportInitialiser iInit) {
		var tests = new ArrayList<DynamicNode>();

		var cuInit = new CompilationUnitInitialiser();
		var cu1 = cuInit.instantiate();
		cuInit.addNamespace(cu1, "cu1");
		cuInit.addClassifier(cu1, cls1);
		var cu2 = cuInit.instantiate();
		cuInit.addNamespace(cu2, "cu2");
		cuInit.addClassifier(cu2, cls2);

		var cyclicDefWithImpTest1 = DynamicTest.dynamicTest("Compare compilation units (of classifiers)", () -> {
			this.testSimilarity(cu1, cu2, false);
		});

		var imp1 = iInit.instantiate();
		iInit.addNamespaces(imp1, cu1.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp1, cls1);
		var imp2 = iInit.instantiate();
		iInit.addNamespaces(imp2, cu2.getNamespaces().toArray(String[]::new));
		iInit.setClassifier(imp2, cls2);

		var impClassifierDoesNotMatter = this.getExpectedSimilarityResult(iInit.getInstanceClassOfInitialiser(),
				ImportsPackage.Literals.IMPORT__CLASSIFIER);
		var impNsDoesNotMatter = this.getExpectedSimilarityResult(iInit.getInstanceClassOfInitialiser(),
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		var expectedImpComparisonResult = impClassifierDoesNotMatter && impNsDoesNotMatter;

		var cyclicDefWithImpTest2 = DynamicTest.dynamicTest("Compare imports (cuNamespace.classifier)", () -> {
			this.testSimilarity(imp1, imp2, expectedImpComparisonResult);
		});

		// Test again after adding imports to the compilation units

		cuInit.addImport(cu1, imp2);
		cuInit.addImport(cu2, imp1);

		var cyclicDefWithImpTest3 = DynamicTest.dynamicTest("Compare compilation units (of classifiers) with imports",
				() -> {
					this.testSimilarity(cu1, cu2, false);
				});
		var cyclicDefWithImpTest4 = DynamicTest
				.dynamicTest("Compare imports (cuNamespace.classifier) after adding them to compilation units", () -> {
					this.testSimilarity(imp1, imp2, expectedImpComparisonResult);
				});

		var cyclicDefTestNode = DynamicContainer.dynamicContainer("Compare imports from compilation units",
				List.of(cyclicDefWithImpTest1, cyclicDefWithImpTest2, cyclicDefWithImpTest3, cyclicDefWithImpTest4));
		tests.add(cyclicDefTestNode);
		return tests;
	}
}
