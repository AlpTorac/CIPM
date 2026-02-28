package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;

public interface IFluentAPIFeatureTemplate extends IFluentAPITemplate {
	static final String GETTER_PREFIX = "get";
	static final String SETTER_PREFIX = "set";

	public default String getter() {
		return GETTER_PREFIX + StringUtils.capitalize(this.get());
	}

	public default String setter() {
		return SETTER_PREFIX + StringUtils.capitalize(this.get());
	}

	public default String getterCall() {
		return "." + getter() + "()";
	}

	public default String setterCall(String param) {
		return "." + setter() + "(" + param + ")";
	}
}
