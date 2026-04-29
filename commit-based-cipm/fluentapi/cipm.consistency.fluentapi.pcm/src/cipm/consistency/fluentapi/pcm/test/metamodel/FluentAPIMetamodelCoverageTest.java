package cipm.consistency.fluentapi.pcm.test.metamodel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.test.metamodel.AbstractFluentAPIMetamodelCoverageTest;

public class FluentAPIMetamodelCoverageTest extends AbstractFluentAPIMetamodelCoverageTest
		implements IFluentPcmAPIMetamodelTest {
	/**
	 * Currently PCM is having issues casting Object[] into SomePCMModelElement[],
	 * hence this test is disabled
	 */
	@Disabled("Disabled until PCM-specific issues are dealt with")
	@Test
	public void concreteElementCoverageTest_SuperInit_xManyValuedFeature() {
	}

	/**
	 * Currently PCM is having issues casting Object[] into SomePCMModelElement[],
	 * hence this test is disabled
	 */
	@Disabled("Disabled until PCM-specific issues are dealt with")
	@Override
	public void concreteElementCoverageTest_API_xManyValuedFeature() {
	}
}
