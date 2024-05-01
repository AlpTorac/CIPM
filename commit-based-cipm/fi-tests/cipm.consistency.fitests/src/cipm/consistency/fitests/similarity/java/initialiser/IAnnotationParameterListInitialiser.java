package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;

public interface IAnnotationParameterListInitialiser extends IAnnotationParameterInitialiser {
	public default void addSetting(AnnotationParameterList apl, AnnotationAttributeSetting aas) {
		if (aas != null) {
			apl.getSettings().add(aas);
			assert apl.getSettings().contains(aas);
		}
	}
}
