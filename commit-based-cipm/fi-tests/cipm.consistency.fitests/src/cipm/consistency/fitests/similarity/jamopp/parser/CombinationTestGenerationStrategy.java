package cipm.consistency.fitests.similarity.jamopp.parser;

import java.util.Iterator;

/**
 * TODO Delete before creating pull request
 * 
 * @author Alp Torac Genc
 */
public class CombinationTestGenerationStrategy implements IJaMoPPParserTestGenerationStrategy {
	private final static String description = "combination strategy";

	/**
	 * @implSpec Returns an iterator that helps generate dynamic tests over a
	 *           combination of test resources. If {@code testResourceCount = 3},
	 *           then the order denoted by the returned iterator will be as follows,
	 *           where {@code (i, j)} stands for the indices of test resources that
	 *           will be used in the current test: <br>
	 *           <br>
	 *           {@code (0,0); (0,1); (0,2); (1,0); (1,1); (1,2); (2,0); (2,1); (2,2)}
	 * @implNote Assuming both arrays' length is {@code N}, then the returned
	 *           iterator iterates {@code N^2} times. As such, a maximum of
	 *           {@code N^2} dynamic tests can be generated.
	 */
	@Override
	public Iterator<int[]> getTestResourceIterator(int testResourceCount) {
		return new Iterator<int[]>() {
			private int i = 0;
			private int j = 0;

			@Override
			public boolean hasNext() {
				return (i < testResourceCount - 1) || (i == testResourceCount - 1 && j < testResourceCount);
			}

			@Override
			public int[] next() {
				int[] result = null;

				if (j < testResourceCount) {
					result = new int[] { i, j };
				} else {
					j = 0;
					++i;
					result = new int[] { i, j };
				}

				++j;
				return result;
			}

		};
	}

	@Override
	public String getTestGenerationStrategyDescription() {
		return description;
	}
}
