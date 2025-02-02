package coveragepac;

import org.emftext.language.java.commons.NamespaceAwareElement;







/**
 * An interface that contains default methods, which create and send
 * {@link ISimilarityRequest} instances to {@link ISimilarityRequestHandler}
 * instances that are supposed to handle them. <br>
 * <br>
 * These methods can be used to spare code duplication in inner switches, which
 * need them.
 * 
 * @author Alp Torac Genc
 */
public interface IJavaSimilarityInnerSwitch extends IInnerSwitch {
	/**
	 * Sends out a {@link ClassifierNormalizationRequest} and returns the result.
	 * 
	 * @see {@link #handleSimilarityRequest(org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest)}
	 */
	public default String normalizeClassifier(String origin) {
		return (String) this.handleSimilarityRequest(new ClassifierNormalizationRequest(origin));
	}

	/**
	 * Sends out a {@link CompilationUnitNormalizationRequest} and returns the
	 * result.
	 * 
	 * @see {@link #handleSimilarityRequest(org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest)}
	 */
	public default String normalizeCompilationUnit(String origin) {
		return (String) this.handleSimilarityRequest(new CompilationUnitNormalizationRequest(origin));
	}

	/**
	 * Sends out a {@link PackageNormalizationRequest} and returns the result.
	 * 
	 * @see {@link #handleSimilarityRequest(org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest)}
	 */
	public default String normalizePackage(String origin) {
		return (String) this.handleSimilarityRequest(new PackageNormalizationRequest(origin));
	}

	/**
	 * Sends out a {@link NamespaceNormalizationRequest} and returns the result.
	 * 
	 * @see {@link #handleSimilarityRequest(org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest)}
	 */
	public default String normalizeNamespace(String origin) {
		return (String) this.handleSimilarityRequest(new NamespaceNormalizationRequest(origin));
	}

	/**
	 * @see {@link JaMoPPNamespaceUtil#compareNamespacesByPart(NamespaceAwareElement, NamespaceAwareElement)}
	 */
	public default Boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2) {
		return JaMoPPNamespaceUtil.compareNamespacesByPart(ele1, ele2);
	}
}
