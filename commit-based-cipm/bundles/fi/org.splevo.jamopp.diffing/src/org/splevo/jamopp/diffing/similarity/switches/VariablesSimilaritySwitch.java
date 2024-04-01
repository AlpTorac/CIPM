package org.splevo.jamopp.diffing.similarity.switches;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.Variable;
import org.emftext.language.java.variables.util.VariablesSwitch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

import com.google.common.base.Strings;

/**
 * Similarity decisions for the variable elements.
 */
public class VariablesSimilaritySwitch extends VariablesSwitch<Boolean> {
	private final SimilaritySwitch similaritySwitch;

	/**
	 * @param similaritySwitch
	 */
	public VariablesSimilaritySwitch(SimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	/**
     * Check variable declaration similarity.<br>
     * Similarity is checked by
     * <ul>
     * <li>variable name</li>
     * <li>variable container (name space)</li>
     * </ul>
     * 
     * @param var1
     *            The variable declaration to compare with the compare element.
     * @return True/False if the variable declarations are similar or not.
     */
    @Override
    public Boolean caseVariable(Variable var1) {

        Variable var2 = (Variable) this.similaritySwitch.getCompareElement();

        // check the variables name equality
        if (!var1.getName().equals(var2.getName())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean caseAdditionalLocalVariable(AdditionalLocalVariable var1) {
        AdditionalLocalVariable var2 = (AdditionalLocalVariable) this.similaritySwitch.getCompareElement();

        // check the variables name equality
        String name1 = Strings.nullToEmpty(var1.getName());
        String name2 = Strings.nullToEmpty(var2.getName());
        if (!name1.equals(name2)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}