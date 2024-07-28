package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;

public interface IAnnotationParameterListInitialiser extends IAnnotationParameterInitialiser {
    @Override
    public AnnotationParameterList instantiate();
	public default boolean addSetting(AnnotationParameterList apl, AnnotationAttributeSetting aas) {
		if (aas != null) {
			apl.getSettings().add(aas);
			return apl.getSettings().contains(aas);
		}
		return true;
	}
	public default boolean addSettings(AnnotationParameterList apl, AnnotationAttributeSetting[] aass) {
		return this.doMultipleModifications(apl, aass, this::addSetting);
	}
}
