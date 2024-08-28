package cipm.consistency.fitests.similarity.java.action.declaration;

import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.Origin;

import cipm.consistency.fitests.similarity.java.action.IDevAction;

public abstract class DeclareJavaRootAction implements IDevAction {
	private final Resource targetRes;
	private String[] nss;
	private Origin origin;
	private String name;

	public DeclareJavaRootAction(Resource targetRes) {
		this.targetRes = targetRes;
	}

	public DeclareJavaRootAction withOrigin(Origin origin) {
		this.origin = origin;
		return this;
	}

	public DeclareJavaRootAction withNamespaces(String[] nss) {
		this.nss = nss;
		return this;
	}

	public DeclareJavaRootAction withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return The {@link Resource} instance, to which a {@link JavaRoot} instance
	 *         will be added.
	 */
	public Resource getTargetResource() {
		return this.targetRes;
	}

	public String[] getNamespaces() {
		return this.nss != null ? this.nss.clone() : null;
	}

	public Origin getOrigin() {
		return this.origin;
	}

	public String getName() {
		return this.name;
	}
}