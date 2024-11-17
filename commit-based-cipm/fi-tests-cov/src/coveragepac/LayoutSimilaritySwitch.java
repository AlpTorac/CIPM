package coveragepac;

import org.eclipse.emf.ecore.EObject;
import org.emftext.commons.layout.util.LayoutSwitch;


/**
 * Similarity Decisions for layout information is always true as they are not considered for
 * now.
 */
public class LayoutSimilaritySwitch extends LayoutSwitch<Boolean> implements ILoggableJavaSwitch {
    @Override
    public Boolean defaultCase(EObject object) {
    	this.logMessage("defaultCase for Layout");
    	
        return Boolean.TRUE;
    }
}