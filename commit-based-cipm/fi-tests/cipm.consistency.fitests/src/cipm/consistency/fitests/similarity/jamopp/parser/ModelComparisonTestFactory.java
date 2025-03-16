package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.JavaPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.splevo.jamopp.diffing.diff.JaMoPPFeatureFilter;
import org.splevo.jamopp.diffing.scope.PackageIgnoreChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

import cipm.consistency.commitintegration.diff.util.HierarchicalMatchEngineFactoryGenerator;
import cipm.consistency.commitintegration.diff.util.ResourceListFilteringComparisonScope;
import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;

public class ModelComparisonTestFactory extends AbstractJaMoPPParserSimilarityTestFactory {
	private static final String description = "Java model comparison on both sides";
	private ISimilarityCheckerContainer scc;
	private String resourceFileExtension;
	private boolean contentOrderMatters;

	public ModelComparisonTestFactory(ISimilarityCheckerContainer scc, String resourceFileExtension) {
		this(scc, resourceFileExtension, true);
	}

	public ModelComparisonTestFactory(ISimilarityCheckerContainer scc, String resourceFileExtension,
			boolean contentOrderMatters) {
		this.scc = scc;
		this.resourceFileExtension = resourceFileExtension;
		this.contentOrderMatters = contentOrderMatters;
	}

	/**
	 * Checks if both sides' contents ({@code res.getAllContents()}) are similar, if
	 * their order does not matter. Makes sure that the result is the same as
	 * {@code allContentSimilar(rhs, lhs)}.
	 * 
	 * @return Whether all contents of lhs and rhs are similar, i.e. if all contents
	 *         of lhs have a corresponding similar content on rhs.
	 */
	public boolean contentwiseSimilar(Resource lhs, Resource rhs) {
		var lhsContent = new ArrayList<EObject>();
		lhs.getAllContents().forEachRemaining((e) -> lhsContent.add(e));
		var rhsContent = new ArrayList<EObject>();
		rhs.getAllContents().forEachRemaining((e) -> rhsContent.add(e));

		return this.contentwiseSimilar(lhsContent, rhsContent) && this.contentwiseSimilar(rhsContent, lhsContent);
	}

	/**
	 * Checks if both sides' contents ({@code obj.eAllContents()}) are similar, if
	 * their order does not matter. Makes sure that the result is the same as
	 * {@code allContentSimilar(rhs, lhs)}.
	 * 
	 * @return Whether all contents of lhs and rhs are similar, i.e. if all contents
	 *         of lhs have a corresponding similar content on rhs.
	 */
	public boolean contentwiseSimilar(EObject lhs, EObject rhs) {
		if (!this.scc.isSimilar(lhs, rhs) || !this.scc.isSimilar(rhs, lhs)) {
			return false;
		}

		var lhsContent = new ArrayList<EObject>();
		lhs.eAllContents().forEachRemaining((e) -> lhsContent.add(e));
		var rhsContent = new ArrayList<EObject>();
		rhs.eAllContents().forEachRemaining((e) -> rhsContent.add(e));

		return this.contentwiseSimilar(lhsContent, rhsContent) && this.contentwiseSimilar(rhsContent, lhsContent);
	}

	/**
	 * Variant of {@link #contentwiseSimilar(EObject, EObject)} for collections.
	 */
	public boolean contentwiseSimilar(Collection<EObject> lhs, Collection<EObject> rhs) {
		var lhsContent = new ArrayList<EObject>(lhs);
		var rhsContent = new ArrayList<EObject>(rhs);

		if (lhsContent.size() != rhsContent.size()) {
			return false;
		}

		while (!lhsContent.isEmpty() && !rhsContent.isEmpty()) {
			var lhsElem = lhsContent.get(0);
			final var rhsElem = new EObject[] { null };
			for (var e : rhsContent) {
				if (this.contentwiseSimilar(lhsElem, e)) {
					rhsElem[0] = e;
					break;
				}
			}
			if (rhsElem[0] != null) {
				lhsContent.remove(lhsElem);
				rhsContent.remove(rhsElem[0]);
			} else {
				return false;
			}
		}
		return lhsContent.isEmpty() && rhsContent.isEmpty();
	}

