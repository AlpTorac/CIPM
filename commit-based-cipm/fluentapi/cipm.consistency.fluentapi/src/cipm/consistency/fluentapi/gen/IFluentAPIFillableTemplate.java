package cipm.consistency.fluentapi.gen;

public interface IFluentAPIFillableTemplate extends IFluentAPITemplate {
	public String getFor(Object... params);

	public default String callFor(Object[] templateParams, String... methodParams) {
		var callRoot = "." + getFor(templateParams);
		var args = "(";

		if (methodParams == null || methodParams.length == 0) {
			args += String.join(",", methodParams);
		}

		return callRoot + args + ")";
	}

	public default String thisCallFor(Object[] templateParams, String... methodParams) {
		return "this" + callFor(templateParams, methodParams);
	}
}
