package cipm.consistency.fluentapi.gen.methods;

public class FluentAPIMethodsUtil {
	private static final String semicolon = ";";

	private static final String newLine = System.lineSeparator();
	private static final String endLine = semicolon + newLine;

	public static String joinLOC(String... loc) {
		var result = "";
		for (int i = 0; i < loc.length; i++) {
			result += loc[i] + endLine;
		}
		return result;
	}
}
