package tools.vitruv.applications.pcmjava.linkingintegration.change2command

class Pcm2JavaIntegrationChangePropagationSpecification extends Pcm2JavaChangePropagationSpecification {
	
	override protected setup() {
		super.setup();
		addChangePreprocessor(new CodeIntegrationChangeProcessor());
	}
	
}