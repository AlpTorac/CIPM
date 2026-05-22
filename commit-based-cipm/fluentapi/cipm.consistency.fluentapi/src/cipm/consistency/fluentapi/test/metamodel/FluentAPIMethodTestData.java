package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;

/**
 * A class that encapsulates which methods the metamodel-related tests should
 * consider. Those tests should take an instance of this class and use it as
 * information source.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIMethodTestData {
	private List<EClass> eClssToCheckFor;

	private Function<EClass, String> methodName;
	private Function<EClass, EClassifier> returnTypeOfOp = (eCls) -> eCls;
	private Function<EClass, List<String>> paramNames = (eCls) -> List.of();
	private Function<EClass, List<EClassifier>> paramTypes = (eCls) -> List.of();
	private Function<EClass, Boolean> expectMultiValueVariants = (eCls) -> false;
	private Function<EClass, Boolean> expectBigNumberVariants = (eCls) -> false;

	public String getMethodName(EClass eCls) {
		return methodName.apply(eCls);
	}

	public void setMethodNamePrefix(Function<EClass, String> methodName) {
		this.methodName = methodName;
	}

	public EClassifier getReturnTypeOfOp(EClass eCls) {
		return returnTypeOfOp.apply(eCls);
	}

	public <T extends EClassifier> void setReturnTypeOfOp(Function<EClass, T> returnTypeOfOp) {
		this.returnTypeOfOp = (eCls) -> (T) returnTypeOfOp.apply(eCls);
	}

	public List<String> getParamNames(EClass eCls) {
		return paramNames.apply(eCls);
	}

	public void setParamNames(Function<EClass, List<String>> paramNames) {
		this.paramNames = paramNames;
	}

	public List<EClassifier> getParamTypes(EClass eCls) {
		return paramTypes.apply(eCls);
	}

	public void setParamTypes(Function<EClass, List<EClassifier>> paramTypes) {
		this.paramTypes = paramTypes;
	}

	public List<EClass> geteClssToCheckFor() {
		return eClssToCheckFor;
	}

	public void seteClssToCheckFor(List<EClass> eClssToCheckFor) {
		this.eClssToCheckFor = eClssToCheckFor;
	}

	public Boolean getExpectMultiValueVariants(EClass eCls) {
		return expectMultiValueVariants.apply(eCls);
	}

	public void setExpectMultiValueVariants(Function<EClass, Boolean> expectMultiValueVariants) {
		this.expectMultiValueVariants = expectMultiValueVariants;
	}

	public Boolean getExpectBigNumberVariants(EClass eCls) {
		return expectBigNumberVariants.apply(eCls);
	}

	public void setExpectBigNumberVariants(Function<EClass, Boolean> expectBigNumberVariants) {
		this.expectBigNumberVariants = expectBigNumberVariants;
	}

}