	/**
	 * Defaults to comparing the source file paths.
	 * 
	 * @param lhs               Left-hand side resource
	 * @param lhsSourceFilePath The path that the resource lhs was parsed from
	 * @param rhs               Right-hand side resource
	 * @param rhsSourceFilePath The path that the resource rhs was parsed from
	 * @return The expected result of similarity checking the given resources by
	 *         using model comparison
	 * 
	 * @see {@link #testSimilarityWithModelComparison(Resource, Resource, Boolean)}
	 */
	public Boolean getExpectedSimilarityResultForModelComparison(Resource lhs, Path lhsSourceFilePath, Resource rhs,
			Path rhsSourceFilePath) {
		var pathsEqual = lhsSourceFilePath.toString().equals(rhsSourceFilePath.toString());
		return pathsEqual || (!this.contentOrderMatters && this.contentwiseSimilar(lhs, rhs));
	}

	/**
	 * Compares the given {@link Resource} instances representing Java models. Uses
	 * the underlying similarity checking mechanisms for identifying changes. <br>
	 * <br>
	 * Note that the order of the given parameters matters and will influence the
	 * result, since reaching from one side to the other will require "opposite"
	 * operations.
	 * 
	 * @param res1 The new state
	 * @param res2 The old state
	 * @return Result of comparing {@code res2} to {@code res1}, i.e. what needs to
	 *         be done to {@code res2} to get to {@code res1}.
	 * 
	 * @see {@link #getSCC()}
	 */
	protected Comparison compareModels(Resource res1, Resource res2) {

		var scope = new ResourceListFilteringComparisonScope(res1, res2, null, null);
		scope.getNsURIs().add(JavaPackage.eNS_URI);

		var jamoppFeatureFilter = new JaMoPPFeatureFilter(new PackageIgnoreChecker(List.of()));
		var diffProcessor = new DiffBuilder();
		var diffEngine = new DefaultDiffEngine(diffProcessor) {
			@Override
			protected FeatureFilter createFeatureFilter() {
				return jamoppFeatureFilter;
			}
		};

		var engineRegistry = HierarchicalMatchEngineFactoryGenerator.generateMatchEngineRegistry(
				HierarchicalMatchEngineFactoryGenerator.generateMatchEngineFactory(new ISimilarityChecker() {

					@Override
					public Boolean isSimilar(Object element1, Object element2) {
						return scc.isSimilar(element1, element2);
					}

					@Override
					public Boolean areSimilar(Collection<Object> elements1, Collection<Object> elements2) {
						return scc.areSimilar(elements1, elements2);
					}

				}, this.resourceFileExtension));

		var builder = EMFCompare.builder().setMatchEngineFactoryRegistry(engineRegistry).setDiffEngine(diffEngine);

		return builder.build().compare(scope);
	}

	/**
	 * Asserts that the result of similarity checking via model comparison results
	 * in differences or not (denoted by expectedResult). <br>
	 * <br>
	 * Compares res1 and res2, as well as res2 and res1; in order to ensure that the
	 * comparison is symmetric.
	 */
	protected void testSimilarityWithModelComparison(Resource res1, Resource res2, Boolean expectedResult) {
		var cmp1To2 = this.compareModels(res1, res2);
		var cmp2To1 = this.compareModels(res2, res1);
		Assertions.assertEquals(expectedResult, cmp1To2.getDifferences().size() == 0);
		Assertions.assertEquals(expectedResult, cmp2To1.getDifferences().size() == 0);
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with res2.
	 */
	@Override
	public DynamicNode createTestsFor(Resource res1, Path path1, Resource res2, Path path2) {
		return DynamicTest.dynamicTest(String.format("%s vs %s", path1.getFileName(), path2.getFileName()), () -> {
			this.testSimilarityWithModelComparison(res1, res2,
					this.getExpectedSimilarityResultForModelComparison(res1, path1, res2, path2));
		});
	}

	@Override
	public String getTestDescription() {
		return description;
	}
}
