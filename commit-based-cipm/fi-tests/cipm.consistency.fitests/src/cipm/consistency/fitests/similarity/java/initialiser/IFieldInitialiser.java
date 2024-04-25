package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;

public interface IFieldInitialiser extends IAnnotableAndModifiableInitialiser,
	IInitializableInitialiser, IMemberInitialiser, IVariableInitialiser {

	@Override
	public Field instantiate();
	
	@Override
	public default Field minimalInstantiation() {
		return (Field) IVariableInitialiser.super.minimalInstantiation();
	}
	
	public default void addAdditionalField(Field field, AdditionalField adField) {
		if (adField != null) {
			field.getAdditionalFields().add(adField);
			assert field.getAdditionalFields().contains(adField);
		}
	}
}
