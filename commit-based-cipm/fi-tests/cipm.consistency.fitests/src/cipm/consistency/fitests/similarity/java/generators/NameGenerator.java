package cipm.consistency.fitests.similarity.java.generators;

public class NameGenerator extends AbstractParameterGenerator<String> {
	private String namePrefix = "name";
	private int nameCount = 0;
	
	public NameGenerator() {
		super();
	}
	
	protected int getNameCount() {
		return this.nameCount;
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
		this.nameCount = 0;
	}
	
	@Override
	public String createDefaultElement() {
		var result = this.getNamePrefix() + this.getNameCount();
		this.incNameCount();
		return result;
	}
}
