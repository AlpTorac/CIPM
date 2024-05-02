package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationParameterInitialiser;

public interface IAnnotationParameterListInitialiser extends IAnnotationParameterInitialiser {
	public default void addSetting(AnnotationParameterList apl, AnnotationAttributeSetting aas) {
		if (aas != null) {
			apl.getSettings().add(aas);
			assert apl.getSettings().contains(aas);
		}
	}
}
