package cipm.consistency.fluentapi.newapi.metamodel;

import cipm.consistency.fluentapi.newapi.APIStep;
import cipm.consistency.fluentapi.newapi.FeatureChoice;
import cipm.consistency.fluentapi.newapi.SingleValueFeatureOperationChoice;

public class GeneratedClassFeatureChoice<Prev extends APIStep<Prev>> extends FeatureChoice<Prev> {
	public SingleValueFeatureOperationChoice<Prev, GeneratedClassChoice<Prev>, String> nameFeature() {
		return new SingleValueFeatureOperationChoice<Prev, GeneratedClassChoice<Prev>, String>(null);
	}
}
