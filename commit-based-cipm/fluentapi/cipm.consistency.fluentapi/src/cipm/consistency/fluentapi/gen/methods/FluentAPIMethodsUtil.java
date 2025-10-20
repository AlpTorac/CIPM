package cipm.consistency.fluentapi.gen.methods;

public class FluentAPIMethodsUtil {
	private static final String whitespace = " ";
	private static final String semicolon = ";";
	private static final String dot = ".";
	private static final String lhsBracket = "(";
	private static final String rhsBracket = ")";
	private static final String escapedQuotation = "\"";
	private static final String comma = ",";

	private static final String thisStatement = "this";
	private static final String superStatement = "super";

	private static final String eListClassName = "EList";

	private static final String newLine = System.lineSeparator();
	private static final String endLine = semicolon + newLine;

	private static final String returnStatement = "return";

	private static final String returnSelfStatement = returnStatement + whitespace + "this" + semicolon;

	private static final String eClassMetCall = dot + "eClass()";
	private static final String eContainerMetCall = dot + "eContainer()";

	private static final String eListCopyMethodName = "List.copyOf";
	private static final String addToEListMethodName = "add";
	private static final String removeFromEListMethodName = "remove";

	private static final String getEStructuralFeatureMetName = "getEStructuralFeature";
	private static final String eGetMetName = "eGet";
	private static final String eSetMetName = "eSet";
	private static final String eUnsetMetName = "eUnset";

	public static String putInBrackets(String inBracket) {
		return lhsBracket + inBracket + rhsBracket;
	}

	public static String putStringInBrackets(String str) {
		return putInBrackets(escapedQuotation + str + escapedQuotation);
	}

	public static String getReturnSelfMethodBody() {
		return returnSelfStatement;
	}

	public static String getContainerMethodCall(String caller) {
		return caller + eContainerMetCall;
	}

	public static String callMethodWithThisArgumentAndReturnThis(Class<?> fluentAPIMetsCls, String methodName) {
		return fluentAPIMetsCls.getName() + dot + putInBrackets(thisStatement) + endLine + getReturnSelfMethodBody();
	}

	public static String callMethodAndReturnThis(String methodCall) {
		return methodCall + endLine + returnSelfStatement;
	}

	public static String getThisEFeatureStatement(String featName) {
		return getEFeatureStatement(thisStatement, featName);
	}

	public static String getThisEGetStatement(String featName) {
		return getEGetStatement(thisStatement, featName);
	}

	public static String getThisESetStatement(String featName, String newVal) {
		return getESetStatement(thisStatement, featName, newVal);
	}

	public static String getEFeatureStatement(String caller, String featName) {
		return caller + eClassMetCall + dot + getEStructuralFeatureMetName + putStringInBrackets(featName);
	}

	public static String getEGetStatement(String caller, String featName) {
		return caller + dot + eGetMetName + putInBrackets(getEFeatureStatement(caller, featName));
	}

	public static String getESetStatement(String caller, String featName, String newVal) {
		return caller + dot + eSetMetName + putInBrackets(getEFeatureStatement(caller, featName) + comma + newVal);
	}

	public static String getEUnsetStatement(String caller, String featName) {
		return caller + dot + eUnsetMetName + putInBrackets(getEFeatureStatement(caller, featName));
	}

	public static String castTo(String clsNameToCastTo, String val) {
		return putInBrackets(clsNameToCastTo) + whitespace + val;
	}

	public static String castTo(Class<?> clsToCastTo, String val) {
		return castTo(clsToCastTo.getName(), val);
	}

	public static String castToEList(String val) {
		return castTo(eListClassName, val);
	}

	public static String castMethodReturnTypeTo(Class<?> clsToCastTo, String val) {
		return returnStatement + whitespace + putInBrackets(castTo(clsToCastTo, val)) + endLine;
	}

	public static String getEGetAsEList(String caller, String featName) {
		return castToEList(getEGetStatement(caller, featName));
	}

	public static String getShallowCopyEGetAsEList(String caller, String featName) {
		return getShallowCopiedEList(castToEList(getEGetStatement(caller, featName)));
	}

	public static String getShallowCopiedEList(String eListVal) {
		return eListCopyMethodName + putInBrackets(eListVal);
	}

	public static String addToEList(String elist, String newVal) {
		return elist + dot + addToEListMethodName + putInBrackets(newVal);
	}

	public static String removeFromEList(String elist, String oldVal) {
		return elist + dot + removeFromEListMethodName + putInBrackets(oldVal);
	}
}
