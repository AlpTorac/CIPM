public class ParentCls {
	private int b, a, c;
	public void someParentMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
	}
}

public class ChildCls extends ParentCls {
	private int b, a, c;
	public void someChildMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
	}
}