package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.members.Field;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IVariableInitialiser;

public interface IFieldInitialiser extends IAnnotableAndModifiableInitialiser,
	IInitializableInitialiser, IMemberInitialiser, IVariableInitialiser {
	public default void addAdditionalField(Field field, AdditionalField adField) {
		if (adField != null) {
			field.getAdditionalFields().add(adField);
			assert field.getAdditionalFields().contains(adField);
		}
	}
}
