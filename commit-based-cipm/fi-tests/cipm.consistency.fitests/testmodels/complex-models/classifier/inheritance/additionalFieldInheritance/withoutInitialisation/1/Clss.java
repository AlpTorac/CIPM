public class ParentCls {
	protected int a, b, c;
	public void someParentMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
	}
}

public class ChildCls extends ParentCls {
	public void someChildMet() {
		System.out.print(this.a);
		System.out.print(this.b);
		System.out.print(this.c);
	}
}