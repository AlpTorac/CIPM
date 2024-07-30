package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;

import cipm.consistency.fitests.similarity.java.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.IVariableInitialiser;

public interface IFieldInitialiser extends IAnnotableAndModifiableInitialiser, IInitializableInitialiser,
		IMemberInitialiser, IVariableInitialiser {
	@Override
	public Field instantiate();

	public default boolean addAdditionalField(Field field, AdditionalField adField) {
		if (adField != null) {
			field.getAdditionalFields().add(adField);
			return field.getAdditionalFields().contains(adField);
		}
		return true;
	}

	public default boolean addAdditionalFields(Field field, AdditionalField[] adFields) {
		return this.doMultipleModifications(field, adFields, this::addAdditionalField);
	}
}
