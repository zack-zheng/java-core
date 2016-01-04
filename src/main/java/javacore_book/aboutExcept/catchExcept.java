package javacore_book.aboutExcept;

import java.util.Random;

class Exc0 {
	public static void main(String args[]) {
		int d = 0;
		int a = 42 / d;
	}
}

class Exc1 {
	static void subroutine() {
		int d = 0;
		int a = 10 / d;
	}

	public static void main(String args[]) {
		Exc1.subroutine();
	}
}

class Exc2 {
	public static void main(String args[]) {
		int d, a;
		try { // monitor a block of code.
			d = 0;
			a = 42 / d;
			System.out.println("This will not be printed.");
		} catch (ArithmeticException e) { // catch divide-by-zero error
			System.out.println("Division by zero.");
		}
		System.out.println("After catch statement.");
	}
}

// Handle an exception and move on.

class HandleError {
	public static void main(String args[]) {
		int a = 0, b = 0, c = 0;
		Random r = new Random();
		for (int i = 0; i < 32000; i++) {
			try {
				b = r.nextInt();
				c = r.nextInt();
				a = 12345 / (b / c);
			} catch (ArithmeticException e) {
				System.out.println("Division by zero.");
				a = 0; // set a to zero and continue
			}
			System.out.println("a: " + a);
		}
	}
}

// Demonstrate multiple catch statements.
class MultiCatch {
	public static void main(String args[]) {
		try {
			int a = args.length;
			System.out.println("a = " + a);
			int b = 42 / a;
			int c[] = { 1 };
			c[42] = 99;
		} catch (ArithmeticException e) {
			System.out.println("Divide by 0: " + e);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array index oob: " + e);
		}
		System.out.println("After try/catch blocks.");
	}
}

/*
 * This program contains an error. A subclass must come before its superclass in
 * a series of catch statements. If not, unreachable code will be created and a
 * compile-time error will result.
 */
class SuperSubCatch {
	public static void main(String args[]) {
		try {
			int a = 0;
			int b = 42 / a;
		} catch (Exception e) {
			System.out.println("Generic Exception catch.");
		}
		/*
		 * This catch is never reached because ArithmeticException is a subclass
		 * of Exception.
		 */
		/*
		 * catch (ArithmeticException e) { // ERROR - unreachable
		 * System.out.println("This is never reached."); }
		 */
	}
}

// An example of nested try statements.
class NestTry {
	public static void main(String args[]) {
		try {
			int a = args.length;
			/*
			 * If no command-line args are present, the following statement will
			 * generate a divide-by-zero exception.
			 */
			int b = 42 / a;
			System.out.println("a = " + a);
			try { // nested try block
				/*
				 * If one command-line arg is used, then a divide-by-zero
				 * exception will be generated by the following code.
				 */
				if (a == 1)
					a = a / (a - a); // division by zero
				/*
				 * If two command-line args are used, then generate an
				 * out-of-bounds exception.
				 */
				if (a == 2) {
					int c[] = { 1 };
					c[42] = 99; // generate an out-of-bounds exception
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Array index out-of-bounds: " + e);
			}
		} catch (ArithmeticException e) {
			System.out.println("Divide by 0: " + e);
		}
	}
}

/*
 * Try statements can be implicitly nested via calls to methods.
 */
class MethNestTry {
	static void nesttry(int a) {
		try { // nested try block
		/*
		 * If one command-line arg is used, then a divide-by-zero exception will
		 * be generated by the following code.
		 */
			if (a == 1)
				a = a / (a - a); // division by zero
			/*
			 * If two command-line args are used, then generate an out-of-bounds
			 * exception.
			 */
			if (a == 2) {
				int c[] = { 1 };
				c[42] = 99; // generate an out-of-bounds exception
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array index out-of-bounds: " + e);
		}
	}

	public static void main(String args[]) {
		try {
			int a = args.length;
			/*
			 * If no command-line args are present, the following statement will
			 * generate a divide-by-zero exception.
			 */
			int b = 42 / a;
			System.out.println("a = " + a);
			nesttry(a);
		} catch (ArithmeticException e) {
			System.out.println("Divide by 0: " + e);
		}
	}
}