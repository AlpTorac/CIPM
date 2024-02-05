package tools.vitruv.applications.pcmjava.linkingintegration.change2command

class Java2PcmIntegrationChangePropagationSpecification extends Java2PcmChangePropagationSpecification {
	
	override protected setup() {
		super.setup();
		addChangePreprocessor(new CodeIntegrationChangeProcessor());
	}
	
}