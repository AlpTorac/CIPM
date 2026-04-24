package cipm.consistency.fluentapi.java.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.java.api.ApiFactory;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIInitWithTest extends AbstractFluentAPITest {
	@Test
	public void withTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var name = "cuName";
		var cu = api.newCompilationUnit().withName(name).createNow();
		Assertions.assertEquals(name, cu.getName());
	}

	@Test
	public void withoutTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var name = "cuName";
		var cu = api.newCompilationUnit().withName(name).withoutName().createNow();
		Assertions.assertNull(cu.getName());
	}

	@Test
	public void withRemovedTest_SingleValue() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = ns1;

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(2, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
		Assertions.assertEquals(ns3, cu.getNamespaces().get(1));
	}

	@Test
	public void withRemovedTest_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = new String[] { ns1, ns3 };

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
	}

	@Test
	public void withRemovedTest_AsEList() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = FluentAPITestUtils.toEList(ns1, ns3);

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
	}

	@Test
	public void withRemovedTest_AsCollection() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };
		var toRemove = List.of(ns1, ns3);

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).withRemovedNamespaces(toRemove).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns2, cu.getNamespaces().get(0));
	}

	@Test
	public void cleanTest() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var ns1 = "ns1";
		var ns2 = "ns2";
		var ns3 = "ns3";

		var nss = new String[] { ns1, ns2, ns3 };

		var cu = api.newCompilationUnit().withAddedNamespaces(nss).cleanNamespaces().createNow();
		Assertions.assertEquals(0, cu.getNamespaces().size());
	}

	@Test
	public void withAddedTest_SingleVal() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var ns = "ns";
		var cu = api.newCompilationUnit().withAddedNamespaces(ns).createNow();
		Assertions.assertEquals(1, cu.getNamespaces().size());
		Assertions.assertEquals(ns, cu.getNamespaces().get(0));
	}

	@Test
	public void withAddedTest_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var nss = new String[] { "ns1", "ns2" };
		var cu = api.newCompilationUnit().withAddedNamespaces(nss).createNow();
		Assertions.assertArrayEquals(nss, cu.getNamespaces().toArray(String[]::new));
	}

	@Test
	public void withAddedTest_AsEList() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var nss = FluentAPITestUtils.toEList("ns1", "ns2");
		var cu = api.newCompilationUnit().withAddedNamespaces(nss).createNow();
		Assertions.assertArrayEquals(nss.toArray(String[]::new), cu.getNamespaces().toArray(String[]::new));
	}

	@Test
	public void withAddedTest_AsCollection() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var nss = List.of("ns1", "ns2");
		var cu = api.newCompilationUnit().withAddedNamespaces(nss).createNow();
		Assertions.assertArrayEquals(nss.toArray(String[]::new), cu.getNamespaces().toArray(String[]::new));
	}
}
