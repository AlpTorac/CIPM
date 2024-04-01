package org.splevo.jamopp.diffing.similarity.switches;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.UnknownTypeArgument;
import org.emftext.language.java.generics.util.GenericsSwitch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

/**
 * Similarity decisions for the generic elements.
 */
public class GenericsSimilaritySwitch extends GenericsSwitch<Boolean> {
	private final SimilaritySwitch similaritySwitch;

	/**
	 * @param similaritySwitch
	 */
	public GenericsSimilaritySwitch(SimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	@Override
	public Boolean caseQualifiedTypeArgument(QualifiedTypeArgument qta1) {
		QualifiedTypeArgument qta2 = (QualifiedTypeArgument) this.similaritySwitch.getCompareElement();
		return this.similaritySwitch.isSimilar(qta1.getTypeReference(), qta2.getTypeReference());
	}
	
	@Override
	public Boolean caseSuperTypeArgument(SuperTypeArgument sta1) {
		SuperTypeArgument sta2 = (SuperTypeArgument) this.similaritySwitch.getCompareElement();
		return this.similaritySwitch.isSimilar(sta1.getSuperType(), sta2.getSuperType());
	}
	
	@Override
	public Boolean caseExtendsTypeArgument(ExtendsTypeArgument eta1) {
		ExtendsTypeArgument eta2 = (ExtendsTypeArgument) this.similaritySwitch.getCompareElement();
		return this.similaritySwitch.isSimilar(eta1.getExtendType(), eta2.getExtendType());
	}
	
	@Override
	public Boolean caseUnknownTypeArgument(UnknownTypeArgument arg) {
		return Boolean.TRUE;
	}
	
    @Override
    public Boolean caseTypeParameter(TypeParameter param1) {
    	TypeParameter param2 = (TypeParameter) this.similaritySwitch.getCompareElement();
    	
    	if (!param1.getName().equals(param2.getName())) {
    		return Boolean.FALSE;
    	}
    	
    	return this.similaritySwitch.areSimilar(param1.getExtendTypes(), param2.getExtendTypes());
    }
}