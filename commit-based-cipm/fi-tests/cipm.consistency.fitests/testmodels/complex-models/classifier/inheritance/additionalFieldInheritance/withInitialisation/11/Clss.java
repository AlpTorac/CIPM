public class ParentCls {
	protected int a = 1, b = 2, c = 3, d = 4;
	public void someParentMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
		System.out.print(this.d);
	}
}

public class ChildCls extends ParentCls {
	protected int b = 2, a = 1, c = 3, d = 4;
	public void someChildMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
		System.out.print(this.d);
	}
}