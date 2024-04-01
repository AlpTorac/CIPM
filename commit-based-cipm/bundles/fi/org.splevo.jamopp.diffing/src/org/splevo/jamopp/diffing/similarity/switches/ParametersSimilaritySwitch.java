package org.splevo.jamopp.diffing.similarity.switches;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.util.ParametersSwitch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

import com.google.common.base.Strings;

/**
 * Similarity decisions for parameter elements.
 * <p>
 * Parameters are variables and for this named elements. So their names must be checked but no
 * more identifying attributes or references exist.
 * </p>
 */
public class ParametersSimilaritySwitch extends ParametersSwitch<Boolean> {
	private final SimilaritySwitch similaritySwitch;

	/**
	 * @param similaritySwitch
	 */
	public ParametersSimilaritySwitch(SimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	@Override
    public Boolean caseParameter(Parameter param1) {
        Parameter param2 = (Parameter) this.similaritySwitch.getCompareElement();
        String name1 = Strings.nullToEmpty(param1.getName());
        String name2 = Strings.nullToEmpty(param2.getName());
        return (name1.equals(name2));
    }
}