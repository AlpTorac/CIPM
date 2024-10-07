package cipm.consistency.initialisers.tests.dummy.types;

import java.util.Collection;
import java.util.HashSet;

public class DummyModifiableObj {
	private final Collection<Object> someModifiableAttrCol = new HashSet<Object>();

	public boolean addAttr(Object attrToAdd) {
		return attrToAdd != null && this.someModifiableAttrCol.add(attrToAdd);
	}
	
	public Collection<Object> getAttrs() {
		var attrs = new HashSet<Object>();
		this.someModifiableAttrCol.forEach((attr) -> attrs.add(attr));
		return attrs;
	}
}