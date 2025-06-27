public class Cls {
	Cls() {
		Cls.Cls2.Cls5<Cls>.Cls6.Cls7 a = null;
	}

	class Cls2 {
		class Cls5<T> {
			class Cls6 {
				class Cls7 {}
			}
		}
	}

	class Cls3 {
	}

	class Cls4 {
	}
}