package cipm.consistency.fluentapi.gen;

import java.util.Map;

public final class FluentAPIDocumentationUtil {
	private static final String doNotUseFromOutsideDocumentationNote = "This method is not intended for outside use, but is generated as public because of code generation limitations.";
	private static final String documentationParagraphSeparator = "<p><p>";

	private static final String classMethodOverviewIntroTemplate = "It is recommended to only use the methods presented below. In the following, replace 'X's with the concrete feature name:"
			+ getDocParagraphSeparator() + "<ul>%s</ul>";

	public static String getDocParagraphSeparator() {
		return documentationParagraphSeparator;
	}

	public static String getDoNotUseFromOutsideDocNote() {
		return doNotUseFromOutsideDocumentationNote;
	}

	public static String getClassMethodOverviewIntroTemplate() {
		return classMethodOverviewIntroTemplate;
	}

	public static String getClassMethodOverviewIntro(Map<String, String> methodNameToSummaryMap) {
		return String.format(getClassMethodOverviewIntroTemplate(), serialiseSummaries(methodNameToSummaryMap));
	}

	public static String appendDoNotUseFromOutsideDocNoteAtEnd() {
		return getDocParagraphSeparator() + doNotUseFromOutsideDocumentationNote + getDocParagraphSeparator();
	}

	public static String appendSummaryToStart(String summary) {
		return summary + getDocParagraphSeparator();
	}

	public static String serialiseSummaries(Map<String, String> methodNameToSummaryMap) {
		var sb = new StringBuilder();
		methodNameToSummaryMap
				.forEach((metName, summary) -> sb.append("<li><b>").append(metName).append("</b>: ").append(summary));
		return sb.toString();
	}
}
