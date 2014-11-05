package other;

interface func<T, V> {
	T func (V arg);
}

public class AnonymousFunction {
	public static void main(String args[]) {
		func<Integer,Integer> sq = new func<Integer, Integer>() {
			@Override
			public Integer func(Integer arg) {
				return arg*arg;
			}
		};
		
		System.out.println(sq.func(5));
	}
}

class B {
	int meth(func<Integer, Integer> obj) {
		return obj.func(2);
	}
}