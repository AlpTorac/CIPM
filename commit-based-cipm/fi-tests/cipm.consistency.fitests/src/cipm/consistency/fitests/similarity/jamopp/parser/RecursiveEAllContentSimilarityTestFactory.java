package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;

public class RecursiveEAllContentSimilarityTestFactory extends AbstractJaMoPPParserSimilarityTestFactory {
	private static final String description = "areSimilar on eAllContents [recursively]";
	private static final FileUtil fileUtil = new FileUtil();

	private ISimilarityCheckerContainer scc;

	public RecursiveEAllContentSimilarityTestFactory(ISimilarityCheckerContainer scc) {
		this.scc = scc;
	}

	/**
	 * Recursively explores obj for nested contents.
	 * 
	 * @param obj A given EObject instance
	 * @return Collection that contains obj and all further EObject instances, which
	 *         are nested in obj as content.
	 */
	protected Collection<EObject> getAllEObjectsRecursively(EObject obj) {
		var allContents = new ArrayList<EObject>();
		allContents.add(obj);
		obj.eAllContents().forEachRemaining((c) -> {
			allContents.addAll(this.getAllEObjectsRecursively(c));
		});
		return allContents;
	}

	/**
	 * Checks whether the given {@link Resource} instances are similar, based on
	 * {@code res_i.getAllContents()}. The order of the contents, as well as nested
	 * contents, is also considered and will impact the result. <br>
	 * <br>
	 * It is important to use this method over other similarity testing methods, due
	 * to the Java models in these tests being potentially fragmented. Hence the use
	 * of {@code res_i.getAllContents()}. <br>
	 * <br>
	 * <b><i>!!! It is important to note that the result of the similarity checking
	 * in this method will differ from others, because it compares all contents
	 * within the resources and not just root contents. !!!</i></b>
	 */
	protected void testSimilarityOfAllContents(Resource res1, Resource res2, Boolean expectedResult) {
		var list1 = new ArrayList<EObject>();
		var list2 = new ArrayList<EObject>();

		/*
		 * Only adding all contents as is can yield unexpected results, because doing so
		 * does not necessarily account for the nested contents' order. This is a
		 * problem, because it may lead to comparisons that are not performed by
		 * similarity checking.
		 */
		res1.getAllContents().forEachRemaining((o) -> list1.addAll(this.getAllEObjectsRecursively(o)));
		res2.getAllContents().forEachRemaining((o) -> list2.addAll(this.getAllEObjectsRecursively(o)));

		Assertions.assertEquals(expectedResult, this.scc.areSimilar(list1, list2));
	}

	/**
	 * Ensures that all contents of the parsed models are only then similar
	 * (accounting for their order too), if the content of their source files are
	 * equal (in terms of code, not whitespace nor comments etc.).
	 */
	@Override
	public DynamicNode createTestsFor(Resource res1, Path path1, Resource res2, Path path2) {
		return DynamicTest.dynamicTest(String.format("%s vs %s", path1.getFileName(), path2.getFileName()), () -> {
			this.testSimilarityOfAllContents(res1, res2, fileUtil.areContentsEqual(path1, path2));
		});
	}

	@Override
	public String getTestDescription() {
		return description;
	}
}
