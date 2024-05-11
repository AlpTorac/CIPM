package cipm.consistency.fitests.similarity.java.generators;

public class NameGenerator extends AbstractParameterGenerator<String> {
	private static int nameCount = 0;
	
	private String namePrefix = "name";
	
	public NameGenerator() {
		super();
	}
	
	protected int getNameCount() {
		return nameCount;
	}
	
	protected String getNamePrefix() {
		return this.namePrefix;
	}
	
	public void setNamePrefix(String prefix) {
		this.namePrefix = prefix;
	}
	
	protected void incNameCount() {
		nameCount++;
	}
	
	@Override
	public void reset() {
		super.reset();
		nameCount = 0;
	}
	
	@Override
	public String createElement() {
		var result = this.getNamePrefix() + this.getNameCount();
		this.incNameCount();
		return result;
	}
}
