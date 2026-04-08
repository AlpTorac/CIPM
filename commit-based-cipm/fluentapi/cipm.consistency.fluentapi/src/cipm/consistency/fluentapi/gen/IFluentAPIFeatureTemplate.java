package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;

public interface IFluentAPIFeatureTemplate extends IFluentAPITemplate {
	static final String THIS_PREFIX = "this.";
	static final String GETTER_PREFIX = "get";
	static final String SETTER_PREFIX = "set";

	public default String inThis() {
		return THIS_PREFIX + this.get();
	}

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

	public default String thisGetterCall() {
		return "this" + getterCall();
	}

	public default String thisSetterCall(String param) {
		return "this" + setterCall(param);
	}
}
