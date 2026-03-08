package cipm.consistency.fluentapi.gen;

public interface IFluentAPITemplate {
	public String get();

	public default String call(String... params) {
		var callRoot = "." + get();
		var args = "(";

		if (params != null && params.length > 0) {
			args += String.join(",", params);
		}

		return callRoot + args + ")";
	}

	public default String thisCall(String... params) {
		return "this" + call(params);
	}
}
