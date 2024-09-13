package cipm.consistency.fitests.similarity.java.eobject.initialiser.members;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.variables.IVariableInitialiser;

public interface IFieldInitialiser extends IAnnotableAndModifiableInitialiser, IInitializableInitialiser,
		IMemberInitialiser, IVariableInitialiser {
	@Override
	public Field instantiate();

	public default boolean addAdditionalField(Field field, AdditionalField additionalField) {
		if (additionalField != null) {
			field.getAdditionalFields().add(additionalField);
			return field.getAdditionalFields().contains(additionalField);
		}
		return true;
	}

	public default boolean addAdditionalFields(Field field, AdditionalField[] additionalFields) {
		return this.doMultipleModifications(field, additionalFields, this::addAdditionalField);
	}
}
