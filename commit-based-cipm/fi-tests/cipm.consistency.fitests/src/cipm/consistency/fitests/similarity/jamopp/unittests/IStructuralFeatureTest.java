package cipm.consistency.fitests.similarity.jamopp.unittests;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface IStructuralFeatureTest {
	public default void setValueOf(EObject obj, EStructuralFeature feat, Object val) {
		if (val == null)
			return;

		if (!feat.isMany()) {
			obj.eSet(feat, val);
		} else {
			var list = new BasicEList<>();
			if (!val.getClass().isArray()) {
				list.add(val);
			} else {
				for (var valObj : (Object[]) val) {
					if (valObj != null) {
						list.add(valObj);
					}
				}
			}
			obj.eSet(feat, list);
		}
	}
}
