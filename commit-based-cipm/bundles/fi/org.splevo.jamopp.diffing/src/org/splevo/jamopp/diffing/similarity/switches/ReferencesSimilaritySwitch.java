package org.splevo.jamopp.diffing.similarity.switches;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.references.util.ReferencesSwitch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;
import org.splevo.jamopp.util.JaMoPPElementUtil;

/**
 * Similarity decisions for reference elements.
 */
public class ReferencesSimilaritySwitch extends ReferencesSwitch<Boolean> {
	private final SimilaritySwitch similaritySwitch;

	/**
	 * @param similaritySwitch
	 */
	public ReferencesSimilaritySwitch(SimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	@Override
    public Boolean caseStringReference(StringReference ref1) {

        StringReference ref2 = (StringReference) this.similaritySwitch.getCompareElement();
        if (ref1.getValue() == null) {
            return (ref2.getValue() == null);
        }

        return (ref1.getValue().equals(ref2.getValue()));
    }

    @Override
    public Boolean caseIdentifierReference(IdentifierReference ref1) {

        IdentifierReference ref2 = (IdentifierReference) this.similaritySwitch.getCompareElement();
        ReferenceableElement target1 = ref1.getTarget();
        ReferenceableElement target2 = ref2.getTarget();

        // target identity similarity
        Boolean similarity = this.similaritySwitch.isSimilar(target1, target2);
        if (similarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        if (target1 != null) {
            // target container similarity
            // check this only if the reference target is located
            // in another container than the reference itself.
            // Otherwise such a situation would lead to endless loops
            // e.g. for for "(Iterator i = c.iterator(); i.hasNext(); ) {"
            // Attention: The reference could be encapsulated by an expression!
            EObject ref1Container = JaMoPPElementUtil.getFirstContainerNotOfGivenType(ref1, Expression.class,
                    ArraySelector.class);
            EObject ref2Container = JaMoPPElementUtil.getFirstContainerNotOfGivenType(ref2, Expression.class,
                    ArraySelector.class);
            EObject target1Container = target1.eContainer();
            EObject target2Container = target2.eContainer();
            if (target1Container != ref1Container && target2Container != ref2Container
            		&& target1Container != ref1 && target2Container != ref2) {
                Boolean containerSimilarity = this.similaritySwitch.isSimilar(target1Container, target2Container);
                if (containerSimilarity == Boolean.FALSE) {
                    return Boolean.FALSE;
                }
            }
        }

        if (ref1.getArraySelectors().size() != ref2.getArraySelectors().size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < ref1.getArraySelectors().size(); i++) {
            ArraySelector selector1 = ref1.getArraySelectors().get(i);
            ArraySelector selector2 = ref2.getArraySelectors().get(i);
            Boolean positionSimilarity = this.similaritySwitch.isSimilar(selector1.getPosition(),
                    selector2.getPosition());
            if (positionSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        Reference next1 = ref1.getNext();
        Reference next2 = ref2.getNext();
        Boolean nextSimilarity = this.similaritySwitch.isSimilar(next1, next2);
        if (nextSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Check element reference similarity.<br>
     * 
     * Is checked by the target (the method called). Everything else are containment references
     * checked indirectly.
     * 
     * @param ref1
     *            The method call to compare with the compare element.
     * @return True As null always means null.
     */
    @Override
    public Boolean caseElementReference(ElementReference ref1) {
        ElementReference ref2 = (ElementReference) this.similaritySwitch.getCompareElement();

        Boolean targetSimilarity = this.similaritySwitch.isSimilar(ref1.getTarget(), ref2.getTarget());
        if (targetSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Proof method call similarity.
     * 
     * Similarity is decided by the method referenced and the arguments passed by.
     * 
     * @param call1
     *            The left / modified method call to compare with the original one.
     * @return True/False if the method calls are similar or not.
     */
    @Override
    public Boolean caseMethodCall(MethodCall call1) {
        MethodCall call2 = (MethodCall) this.similaritySwitch.getCompareElement();

        Boolean targetSimilarity = this.similaritySwitch.isSimilar(call1.getTarget(), call2.getTarget());
        if (targetSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        if (call1.getArguments().size() != call2.getArguments().size()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < call1.getArguments().size(); i++) {
            Expression exp1 = call1.getArguments().get(i);
            Expression exp2 = call2.getArguments().get(i);
            Boolean argSimilarity = this.similaritySwitch.isSimilar(exp1, exp2);
            if (argSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        Boolean nextSimilarity = this.similaritySwitch.isSimilar(call1.getNext(), call2.getNext());
        if (nextSimilarity == Boolean.FALSE) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean defaultCase(EObject object) {
        return Boolean.TRUE;
    }
}