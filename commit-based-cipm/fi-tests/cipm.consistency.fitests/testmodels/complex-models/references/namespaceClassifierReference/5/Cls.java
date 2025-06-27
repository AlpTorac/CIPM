public class Cls {
	Cls() {
		Cls.Cls2<Cls3, Cls4>.Cls5<Cls>.Cls6 a = null;
	}

	class Cls2<T1, T2> {
		class Cls5<T3> {
			class Cls6 {
			}
		}
	}

	class Cls3 {
	}

	class Cls4 {
	}
}