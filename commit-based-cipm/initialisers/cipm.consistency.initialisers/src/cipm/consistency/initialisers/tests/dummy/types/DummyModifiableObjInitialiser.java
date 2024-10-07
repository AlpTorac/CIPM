package cipm.consistency.initialisers.tests.dummy.types;

import cipm.consistency.initialisers.IInitialiser;

public class DummyModifiableObjInitialiser implements IInitialiser {

	@Override
	public DummyModifiableObjInitialiser newInitialiser() {
		return new DummyModifiableObjInitialiser();
	}

	@Override
	public DummyModifiableObj instantiate() {
		return new DummyModifiableObj();
	}

	@Override
	public boolean initialise(Object obj) {
		return true;
	}

	public boolean addAttr(DummyModifiableObj obj, Object attrToAdd) {
		return obj.addAttr(attrToAdd);
	}

	public boolean addAttrs(DummyModifiableObj obj, Object[] attrsToAdd) {
		return this.doMultipleModifications(obj, attrsToAdd, this::addAttr);
	}
}