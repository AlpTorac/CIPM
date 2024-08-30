package cipm.consistency.commitintegration.diff.util.pcm.switches;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.util.RepositorySwitch;

import cipm.consistency.commitintegration.diff.util.pcm.PCMRepositorySimilaritySwitch;

public class SimilarityRepositorySwitch extends RepositorySwitch<Boolean> {
	private final PCMRepositorySimilaritySwitch similaritySwitch;

	/**
	 * @param pcmRepositorySimilaritySwitch
	 */
	public SimilarityRepositorySwitch(PCMRepositorySimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	@Override
	public Boolean caseRepository(Repository repo1) {
		Repository repo2 = (Repository) this.similaritySwitch.getCompareElement();

		if (!repo1.getEntityName().equals(repo2.getEntityName())) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean caseOperationInterface(OperationInterface opInterface1) {
		OperationInterface opInterface2 = (OperationInterface) this.similaritySwitch.getCompareElement();

		if (!opInterface1.getEntityName().equals(opInterface2.getEntityName())) {
			return Boolean.FALSE;
		}

		return this.similaritySwitch.areSimilar(
				opInterface1.getParentInterfaces__Interface(),
				opInterface2.getParentInterfaces__Interface());
	}

	@Override
	public Boolean caseOperationSignature(OperationSignature sign1) {
		OperationSignature sign2 = (OperationSignature) this.similaritySwitch.getCompareElement();

		if (!sign1.getEntityName().equals(sign2.getEntityName())) {
			return Boolean.FALSE;
		}

		var result = this.similaritySwitch.areSimilar(
				sign1.getParameters__OperationSignature(),
				sign2.getParameters__OperationSignature());

		if (!result) {
			return Boolean.FALSE;
		}

		return this.similaritySwitch.isSimilar(sign1.getReturnType__OperationSignature(),
				sign2.getReturnType__OperationSignature());
	}

	@Override
	public Boolean caseParameter(Parameter param1) {
		Parameter param2 = (Parameter) this.similaritySwitch.getCompareElement();

		if (!param1.getParameterName().equals(param2.getParameterName())) {
			return Boolean.FALSE;
		}

		return this.similaritySwitch.isSimilar(param1.getDataType__Parameter(),
				param2.getDataType__Parameter());
	}

	@Override
	public Boolean casePrimitiveDataType(PrimitiveDataType type1) {
		PrimitiveDataType type2 = (PrimitiveDataType) this.similaritySwitch.getCompareElement();
		return type1.getType() == type2.getType();
	}

	@Override
	public Boolean caseCollectionDataType(CollectionDataType type1) {
		CollectionDataType type2 = (CollectionDataType) this.similaritySwitch.getCompareElement();

		if (!type1.getEntityName().equals(type2.getEntityName())) {
			return Boolean.FALSE;
		}

		return this.similaritySwitch.isSimilar(type1.getInnerType_CollectionDataType(),
				type2.getInnerType_CollectionDataType());
	}

	@Override
	public Boolean caseCompositeDataType(CompositeDataType type1) {
		CompositeDataType type2 = (CompositeDataType) this.similaritySwitch.getCompareElement();

		if (!type1.getEntityName().equals(type2.getEntityName())) {
			return Boolean.FALSE;
		}

		return this.similaritySwitch.areSimilar(type1.getParentType_CompositeDataType(),
				type2.getParentType_CompositeDataType());
	}

	@Override
	public Boolean caseInnerDeclaration(InnerDeclaration decl1) {
		InnerDeclaration decl2 = (InnerDeclaration) this.similaritySwitch.getCompareElement();

		if (!decl1.getEntityName().equals(decl2.getEntityName())) {
			return Boolean.FALSE;
		}

		return this.similaritySwitch.isSimilar(decl1.getDatatype_InnerDeclaration(),
				decl2.getDatatype_InnerDeclaration());
	}

	@Override
	public Boolean caseBasicComponent(BasicComponent com1) {
		BasicComponent com2 = (BasicComponent) this.similaritySwitch.getCompareElement();

		return com1.getEntityName().equals(com2.getEntityName());
	}

	@Override
	public Boolean caseOperationProvidedRole(OperationProvidedRole opRole1) {
		OperationProvidedRole opRole2 = (OperationProvidedRole) this.similaritySwitch.getCompareElement();

		return this.similaritySwitch.isSimilar(
				opRole1.getProvidedInterface__OperationProvidedRole(),
				opRole2.getProvidedInterface__OperationProvidedRole());
	}

	@Override
	public Boolean caseOperationRequiredRole(OperationRequiredRole reqRole1) {
		OperationRequiredRole reqRole2 = (OperationRequiredRole) this.similaritySwitch.getCompareElement();

		return this.similaritySwitch.isSimilar(
				reqRole1.getRequiredInterface__OperationRequiredRole(),
				reqRole2.getRequiredInterface__OperationRequiredRole());
	}

	@Override
	public Boolean caseCompositeComponent(CompositeComponent com1) {
		CompositeComponent com2 = (CompositeComponent) this.similaritySwitch.getCompareElement();

		return com1.getEntityName().equals(com2.getEntityName());
	}
}