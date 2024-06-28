package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;

public interface IAnnotationParameterListInitialiser extends IAnnotationParameterInitialiser {
	public default void addSetting(AnnotationParameterList apl, AnnotationAttributeSetting aas) {
		if (aas != null) {
			apl.getSettings().add(aas);
			assert apl.getSettings().contains(aas);
		}
	}
	
	public default void addSettings(AnnotationParameterList apl, AnnotationAttributeSetting[] aass) {
		this.addXs(apl, aass, this::addSetting);
	}
}
