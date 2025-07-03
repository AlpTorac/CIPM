package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.FeatureFilter;

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
		var comparer = builder.build();

		ParserTestTimeMeasurer.getInstance().startTimeMeasurement(this.getClass().getSimpleName(),
				GeneralTimeMeasurementTag.MODEL_RESOURCE_COMPARISON);
		var result = comparer.compare(scope);
		ParserTestTimeMeasurer.getInstance().stopTimeMeasurement();

		return result;
	}

	/**
	 * Asserts that the result of similarity checking via model comparison results
	 * in differences or not (denoted by expectedResult). <br>
	 * <br>
	 * Compares res1 and res2, as well as res2 and res1; in order to ensure that the
	 * comparison is symmetric.
	 */
	protected void testSimilarityWithModelComparison(Resource res1, Resource res2, Boolean expectedResult) {
		ParserTestTimeMeasurer.getInstance().startTimeMeasurement(this.getClass().getSimpleName(), GeneralTimeMeasurementTag.TEST_OVERHEAD);

		var cmp1To2 = this.compareModels(res1, res2);
		var cmp2To1 = this.compareModels(res2, res1);
		Assertions.assertEquals(expectedResult, cmp1To2.getDifferences().size() == 0);
		Assertions.assertEquals(expectedResult, cmp2To1.getDifferences().size() == 0);

		ParserTestTimeMeasurer.getInstance().stopTimeMeasurement();
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with res2.
	 */
	@Override
	public DynamicNode createTestsFor(Resource res1, Path path1, Resource res2, Path path2) {
		return DynamicTest.dynamicTest(String.format("%s vs %s", path1.getFileName(), path2.getFileName()), () -> {
			this.testSimilarityWithModelComparison(res1, res2,
					this.getExpectedSimilarityResultFor(res1, path1, res2, path2));
		});
	}

	@Override
	public String getTestDescription() {
		return description;
	}

	@Override
	public IExpectedSimilarityResultProvider getDefaultExpectedSimilarityResultProvider() {
		return new ResourceContentSimilarityResultProvider(this.scc, this.contentOrderMatters);
	}
}
