package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;

public interface UsesPackages {
	public default Package createMinimalPackage(String[] nss) {
		var pacInit = new PackageInitialiser();
		
		Package result = pacInit.instantiate();
		pacInit.initialise(result);
		pacInit.addNamespaces(result, nss);
		
		return result;
	}
	
	public default Package createMinimalPackage(String nsPrefix, int nsCount) {
		var nss = new String[nsCount];
		
		for (int i = 0; i < nsCount; i++)
			nss[i] = nsPrefix+i;
		
		return this.createMinimalPackage(nss);
	}
}
