public class ParentCls {
	private int a = 1, c = 3;
	protected int b = 2, d = 4;
	public void someParentMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
		System.out.print(this.d);
	}
}

public class ChildCls extends ParentCls {
	private int a = 1, c = 3;
	public void someChildMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
		System.out.print(this.d);
	}
}