package org.splevo.vpm.analyzer.semantic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.splevo.vpm.analyzer.semantic.lucene.LuceneCodeAnalyzer;

/**
 * This class contains all constant values for the semantic analyzer to ease the access. 
 * 
 * @author Daniel Kojic
 *
 */
public final class Constants {
	/** The field name the Variation Point ID is stored in the Lucene index. */
	public static final String INDEX_VARIATIONPOINT = "VP";

	/** The field name the content ID is stored in the Lucene index. */
	public static final String INDEX_CONTENT = "CONTENT";
	
	/** The relationship label of the analyzer. */
    public static final String RELATIONSHIP_LABEL_SEMANTIC = "Semantic";

    /** The displayed name of the analyzer. */
    public static final String DISPLAYED_NAME = "Semantic VPM Analyzer";

	/** The configuration label for the cosine minimum similarity configuration. */
	public static final String CONFIG_MINIMUM_SIMILARITY_LABEL = "MINSIM";

	/** The configuration label for the include comments configuration. */
	public static final String CONFIG_INCLUDE_COMMENTS_LABEL = "INCLUDE_COMMENTS";

	/** The default value for the minimum cosine similarity. */
	public static final double DEFAULT_MIN_COSINE_SIMILARITY = 0.85d;
	
	/** This {@link Analyzer} is used to store code fragments. */
	public static final Analyzer CODE_ANALYZER = new LuceneCodeAnalyzer();
	
	/** This {@link Analyzer} is used to store comments and annotations. */
	public static final Analyzer COMMENT_ANALYZER = new StandardAnalyzer(Version.LUCENE_43);
	
	/** Search for terms with a maximum frequency in the document.  */
	public static final float RAREFINDER_DEFAULT_PERCENTAGE = 0.3f;
	
	/** The stop-word list for the analyzers. */
	public static final String[] DEFAULT_STOP_WORDS = new String[]{
		"get", 
		"set", 
		"default", 
		"configure", 
		"clear",
		"integer",
		"float",
		"double",
		"string",
		"value"
		};

	/** The configuration label for the USE_RARE_FINDER configuration. */
	public static final String CONFIG_USE_RARE_FINDER_LABEL = "Use Rare-Finder";

	/** The configuration label for the USE_OVERALL_SIMILARITY_FINDER configuration. */
	public static final String CONFIG_USE_OVERALL_SIMILARITY_FINDER_LABEL = "Use Overall-Similarity-Finder";
}
