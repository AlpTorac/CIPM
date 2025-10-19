package cipm.consistency.fluentapi.gen.methods;

public class FluentAPIMethodsUtil {
	private static final String whitespace = " ";
	private static final String semicolon = ";";
	private static final String dot = ".";
	private static final String lhsBracket = "(";
	private static final String rhsBracket = "(";
	private static final String escapedQuotation = "\"";
	private static final String comma = ",";

	private static final String thisStatement = "this";
	private static final String superStatement = "super";

	private static final String newLine = System.lineSeparator();
	private static final String endLine = semicolon + newLine;

	private static final String returnStatement = "return";

	private static final String returnSelfStatement = returnStatement + whitespace + "this" + semicolon;

	private static final String eClassMetCall = dot + "eClass()";
	private static final String getEStructuralFeatureMetName = "getEStructuralFeature";
	private static final String eGetMetName = "eGet";
	private static final String eSetMetName = "eSet";

	public static String putInBrackets(String inBracket) {
		return lhsBracket + inBracket + rhsBracket;
	}

	public static String putStringInBrackets(String str) {
		return putInBrackets(escapedQuotation + str + escapedQuotation);
	}

	public static String getReturnSelfMethodBody() {
		return returnSelfStatement;
	}

	public static String callMethodWithThisArgumentAndReturnThis(Class<?> fluentAPIMetsCls, String methodName) {
		return fluentAPIMetsCls.getName() + dot + putInBrackets(thisStatement) + endLine + getReturnSelfMethodBody();
	}

	public static String getThisFeatureStatement(String featName) {
		return thisStatement + eClassMetCall + dot + getEStructuralFeatureMetName + putStringInBrackets(featName);
	}

	public static String getThisEGetStatement(String featName) {
		return thisStatement + dot + eGetMetName + putInBrackets(getThisFeatureStatement(featName));
	}

	public static String getThisESetStatement(String featName, String newVal) {
		return thisStatement + dot + eSetMetName + putInBrackets(getThisFeatureStatement(featName) + comma + newVal);
	}

	public static String castMethodReturnTypeTo(Class<?> clsToCastTo, String methodBody) {
		return returnStatement + whitespace
				+ putInBrackets(putInBrackets(clsToCastTo.getName()) + whitespace + methodBody) + endLine;
	}
}
